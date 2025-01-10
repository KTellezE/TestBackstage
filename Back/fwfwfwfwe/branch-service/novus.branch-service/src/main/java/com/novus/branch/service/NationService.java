package com.novus.branch.service;

import java.util.List;

import com.novus.branch.commons.dto.NationDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;

/**
 * Interface NationService
 * 
 * @author username@novus.com
 */
public interface NationService
{

  /**
   * Method to get Nations
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<NationDto> findNations( PaginatedRequestDto request );

  /**
   * Method to get Nation by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<NationDto> find( Integer id );

  /**
   * Method to create a Nation
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<NationDto> create( NationDto dto );

  /**
   * Method to update a Nation
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( NationDto dto );

  /**
   * Method to delete a Nation
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
