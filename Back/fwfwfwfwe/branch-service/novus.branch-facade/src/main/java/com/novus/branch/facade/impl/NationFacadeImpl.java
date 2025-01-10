package com.novus.branch.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novus.branch.commons.dto.NationDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.NationFacade;
import com.novus.branch.service.NationService;

/**
 * Class NationFacadeImpl
 * @author username@novus.com
 */
@Service
@Transactional
public class NationFacadeImpl implements NationFacade
{
  @Autowired
  private NationService nationService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<NationDto> findNations( PaginatedRequestDto request )
  {
    return this.nationService.findNations( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<NationDto> find( Integer id )
  {
    return this.nationService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<NationDto> create( NationDto dto )
  {
    return this.nationService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( NationDto dto )
  {
    return this.nationService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.nationService.delete( id );
  }
}
