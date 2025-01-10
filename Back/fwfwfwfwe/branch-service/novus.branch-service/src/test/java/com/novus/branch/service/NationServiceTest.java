package com.novus.branch.service;

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

import com.novus.branch.persistence.NationPersistence;
import com.novus.branch.model.NationDO;
import com.novus.branch.commons.dto.NationDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;

/**
 * Class NationServiceTest
 * 
 * @author username@novus.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class NationServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( NationServiceTest.class );

  @Autowired
  private NationService nationService;

  @MockBean
  private NationPersistence nationPersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindNations()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<NationDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(nationPersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var nations = this.nationService.findNations( request );

    LOG.info( "Response: {}", nations );

    assertNotNull( nations );
    assertNotNull( nations.getData() );
    assertTrue( nations.getData().isEmpty() );
  }

  @Test
  void testFindNation()
  {
    Integer id = 2;
    NationDO nationDo = new NationDO();
    nationDo.setId(id);

    when(nationPersistence.findById(id)).thenReturn(Optional.of(nationDo));

    var nations = this.nationService.find( id );

    LOG.info( "Response: {}", nations );

    assertNotNull( nations );

    assertNotNull(nations.getBody());

    assertEquals(nationDo.getId(), nations.getBody().getId());

    verify(nationPersistence, times(1)).findById(id);
  }

  @Test
  void testFindNationWhenIdNotPresent() {
    Integer id = 2;
    when(nationPersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<NationDto> response = nationService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateNation() {
    String agentName = "Tester";
    NationDto nationDto = new NationDto();

    NationDO nationDo = new NationDO();

    when(nationPersistence.save(any(NationDO.class))).thenReturn(nationDo);

    GenericResponseDto<NationDto> response = nationService.create(nationDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(nationDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateNation() {
    Integer id = 2;
    NationDto nationDto = new NationDto();
    nationDto.setId(id);

    NationDO nationDo = new NationDO();
    nationDo.setId(id);

    when(nationPersistence.findById(id)).thenReturn(Optional.of(nationDo));

    when(nationPersistence.save(any(NationDO.class))).thenReturn(nationDo);

    GenericResponseDto<Boolean> response = nationService.update(nationDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(nationPersistence, times(1)).save(any(NationDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateNationNotFound() {
    Integer id = 2;
    NationDto nationDto = new NationDto();
    nationDto.setId(id);

    when(nationPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> nationService.update(nationDto));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteNation() {
    Integer id = 2;

    NationDO nationDo = new NationDO();
    nationDo.setId(id);

    when(nationPersistence.findById(id)).thenReturn(Optional.of(nationDo));

    doNothing().when(nationPersistence).delete(any(NationDO.class));

    GenericResponseDto<Boolean> response = nationService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(nationPersistence, times(1)).delete(any(NationDO.class));

  }

  @Test
  void testDeleteNationNotFound() {
    Integer id = 2;

    when(nationPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> nationService.delete(id));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

}
