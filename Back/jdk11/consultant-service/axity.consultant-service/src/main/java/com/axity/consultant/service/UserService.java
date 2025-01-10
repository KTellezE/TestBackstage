package com.axity.consultant.service;

import java.util.List;

import com.axity.consultant.commons.dto.UserDto;
import com.axity.consultant.commons.request.PaginatedRequestDto;
import com.axity.consultant.commons.response.GenericResponseDto;
import com.axity.consultant.commons.response.PaginatedResponseDto;

/**
 * Interface UserService
 * 
 * @author KevinTellez@axity.com
 */
public interface UserService
{

  /**
   * Method to get Users
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<UserDto> findUsers( PaginatedRequestDto request );

  /**
   * Method to get User by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<UserDto> find( Integer id );

  /**
   * Method to create a User
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<UserDto> create( UserDto dto );

  /**
   * Method to update a User
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( UserDto dto );

  /**
   * Method to delete a User
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
