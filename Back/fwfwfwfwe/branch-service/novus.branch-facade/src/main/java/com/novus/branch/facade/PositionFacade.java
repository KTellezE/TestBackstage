package com.novus.branch.facade;

import java.util.List;

import com.novus.branch.commons.dto.PositionDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface PositionFacade
 * 
 * @author username@novus.com
 */
public interface PositionFacade
{
  /**
   * Method to get Positions
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<PositionDto> findPositions( PaginatedRequestDto request );

  /**
   * Method to get Position by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<PositionDto> find( Integer id );

  /**
   * Method to create a Position
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<PositionDto> create( PositionDto dto );

  /**
   * Method to update a Position
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( PositionDto dto );

  /**
   * Method to delete a Position
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
