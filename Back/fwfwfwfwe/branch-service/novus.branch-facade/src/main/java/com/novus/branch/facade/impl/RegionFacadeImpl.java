package com.novus.branch.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novus.branch.commons.dto.RegionDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.RegionFacade;
import com.novus.branch.service.RegionService;

/**
 * Class RegionFacadeImpl
 * @author username@novus.com
 */
@Service
@Transactional
public class RegionFacadeImpl implements RegionFacade
{
  @Autowired
  private RegionService regionService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<RegionDto> findRegions( PaginatedRequestDto request )
  {
    return this.regionService.findRegions( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<RegionDto> find( Integer id )
  {
    return this.regionService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<RegionDto> create( RegionDto dto )
  {
    return this.regionService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( RegionDto dto )
  {
    return this.regionService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.regionService.delete( id );
  }
}
