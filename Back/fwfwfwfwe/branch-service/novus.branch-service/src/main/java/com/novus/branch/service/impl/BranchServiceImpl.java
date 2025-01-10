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

import com.novus.branch.commons.dto.BranchDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.MessageDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.model.BranchDO;
import com.novus.branch.persistence.BranchPersistence;
import com.novus.branch.service.BranchService;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class BranchServiceImpl
 * 
 * @author username@novus.com
 */
@Service
@Transactional
@Slf4j
public class BranchServiceImpl implements BranchService
{
  @Autowired
  private BranchPersistence branchPersistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<BranchDto> findBranchs( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ) );

    var paged = this.branchPersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<BranchDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<BranchDto> find( Integer id )
  {
    GenericResponseDto<BranchDto> response = null;

    var optional = this.branchPersistence.findById( id );
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
  public GenericResponseDto<BranchDto> create( BranchDto dto )
  {

    BranchDO entity = new BranchDO();
    this.mapper.map( dto, entity );
    entity.setId(null);

    this.branchPersistence.save( entity );

    dto.setId(entity.getId());

    return new GenericResponseDto<>( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( BranchDto dto )
  {
    var optional = this.branchPersistence.findById( dto.getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.BRANCH_NOT_FOUND );
    }

    var entity = optional.get();
    
    
    entity.setCity( dto.getCity() );
    entity.setPhone( dto.getPhone() );
    entity.setAddressLine1( dto.getAddressLine1() );
    entity.setAddressLine2( dto.getAddressLine2() );
    entity.setState( dto.getState() );
    // TODO: Actualizar entity.Nation (?) 
    entity.setPostalCode( dto.getPostalCode() );

    this.branchPersistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    var optional = this.branchPersistence.findById( id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.BRANCH_NOT_FOUND );
    }

    var entity = optional.get();
    this.branchPersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private BranchDto transform( BranchDO entity )
  {
    BranchDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, BranchDto.class );
    }
    return dto;
  }
}
