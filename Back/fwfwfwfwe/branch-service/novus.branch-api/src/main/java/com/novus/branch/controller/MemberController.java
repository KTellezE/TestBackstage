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
import com.novus.branch.commons.dto.MemberDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.MemberFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Member controller class
 * 
 * @author username@novus.com
 *
 */
@RestController
@RequestMapping("/api/members")
@CrossOrigin
@Slf4j
public class MemberController
{
  @Autowired
  private MemberFacade memberFacade;

  

  /***
   * Method to get Member
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Member
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Members", summary = "Method to get Members paginated")
  public ResponseEntity<PaginatedResponseDto<MemberDto>> findMembers(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.memberFacade.findMembers( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Member by id
   * 
   * @param id The member Id
   * @return An registry of member or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Members", summary = "Method to get Member by id")
  public ResponseEntity<GenericResponseDto<MemberDto>> findMember( @PathVariable("id")
  Integer id )
  {
    var result = this.memberFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getMemberKey( Integer id )
  {
    String key = new StringBuilder().append( "Member.byMemberId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a Member
   * 
   * @param dto The Member object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Members", summary = "Method to create a Member")
  public ResponseEntity<GenericResponseDto<MemberDto>> create( @RequestBody MemberDto dto )
  {
    var result = this.memberFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Member
   * 
   * @param id The Member Id
   * @param dto The Member object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Members", summary = "Method to update a Member")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody MemberDto dto )
  {
    dto.setId( id );
    var result = this.memberFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Member
   * 
   * @param id The Member Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Members", summary = "Method to delete a Member")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.memberFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Members", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
