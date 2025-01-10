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

import com.novus.branch.persistence.RegionPersistence;
import com.novus.branch.model.RegionDO;
import com.novus.branch.commons.dto.RegionDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;

/**
 * Class RegionServiceTest
 * 
 * @author username@novus.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class RegionServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( RegionServiceTest.class );

  @Autowired
  private RegionService regionService;

  @MockBean
  private RegionPersistence regionPersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindRegions()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<RegionDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(regionPersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var regions = this.regionService.findRegions( request );

    LOG.info( "Response: {}", regions );

    assertNotNull( regions );
    assertNotNull( regions.getData() );
    assertTrue( regions.getData().isEmpty() );
  }

  @Test
  void testFindRegion()
  {
    Integer id = 2;
    RegionDO regionDo = new RegionDO();
    regionDo.setId(id);

    when(regionPersistence.findById(id)).thenReturn(Optional.of(regionDo));

    var regions = this.regionService.find( id );

    LOG.info( "Response: {}", regions );

    assertNotNull( regions );

    assertNotNull(regions.getBody());

    assertEquals(regionDo.getId(), regions.getBody().getId());

    verify(regionPersistence, times(1)).findById(id);
  }

  @Test
  void testFindRegionWhenIdNotPresent() {
    Integer id = 2;
    when(regionPersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<RegionDto> response = regionService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateRegion() {
    String agentName = "Tester";
    RegionDto regionDto = new RegionDto();

    RegionDO regionDo = new RegionDO();

    when(regionPersistence.save(any(RegionDO.class))).thenReturn(regionDo);

    GenericResponseDto<RegionDto> response = regionService.create(regionDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(regionDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateRegion() {
    Integer id = 2;
    RegionDto regionDto = new RegionDto();
    regionDto.setId(id);

    RegionDO regionDo = new RegionDO();
    regionDo.setId(id);

    when(regionPersistence.findById(id)).thenReturn(Optional.of(regionDo));

    when(regionPersistence.save(any(RegionDO.class))).thenReturn(regionDo);

    GenericResponseDto<Boolean> response = regionService.update(regionDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(regionPersistence, times(1)).save(any(RegionDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateRegionNotFound() {
    Integer id = 2;
    RegionDto regionDto = new RegionDto();
    regionDto.setId(id);

    when(regionPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> regionService.update(regionDto));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteRegion() {
    Integer id = 2;

    RegionDO regionDo = new RegionDO();
    regionDo.setId(id);

    when(regionPersistence.findById(id)).thenReturn(Optional.of(regionDo));

    doNothing().when(regionPersistence).delete(any(RegionDO.class));

    GenericResponseDto<Boolean> response = regionService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(regionPersistence, times(1)).delete(any(RegionDO.class));

  }

  @Test
  void testDeleteRegionNotFound() {
    Integer id = 2;

    when(regionPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> regionService.delete(id));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

}
