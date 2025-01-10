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

import com.novus.branch.commons.dto.PositionDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.MessageDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.model.PositionDO;
import com.novus.branch.persistence.PositionPersistence;
import com.novus.branch.service.PositionService;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class PositionServiceImpl
 * 
 * @author username@novus.com
 */
@Service
@Transactional
@Slf4j
public class PositionServiceImpl implements PositionService
{
  @Autowired
  private PositionPersistence positionPersistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<PositionDto> findPositions( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ) );

    var paged = this.positionPersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<PositionDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<PositionDto> find( Integer id )
  {
    GenericResponseDto<PositionDto> response = null;

    var optional = this.positionPersistence.findById( id );
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
  public GenericResponseDto<PositionDto> create( PositionDto dto )
  {

    PositionDO entity = new PositionDO();
    this.mapper.map( dto, entity );
    entity.setId(null);

    this.positionPersistence.save( entity );

    dto.setId(entity.getId());

    return new GenericResponseDto<>( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( PositionDto dto )
  {
    var optional = this.positionPersistence.findById( dto.getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.BRANCH_NOT_FOUND );
    }

    var entity = optional.get();
    
    
    entity.setName( dto.getName() );
    // TODO: Actualizar entity.Members (?) 

    this.positionPersistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    var optional = this.positionPersistence.findById( id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.BRANCH_NOT_FOUND );
    }

    var entity = optional.get();
    this.positionPersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private PositionDto transform( PositionDO entity )
  {
    PositionDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, PositionDto.class );
    }
    return dto;
  }
}
