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

import com.axity.office.persistence.OfficePersistence;
import com.axity.office.model.OfficeDO;
import com.axity.office.commons.dto.OfficeDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;

/**
 * Class OfficeServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class OfficeServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( OfficeServiceTest.class );

  @Autowired
  private OfficeService officeService;

  @MockBean
  private OfficePersistence officePersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindOffices()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<OfficeDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(officePersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var offices = this.officeService.findOffices( request );

    LOG.info( "Response: {}", offices );

    assertNotNull( offices );
    assertNotNull( offices.getData() );
    assertTrue( offices.getData().isEmpty() );
  }

  @Test
  void testFindOffice()
  {
    Integer id = 2;
    OfficeDO officeDo = new OfficeDO();
    officeDo.setId(id);

    when(officePersistence.findById(id)).thenReturn(Optional.of(officeDo));

    var offices = this.officeService.find( id );

    LOG.info( "Response: {}", offices );

    assertNotNull( offices );

    assertNotNull(offices.getBody());

    assertEquals(officeDo.getId(), offices.getBody().getId());

    verify(officePersistence, times(1)).findById(id);
  }

  @Test
  void testFindOfficeWhenIdNotPresent() {
    Integer id = 2;
    when(officePersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<OfficeDto> response = officeService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateOffice() {
    String agentName = "Tester";
    OfficeDto officeDto = new OfficeDto();

    OfficeDO officeDo = new OfficeDO();

    when(officePersistence.save(any(OfficeDO.class))).thenReturn(officeDo);

    GenericResponseDto<OfficeDto> response = officeService.create(officeDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(officeDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateOffice() {
    Integer id = 2;
    OfficeDto officeDto = new OfficeDto();
    officeDto.setId(id);

    OfficeDO officeDo = new OfficeDO();
    officeDo.setId(id);

    when(officePersistence.findById(id)).thenReturn(Optional.of(officeDo));

    when(officePersistence.save(any(OfficeDO.class))).thenReturn(officeDo);

    GenericResponseDto<Boolean> response = officeService.update(officeDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(officePersistence, times(1)).save(any(OfficeDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateOfficeNotFound() {
    Integer id = 2;
    OfficeDto officeDto = new OfficeDto();
    officeDto.setId(id);

    when(officePersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> officeService.update(officeDto));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteOffice() {
    Integer id = 2;

    OfficeDO officeDo = new OfficeDO();
    officeDo.setId(id);

    when(officePersistence.findById(id)).thenReturn(Optional.of(officeDo));

    doNothing().when(officePersistence).delete(any(OfficeDO.class));

    GenericResponseDto<Boolean> response = officeService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(officePersistence, times(1)).delete(any(OfficeDO.class));

  }

  @Test
  void testDeleteOfficeNotFound() {
    Integer id = 2;

    when(officePersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> officeService.delete(id));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

}
