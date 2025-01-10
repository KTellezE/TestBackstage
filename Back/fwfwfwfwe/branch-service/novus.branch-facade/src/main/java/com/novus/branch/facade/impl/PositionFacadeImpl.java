package com.novus.branch.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novus.branch.commons.dto.PositionDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.PositionFacade;
import com.novus.branch.service.PositionService;

/**
 * Class PositionFacadeImpl
 * @author username@novus.com
 */
@Service
@Transactional
public class PositionFacadeImpl implements PositionFacade
{
  @Autowired
  private PositionService positionService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<PositionDto> findPositions( PaginatedRequestDto request )
  {
    return this.positionService.findPositions( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<PositionDto> find( Integer id )
  {
    return this.positionService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<PositionDto> create( PositionDto dto )
  {
    return this.positionService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( PositionDto dto )
  {
    return this.positionService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.positionService.delete( id );
  }
}
