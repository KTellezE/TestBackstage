package com.novus.branch.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.novus.branch.commons.dto.MemberDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.MemberFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Member Controller Test class
 * 
 * @author username@novus.com
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MemberControllerTest
{

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MemberFacade memberFacade;
  
  
  
  @BeforeEach
  void setUp() {
    
  }

  /**
   * Test method for {@link com.novus.branch.controller.MemberController#findMembers(int, int)}.
   * 
   * @throws Exception
   */
  @Test
  void testFindMembers() throws Exception
  {
    int pageSize = 20;

    var data = new ArrayList<MemberDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createMember( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<MemberDto>( 0, pageSize, 50, data );
    when( this.memberFacade.findMembers( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/members?limit=20&offset=0" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.page" ).value( "0" ) )
        .andExpect( jsonPath( "$.size" ).value( "20" ) )
        .andExpect( jsonPath( "$.data" ).isArray() )
        .andExpect( jsonPath( "$.data[0].id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.controller.MemberController#findMember(java.lang.String)}.
   * 
   * @throws Exception
   */
  @Test
  void testFindMember() throws Exception
  {
    var member = this.createMember( 1 );
    var generic = new GenericResponseDto<>(member);
    when( this.memberFacade.find( anyInt() ) ).thenReturn( generic );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/members/1" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.controller.MemberController#create(com.novus.branch.commons.dto.MemberDto)}.
   * 
   * @throws Exception
   */
  @Test
  void testCreate() throws Exception
  {
    var member = this.createMember( 9 );
    var generic = new GenericResponseDto<>(member);
    when(this.memberFacade.create( any( MemberDto.class ) )).thenReturn( generic );
    
    Gson gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.post( "/api/members"  )
            .content(gson.toJson(member))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isCreated() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.id" ).value( 9 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.controller.MemberController#update(java.lang.String, com.novus.branch.commons.dto.MemberDto)}.
   * 
   * @throws Exception
   */
  @Test
  void testUpdate() throws Exception
  {
    var member = this.createMember( 1 );
    var generic = new GenericResponseDto<>(true);
    when(this.memberFacade.update( any( MemberDto.class ) )).thenReturn( generic );
    
    Gson gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.put( "/api/members/1"  )
            .content(gson.toJson(member))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( "true" ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.controller.MemberController#delete(java.lang.String)}.
   * 
   * @throws Exception
   */
  @Test
  void testDelete() throws Exception
  {
    var generic = new GenericResponseDto<>(true);
    when(this.memberFacade.delete( anyInt() )).thenReturn( generic );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.delete( "/api/members/1"  )
            .accept( MediaType.APPLICATION_JSON ))
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( "true" ) ).andReturn();

    assertNotNull( result );
  }


    /**
   * Test method for {@link com.novus.branch.controller.MemberController#ping()}.
   * 
   * @throws Exception
   */
  @Test
  void testPing() throws Exception
  {

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/members/ping" )
    .contentType( MediaType.APPLICATION_JSON )).andReturn();

    assertNotNull( result );
  }

  private MemberDto createMember( int i )
  {
    var member = new MemberDto();
    member.setId( i );
    return member;
  }
}
