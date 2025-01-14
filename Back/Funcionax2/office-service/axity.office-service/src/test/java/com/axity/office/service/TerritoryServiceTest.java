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

import com.axity.office.persistence.TerritoryPersistence;
import com.axity.office.model.TerritoryDO;
import com.axity.office.commons.dto.TerritoryDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;

/**
 * Class TerritoryServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class TerritoryServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( TerritoryServiceTest.class );

  @Autowired
  private TerritoryService territoryService;

  @MockBean
  private TerritoryPersistence territoryPersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindTerritorys()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<TerritoryDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(territoryPersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var territorys = this.territoryService.findTerritorys( request );

    LOG.info( "Response: {}", territorys );

    assertNotNull( territorys );
    assertNotNull( territorys.getData() );
    assertTrue( territorys.getData().isEmpty() );
  }

  @Test
  void testFindTerritory()
  {
    Integer id = 2;
    TerritoryDO territoryDo = new TerritoryDO();
    territoryDo.setId(id);

    when(territoryPersistence.findById(id)).thenReturn(Optional.of(territoryDo));

    var territorys = this.territoryService.find( id );

    LOG.info( "Response: {}", territorys );

    assertNotNull( territorys );

    assertNotNull(territorys.getBody());

    assertEquals(territoryDo.getId(), territorys.getBody().getId());

    verify(territoryPersistence, times(1)).findById(id);
  }

  @Test
  void testFindTerritoryWhenIdNotPresent() {
    Integer id = 2;
    when(territoryPersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<TerritoryDto> response = territoryService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateTerritory() {
    String agentName = "Tester";
    TerritoryDto territoryDto = new TerritoryDto();

    TerritoryDO territoryDo = new TerritoryDO();

    when(territoryPersistence.save(any(TerritoryDO.class))).thenReturn(territoryDo);

    GenericResponseDto<TerritoryDto> response = territoryService.create(territoryDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(territoryDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateTerritory() {
    Integer id = 2;
    TerritoryDto territoryDto = new TerritoryDto();
    territoryDto.setId(id);

    TerritoryDO territoryDo = new TerritoryDO();
    territoryDo.setId(id);

    when(territoryPersistence.findById(id)).thenReturn(Optional.of(territoryDo));

    when(territoryPersistence.save(any(TerritoryDO.class))).thenReturn(territoryDo);

    GenericResponseDto<Boolean> response = territoryService.update(territoryDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(territoryPersistence, times(1)).save(any(TerritoryDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateTerritoryNotFound() {
    Integer id = 2;
    TerritoryDto territoryDto = new TerritoryDto();
    territoryDto.setId(id);

    when(territoryPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> territoryService.update(territoryDto));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteTerritory() {
    Integer id = 2;

    TerritoryDO territoryDo = new TerritoryDO();
    territoryDo.setId(id);

    when(territoryPersistence.findById(id)).thenReturn(Optional.of(territoryDo));

    doNothing().when(territoryPersistence).delete(any(TerritoryDO.class));

    GenericResponseDto<Boolean> response = territoryService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(territoryPersistence, times(1)).delete(any(TerritoryDO.class));

  }

  @Test
  void testDeleteTerritoryNotFound() {
    Integer id = 2;

    when(territoryPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> territoryService.delete(id));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

}
