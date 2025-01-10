package com.novus.branch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novus.branch.commons.aspectj.JsonResponseInterceptor;
import com.novus.branch.commons.dto.NationDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.NationFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Nation controller class
 * 
 * @author username@novus.com
 *
 */
@RestController
@RequestMapping("/api/nations")
@CrossOrigin
@Slf4j
public class NationController
{
  @Autowired
  private NationFacade nationFacade;

  

  /***
   * Method to get Nation
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Nation
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Nations", summary = "Method to get Nations paginated")
  public ResponseEntity<PaginatedResponseDto<NationDto>> findNations(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.nationFacade.findNations( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Nation by id
   * 
   * @param id The nation Id
   * @return An registry of nation or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Nations", summary = "Method to get Nation by id")
  public ResponseEntity<GenericResponseDto<NationDto>> findNation( @PathVariable("id")
  Integer id )
  {
    var result = this.nationFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getNationKey( Integer id )
  {
    String key = new StringBuilder().append( "Nation.byNationId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a Nation
   * 
   * @param dto The Nation object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Nations", summary = "Method to create a Nation")
  public ResponseEntity<GenericResponseDto<NationDto>> create( @RequestBody NationDto dto )
  {
    var result = this.nationFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Nation
   * 
   * @param id The Nation Id
   * @param dto The Nation object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Nations", summary = "Method to update a Nation")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody NationDto dto )
  {
    dto.setId( id );
    var result = this.nationFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Nation
   * 
   * @param id The Nation Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Nations", summary = "Method to delete a Nation")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.nationFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Nations", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
