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

import com.axity.consultant.commons.dto.UserDto;
import com.axity.consultant.commons.enums.ErrorCode;
import com.axity.consultant.commons.exception.BusinessException;
import com.axity.consultant.commons.request.MessageDto;
import com.axity.consultant.commons.request.PaginatedRequestDto;
import com.axity.consultant.commons.response.GenericResponseDto;
import com.axity.consultant.commons.response.PaginatedResponseDto;
import com.axity.consultant.model.UserDO;
import com.axity.consultant.model.QUserDO;
import com.axity.consultant.persistence.UserPersistence;
import com.axity.consultant.service.UserService;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class UserServiceImpl
 * 
 * @author KevinTellez@axity.com
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService
{
  @Autowired
  private UserPersistence userPersistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<UserDto> findUsers( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ) );

    var paged = this.userPersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<UserDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<UserDto> find( Integer id )
  {
    GenericResponseDto<UserDto> response = null;

    var optional = this.userPersistence.findById( id );
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
  public GenericResponseDto<UserDto> create( UserDto dto )
  {

    UserDO entity = new UserDO();
    this.mapper.map( dto, entity );
    entity.setId(null);

    this.userPersistence.save( entity );

    dto.setId(entity.getId());

    return new GenericResponseDto<>( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( UserDto dto )
  {
    var optional = this.userPersistence.findById( dto.getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.CONSULTANT_NOT_FOUND );
    }

    var entity = optional.get();
    
    
    entity.setUsername( dto.getUsername() );
    entity.setEmail( dto.getEmail() );
    entity.setName( dto.getName() );
    entity.setLastName( dto.getLastName() );
    // TODO: Actualizar entity.Roles (?) 

    this.userPersistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    var optional = this.userPersistence.findById( id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.CONSULTANT_NOT_FOUND );
    }

    var entity = optional.get();
    this.userPersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private UserDto transform( UserDO entity )
  {
    UserDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, UserDto.class );
    }
    return dto;
  }
}
