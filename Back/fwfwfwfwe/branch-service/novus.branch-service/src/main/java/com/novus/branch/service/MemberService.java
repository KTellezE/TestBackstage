package com.novus.branch.service;

import java.util.List;

import com.novus.branch.commons.dto.MemberDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;

/**
 * Interface MemberService
 * 
 * @author username@novus.com
 */
public interface MemberService
{

  /**
   * Method to get Members
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<MemberDto> findMembers( PaginatedRequestDto request );

  /**
   * Method to get Member by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<MemberDto> find( Integer id );

  /**
   * Method to create a Member
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<MemberDto> create( MemberDto dto );

  /**
   * Method to update a Member
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( MemberDto dto );

  /**
   * Method to delete a Member
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
