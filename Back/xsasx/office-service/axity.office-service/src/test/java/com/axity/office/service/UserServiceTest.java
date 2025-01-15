package com.axity.office.service;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.axity.office.persistence.UserPersistence;
import com.axity.office.model.UserDO;
import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;

/**
 * Class UserServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class UserServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( UserServiceTest.class );

  @Autowired
  private UserService userService;

  @MockBean
  private UserPersistence userPersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindUsers()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<UserDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(userPersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var users = this.userService.findUsers( request );

    LOG.info( "Response: {}", users );

    assertNotNull( users );
    assertNotNull( users.getData() );
    assertTrue( users.getData().isEmpty() );
  }

  @Test
  void testFindUser()
  {
    Integer id = 2;
    UserDO userDo = new UserDO();
    userDo.setId(id);

    when(userPersistence.findById(id)).thenReturn(Optional.of(userDo));

    var users = this.userService.find( id );

    LOG.info( "Response: {}", users );

    assertNotNull( users );

    assertNotNull(users.getBody());

    assertEquals(userDo.getId(), users.getBody().getId());

    verify(userPersistence, times(1)).findById(id);
  }

  @Test
  void testFindUserWhenIdNotPresent() {
    Integer id = 2;
    when(userPersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<UserDto> response = userService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateUser() {
    String agentName = "Tester";
    UserDto userDto = new UserDto();

    UserDO userDo = new UserDO();

    when(userPersistence.save(any(UserDO.class))).thenReturn(userDo);

    GenericResponseDto<UserDto> response = userService.create(userDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(userDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateUser() {
    Integer id = 2;
    UserDto userDto = new UserDto();
    userDto.setId(id);

    UserDO userDo = new UserDO();
    userDo.setId(id);

    when(userPersistence.findById(id)).thenReturn(Optional.of(userDo));

    when(userPersistence.save(any(UserDO.class))).thenReturn(userDo);

    GenericResponseDto<Boolean> response = userService.update(userDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(userPersistence, times(1)).save(any(UserDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateUserNotFound() {
    Integer id = 2;
    UserDto userDto = new UserDto();
    userDto.setId(id);

    when(userPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> userService.update(userDto));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteUser() {
    Integer id = 2;

    UserDO userDo = new UserDO();
    userDo.setId(id);

    when(userPersistence.findById(id)).thenReturn(Optional.of(userDo));

    doNothing().when(userPersistence).delete(any(UserDO.class));

    GenericResponseDto<Boolean> response = userService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(userPersistence, times(1)).delete(any(UserDO.class));

  }

  @Test
  void testDeleteUserNotFound() {
    Integer id = 2;

    when(userPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> userService.delete(id));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

}
