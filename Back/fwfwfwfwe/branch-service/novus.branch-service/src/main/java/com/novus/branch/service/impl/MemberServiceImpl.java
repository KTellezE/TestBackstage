package com.novus.branch.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novus.branch.commons.dto.MemberDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.MessageDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.model.MemberDO;
import com.novus.branch.persistence.MemberPersistence;
import com.novus.branch.service.MemberService;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class MemberServiceImpl
 * 
 * @author username@novus.com
 */
@Service
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService
{
  @Autowired
  private MemberPersistence memberPersistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<MemberDto> findMembers( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ) );

    var paged = this.memberPersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<MemberDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<MemberDto> find( Integer id )
  {
    GenericResponseDto<MemberDto> response = null;

    var optional = this.memberPersistence.findById( id );
    if( optional.isPresent() )
    {
      response = new GenericResponseDto<>();
      response.setBody( this.transform( optional.get() ) );
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<MemberDto> create( MemberDto dto )
  {

    MemberDO entity = new MemberDO();
    this.mapper.map( dto, entity );
    entity.setId(null);

    this.memberPersistence.save( entity );

    dto.setId(entity.getId());

    return new GenericResponseDto<>( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( MemberDto dto )
  {
    var optional = this.memberPersistence.findById( dto.getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.BRANCH_NOT_FOUND );
    }

    var entity = optional.get();
    
    
    entity.setUsername( dto.getUsername() );
    entity.setEmail( dto.getEmail() );
    entity.setName( dto.getName() );
    entity.setLastName( dto.getLastName() );
    // TODO: Actualizar entity.Positions (?) 

    this.memberPersistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    var optional = this.memberPersistence.findById( id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.BRANCH_NOT_FOUND );
    }

    var entity = optional.get();
    this.memberPersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private MemberDto transform( MemberDO entity )
  {
    MemberDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, MemberDto.class );
    }
    return dto;
  }
}
