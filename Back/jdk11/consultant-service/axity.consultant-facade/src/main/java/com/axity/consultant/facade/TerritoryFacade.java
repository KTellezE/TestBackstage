package com.axity.consultant.facade;

import java.util.List;

import com.axity.consultant.commons.dto.TerritoryDto;
import com.axity.consultant.commons.request.PaginatedRequestDto;
import com.axity.consultant.commons.response.GenericResponseDto;
import com.axity.consultant.commons.response.PaginatedResponseDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface TerritoryFacade
 * 
 * @author KevinTellez@axity.com
 */
public interface TerritoryFacade
{
  /**
   * Method to get Territorys
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<TerritoryDto> findTerritorys( PaginatedRequestDto request );

  /**
   * Method to get Territory by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<TerritoryDto> find( Integer id );

  /**
   * Method to create a Territory
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<TerritoryDto> create( TerritoryDto dto );

  /**
   * Method to update a Territory
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( TerritoryDto dto );

  /**
   * Method to delete a Territory
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
