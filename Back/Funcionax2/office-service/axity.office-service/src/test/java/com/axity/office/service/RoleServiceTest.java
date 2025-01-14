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

import com.axity.office.persistence.RolePersistence;
import com.axity.office.model.RoleDO;
import com.axity.office.commons.dto.RoleDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;

/**
 * Class RoleServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class RoleServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( RoleServiceTest.class );

  @Autowired
  private RoleService roleService;

  @MockBean
  private RolePersistence rolePersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindRoles()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<RoleDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(rolePersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var roles = this.roleService.findRoles( request );

    LOG.info( "Response: {}", roles );

    assertNotNull( roles );
    assertNotNull( roles.getData() );
    assertTrue( roles.getData().isEmpty() );
  }

  @Test
  void testFindRole()
  {
    Integer id = 2;
    RoleDO roleDo = new RoleDO();
    roleDo.setId(id);

    when(rolePersistence.findById(id)).thenReturn(Optional.of(roleDo));

    var roles = this.roleService.find( id );

    LOG.info( "Response: {}", roles );

    assertNotNull( roles );

    assertNotNull(roles.getBody());

    assertEquals(roleDo.getId(), roles.getBody().getId());

    verify(rolePersistence, times(1)).findById(id);
  }

  @Test
  void testFindRoleWhenIdNotPresent() {
    Integer id = 2;
    when(rolePersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<RoleDto> response = roleService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateRole() {
    String agentName = "Tester";
    RoleDto roleDto = new RoleDto();

    RoleDO roleDo = new RoleDO();

    when(rolePersistence.save(any(RoleDO.class))).thenReturn(roleDo);

    GenericResponseDto<RoleDto> response = roleService.create(roleDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(roleDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateRole() {
    Integer id = 2;
    RoleDto roleDto = new RoleDto();
    roleDto.setId(id);

    RoleDO roleDo = new RoleDO();
    roleDo.setId(id);

    when(rolePersistence.findById(id)).thenReturn(Optional.of(roleDo));

    when(rolePersistence.save(any(RoleDO.class))).thenReturn(roleDo);

    GenericResponseDto<Boolean> response = roleService.update(roleDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(rolePersistence, times(1)).save(any(RoleDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateRoleNotFound() {
    Integer id = 2;
    RoleDto roleDto = new RoleDto();
    roleDto.setId(id);

    when(rolePersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> roleService.update(roleDto));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteRole() {
    Integer id = 2;

    RoleDO roleDo = new RoleDO();
    roleDo.setId(id);

    when(rolePersistence.findById(id)).thenReturn(Optional.of(roleDo));

    doNothing().when(rolePersistence).delete(any(RoleDO.class));

    GenericResponseDto<Boolean> response = roleService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(rolePersistence, times(1)).delete(any(RoleDO.class));

  }

  @Test
  void testDeleteRoleNotFound() {
    Integer id = 2;

    when(rolePersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> roleService.delete(id));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

}
