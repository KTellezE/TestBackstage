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

import com.novus.branch.commons.dto.NationDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.MessageDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.model.NationDO;
import com.novus.branch.persistence.NationPersistence;
import com.novus.branch.service.NationService;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class NationServiceImpl
 * 
 * @author username@novus.com
 */
@Service
@Transactional
@Slf4j
public class NationServiceImpl implements NationService
{
  @Autowired
  private NationPersistence nationPersistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<NationDto> findNations( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ) );

    var paged = this.nationPersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<NationDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<NationDto> find( Integer id )
  {
    GenericResponseDto<NationDto> response = null;

    var optional = this.nationPersistence.findById( id );
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
  public GenericResponseDto<NationDto> create( NationDto dto )
  {

    NationDO entity = new NationDO();
    this.mapper.map( dto, entity );
    entity.setId(null);

    this.nationPersistence.save( entity );

    dto.setId(entity.getId());

    return new GenericResponseDto<>( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( NationDto dto )
  {
    var optional = this.nationPersistence.findById( dto.getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.BRANCH_NOT_FOUND );
    }

    var entity = optional.get();
    
    
    entity.setName( dto.getName() );
    // TODO: Actualizar entity.Region (?) 
    // TODO: Actualizar entity.Branches (?) 

    this.nationPersistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    var optional = this.nationPersistence.findById( id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.BRANCH_NOT_FOUND );
    }

    var entity = optional.get();
    this.nationPersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private NationDto transform( NationDO entity )
  {
    NationDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, NationDto.class );
    }
    return dto;
  }
}
