package com.novus.branch.service;

import java.util.List;

import com.novus.branch.commons.dto.BranchDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;

/**
 * Interface BranchService
 * 
 * @author username@novus.com
 */
public interface BranchService
{

  /**
   * Method to get Branchs
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<BranchDto> findBranchs( PaginatedRequestDto request );

  /**
   * Method to get Branch by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<BranchDto> find( Integer id );

  /**
   * Method to create a Branch
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<BranchDto> create( BranchDto dto );

  /**
   * Method to update a Branch
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( BranchDto dto );

  /**
   * Method to delete a Branch
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
