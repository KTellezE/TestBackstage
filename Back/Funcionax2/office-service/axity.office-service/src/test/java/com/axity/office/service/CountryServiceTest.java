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

import com.axity.office.persistence.CountryPersistence;
import com.axity.office.model.CountryDO;
import com.axity.office.commons.dto.CountryDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;

/**
 * Class CountryServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class CountryServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( CountryServiceTest.class );

  @Autowired
  private CountryService countryService;

  @MockBean
  private CountryPersistence countryPersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindCountrys()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<CountryDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(countryPersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var countrys = this.countryService.findCountrys( request );

    LOG.info( "Response: {}", countrys );

    assertNotNull( countrys );
    assertNotNull( countrys.getData() );
    assertTrue( countrys.getData().isEmpty() );
  }

  @Test
  void testFindCountry()
  {
    Integer id = 2;
    CountryDO countryDo = new CountryDO();
    countryDo.setId(id);

    when(countryPersistence.findById(id)).thenReturn(Optional.of(countryDo));

    var countrys = this.countryService.find( id );

    LOG.info( "Response: {}", countrys );

    assertNotNull( countrys );

    assertNotNull(countrys.getBody());

    assertEquals(countryDo.getId(), countrys.getBody().getId());

    verify(countryPersistence, times(1)).findById(id);
  }

  @Test
  void testFindCountryWhenIdNotPresent() {
    Integer id = 2;
    when(countryPersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<CountryDto> response = countryService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateCountry() {
    String agentName = "Tester";
    CountryDto countryDto = new CountryDto();

    CountryDO countryDo = new CountryDO();

    when(countryPersistence.save(any(CountryDO.class))).thenReturn(countryDo);

    GenericResponseDto<CountryDto> response = countryService.create(countryDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(countryDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateCountry() {
    Integer id = 2;
    CountryDto countryDto = new CountryDto();
    countryDto.setId(id);

    CountryDO countryDo = new CountryDO();
    countryDo.setId(id);

    when(countryPersistence.findById(id)).thenReturn(Optional.of(countryDo));

    when(countryPersistence.save(any(CountryDO.class))).thenReturn(countryDo);

    GenericResponseDto<Boolean> response = countryService.update(countryDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(countryPersistence, times(1)).save(any(CountryDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateCountryNotFound() {
    Integer id = 2;
    CountryDto countryDto = new CountryDto();
    countryDto.setId(id);

    when(countryPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> countryService.update(countryDto));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteCountry() {
    Integer id = 2;

    CountryDO countryDo = new CountryDO();
    countryDo.setId(id);

    when(countryPersistence.findById(id)).thenReturn(Optional.of(countryDo));

    doNothing().when(countryPersistence).delete(any(CountryDO.class));

    GenericResponseDto<Boolean> response = countryService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(countryPersistence, times(1)).delete(any(CountryDO.class));

  }

  @Test
  void testDeleteCountryNotFound() {
    Integer id = 2;

    when(countryPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> countryService.delete(id));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), exception.getCode());

  }

}
