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
import com.novus.branch.commons.dto.PositionDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.PositionFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Position controller class
 * 
 * @author username@novus.com
 *
 */
@RestController
@RequestMapping("/api/positions")
@CrossOrigin
@Slf4j
public class PositionController
{
  @Autowired
  private PositionFacade positionFacade;

  

  /***
   * Method to get Position
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Position
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Positions", summary = "Method to get Positions paginated")
  public ResponseEntity<PaginatedResponseDto<PositionDto>> findPositions(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.positionFacade.findPositions( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Position by id
   * 
   * @param id The position Id
   * @return An registry of position or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Positions", summary = "Method to get Position by id")
  public ResponseEntity<GenericResponseDto<PositionDto>> findPosition( @PathVariable("id")
  Integer id )
  {
    var result = this.positionFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getPositionKey( Integer id )
  {
    String key = new StringBuilder().append( "Position.byPositionId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a Position
   * 
   * @param dto The Position object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Positions", summary = "Method to create a Position")
  public ResponseEntity<GenericResponseDto<PositionDto>> create( @RequestBody PositionDto dto )
  {
    var result = this.positionFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Position
   * 
   * @param id The Position Id
   * @param dto The Position object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Positions", summary = "Method to update a Position")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody PositionDto dto )
  {
    dto.setId( id );
    var result = this.positionFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Position
   * 
   * @param id The Position Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Positions", summary = "Method to delete a Position")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.positionFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Positions", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
