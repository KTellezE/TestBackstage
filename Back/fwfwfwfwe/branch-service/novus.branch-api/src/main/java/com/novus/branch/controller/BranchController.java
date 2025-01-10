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
import com.novus.branch.commons.dto.BranchDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.BranchFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Branch controller class
 * 
 * @author username@novus.com
 *
 */
@RestController
@RequestMapping("/api/branches")
@CrossOrigin
@Slf4j
public class BranchController
{
  @Autowired
  private BranchFacade branchFacade;

  

  /***
   * Method to get Branch
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Branch
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Branchs", summary = "Method to get Branchs paginated")
  public ResponseEntity<PaginatedResponseDto<BranchDto>> findBranchs(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.branchFacade.findBranchs( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Branch by id
   * 
   * @param id The branch Id
   * @return An registry of branch or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Branchs", summary = "Method to get Branch by id")
  public ResponseEntity<GenericResponseDto<BranchDto>> findBranch( @PathVariable("id")
  Integer id )
  {
    var result = this.branchFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getBranchKey( Integer id )
  {
    String key = new StringBuilder().append( "Branch.byBranchId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a Branch
   * 
   * @param dto The Branch object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Branchs", summary = "Method to create a Branch")
  public ResponseEntity<GenericResponseDto<BranchDto>> create( @RequestBody BranchDto dto )
  {
    var result = this.branchFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Branch
   * 
   * @param id The Branch Id
   * @param dto The Branch object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Branchs", summary = "Method to update a Branch")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody BranchDto dto )
  {
    dto.setId( id );
    var result = this.branchFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Branch
   * 
   * @param id The Branch Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Branchs", summary = "Method to delete a Branch")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.branchFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Branchs", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
