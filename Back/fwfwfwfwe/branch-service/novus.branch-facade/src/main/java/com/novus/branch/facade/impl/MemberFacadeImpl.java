package com.novus.branch.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novus.branch.commons.dto.MemberDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.MemberFacade;
import com.novus.branch.service.MemberService;

/**
 * Class MemberFacadeImpl
 * @author username@novus.com
 */
@Service
@Transactional
public class MemberFacadeImpl implements MemberFacade
{
  @Autowired
  private MemberService memberService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<MemberDto> findMembers( PaginatedRequestDto request )
  {
    return this.memberService.findMembers( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<MemberDto> find( Integer id )
  {
    return this.memberService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<MemberDto> create( MemberDto dto )
  {
    return this.memberService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( MemberDto dto )
  {
    return this.memberService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.memberService.delete( id );
  }
}
