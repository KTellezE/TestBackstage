package com.axity.consultant.facade;

import java.util.List;

import com.axity.consultant.commons.dto.CountryDto;
import com.axity.consultant.commons.request.PaginatedRequestDto;
import com.axity.consultant.commons.response.GenericResponseDto;
import com.axity.consultant.commons.response.PaginatedResponseDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface CountryFacade
 * 
 * @author KevinTellez@axity.com
 */
public interface CountryFacade
{
  /**
   * Method to get Countrys
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<CountryDto> findCountrys( PaginatedRequestDto request );

  /**
   * Method to get Country by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<CountryDto> find( Integer id );

  /**
   * Method to create a Country
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<CountryDto> create( CountryDto dto );

  /**
   * Method to update a Country
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( CountryDto dto );

  /**
   * Method to delete a Country
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
