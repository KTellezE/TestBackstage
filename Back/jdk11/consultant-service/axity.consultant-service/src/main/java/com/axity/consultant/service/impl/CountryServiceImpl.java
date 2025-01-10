package com.axity.consultant.service.impl;

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

import com.axity.consultant.commons.dto.CountryDto;
import com.axity.consultant.commons.enums.ErrorCode;
import com.axity.consultant.commons.exception.BusinessException;
import com.axity.consultant.commons.request.MessageDto;
import com.axity.consultant.commons.request.PaginatedRequestDto;
import com.axity.consultant.commons.response.GenericResponseDto;
import com.axity.consultant.commons.response.PaginatedResponseDto;
import com.axity.consultant.model.CountryDO;
import com.axity.consultant.model.QCountryDO;
import com.axity.consultant.persistence.CountryPersistence;
import com.axity.consultant.service.CountryService;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class CountryServiceImpl
 * 
 * @author KevinTellez@axity.com
 */
@Service
@Transactional
@Slf4j
public class CountryServiceImpl implements CountryService
{
  @Autowired
  private CountryPersistence countryPersistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<CountryDto> findCountrys( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ) );

    var paged = this.countryPersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<CountryDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<CountryDto> find( Integer id )
  {
    GenericResponseDto<CountryDto> response = null;

    var optional = this.countryPersistence.findById( id );
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
  public GenericResponseDto<CountryDto> create( CountryDto dto )
  {

    CountryDO entity = new CountryDO();
    this.mapper.map( dto, entity );
    entity.setId(null);

    this.countryPersistence.save( entity );

    dto.setId(entity.getId());

    return new GenericResponseDto<>( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( CountryDto dto )
  {
    var optional = this.countryPersistence.findById( dto.getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.CONSULTANT_NOT_FOUND );
    }

    var entity = optional.get();
    
    
    entity.setName( dto.getName() );
    // TODO: Actualizar entity.Territory (?) 
    // TODO: Actualizar entity.Offices (?) 

    this.countryPersistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    var optional = this.countryPersistence.findById( id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.CONSULTANT_NOT_FOUND );
    }

    var entity = optional.get();
    this.countryPersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private CountryDto transform( CountryDO entity )
  {
    CountryDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, CountryDto.class );
    }
    return dto;
  }
}
