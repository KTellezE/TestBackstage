package com.axity.consultant.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axity.consultant.commons.dto.RoleDto;
import com.axity.consultant.commons.request.PaginatedRequestDto;
import com.axity.consultant.commons.response.GenericResponseDto;
import com.axity.consultant.commons.response.PaginatedResponseDto;
import com.axity.consultant.facade.RoleFacade;
import com.axity.consultant.service.RoleService;

/**
 * Class RoleFacadeImpl
 * @author KevinTellez@axity.com
 */
@Service
@Transactional
public class RoleFacadeImpl implements RoleFacade
{
  @Autowired
  private RoleService roleService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<RoleDto> findRoles( PaginatedRequestDto request )
  {
    return this.roleService.findRoles( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<RoleDto> find( Integer id )
  {
    return this.roleService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<RoleDto> create( RoleDto dto )
  {
    return this.roleService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( RoleDto dto )
  {
    return this.roleService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.roleService.delete( id );
  }
}
