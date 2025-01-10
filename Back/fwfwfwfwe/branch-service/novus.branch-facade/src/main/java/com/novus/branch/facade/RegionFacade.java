package com.novus.branch.facade;

import java.util.List;

import com.novus.branch.commons.dto.RegionDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface RegionFacade
 * 
 * @author username@novus.com
 */
public interface RegionFacade
{
  /**
   * Method to get Regions
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<RegionDto> findRegions( PaginatedRequestDto request );

  /**
   * Method to get Region by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<RegionDto> find( Integer id );

  /**
   * Method to create a Region
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<RegionDto> create( RegionDto dto );

  /**
   * Method to update a Region
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( RegionDto dto );

  /**
   * Method to delete a Region
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
