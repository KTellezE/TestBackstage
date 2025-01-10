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

import com.novus.branch.persistence.PositionPersistence;
import com.novus.branch.model.PositionDO;
import com.novus.branch.commons.dto.PositionDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;

/**
 * Class PositionServiceTest
 * 
 * @author username@novus.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class PositionServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( PositionServiceTest.class );

  @Autowired
  private PositionService positionService;

  @MockBean
  private PositionPersistence positionPersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindPositions()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<PositionDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(positionPersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var positions = this.positionService.findPositions( request );

    LOG.info( "Response: {}", positions );

    assertNotNull( positions );
    assertNotNull( positions.getData() );
    assertTrue( positions.getData().isEmpty() );
  }

  @Test
  void testFindPosition()
  {
    Integer id = 2;
    PositionDO positionDo = new PositionDO();
    positionDo.setId(id);

    when(positionPersistence.findById(id)).thenReturn(Optional.of(positionDo));

    var positions = this.positionService.find( id );

    LOG.info( "Response: {}", positions );

    assertNotNull( positions );

    assertNotNull(positions.getBody());

    assertEquals(positionDo.getId(), positions.getBody().getId());

    verify(positionPersistence, times(1)).findById(id);
  }

  @Test
  void testFindPositionWhenIdNotPresent() {
    Integer id = 2;
    when(positionPersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<PositionDto> response = positionService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreatePosition() {
    String agentName = "Tester";
    PositionDto positionDto = new PositionDto();

    PositionDO positionDo = new PositionDO();

    when(positionPersistence.save(any(PositionDO.class))).thenReturn(positionDo);

    GenericResponseDto<PositionDto> response = positionService.create(positionDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(positionDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdatePosition() {
    Integer id = 2;
    PositionDto positionDto = new PositionDto();
    positionDto.setId(id);

    PositionDO positionDo = new PositionDO();
    positionDo.setId(id);

    when(positionPersistence.findById(id)).thenReturn(Optional.of(positionDo));

    when(positionPersistence.save(any(PositionDO.class))).thenReturn(positionDo);

    GenericResponseDto<Boolean> response = positionService.update(positionDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(positionPersistence, times(1)).save(any(PositionDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdatePositionNotFound() {
    Integer id = 2;
    PositionDto positionDto = new PositionDto();
    positionDto.setId(id);

    when(positionPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> positionService.update(positionDto));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeletePosition() {
    Integer id = 2;

    PositionDO positionDo = new PositionDO();
    positionDo.setId(id);

    when(positionPersistence.findById(id)).thenReturn(Optional.of(positionDo));

    doNothing().when(positionPersistence).delete(any(PositionDO.class));

    GenericResponseDto<Boolean> response = positionService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(positionPersistence, times(1)).delete(any(PositionDO.class));

  }

  @Test
  void testDeletePositionNotFound() {
    Integer id = 2;

    when(positionPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> positionService.delete(id));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

}
