package com.novus.branch.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novus.branch.commons.dto.BranchDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.BranchFacade;
import com.novus.branch.service.BranchService;

/**
 * Class BranchFacadeImpl
 * @author username@novus.com
 */
@Service
@Transactional
public class BranchFacadeImpl implements BranchFacade
{
  @Autowired
  private BranchService branchService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<BranchDto> findBranchs( PaginatedRequestDto request )
  {
    return this.branchService.findBranchs( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<BranchDto> find( Integer id )
  {
    return this.branchService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<BranchDto> create( BranchDto dto )
  {
    return this.branchService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( BranchDto dto )
  {
    return this.branchService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.branchService.delete( id );
  }
}
