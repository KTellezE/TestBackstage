package com.novus.branch.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.novus.branch.commons.dto.MemberDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.impl.MemberFacadeImpl;
import com.novus.branch.service.MemberService;

/**
 * Class MemberFacadeTest
 * 
 * @author username@novus.com
 */
class MemberFacadeTest
{
  private MemberFacade memberFacade;
  private MemberService memberService;

  @BeforeEach
  public void setUp()
  {
    this.memberFacade = new MemberFacadeImpl();

    this.memberService = mock( MemberService.class );
    ReflectionTestUtils.setField( this.memberFacade, "memberService", this.memberService );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.MemberFacadeImpl#findMembers(com.novus.branch.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindMembers()
  {
    int pageSize = 20;

    var data = new ArrayList<MemberDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createMember( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<MemberDto>( 0, pageSize, 50, data );
    when( this.memberService.findMembers( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.memberFacade.findMembers( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.MemberFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<MemberDto>( this.createMember( 1 ) );
    when( this.memberService.find( anyInt() ) ).thenReturn( response );

    var result = this.memberFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.MemberFacadeImpl#create(com.novus.branch.commons.dto.MemberDto)}.
   */
  @Test
  void testCreate()
  {
    var member = this.createMember( 8 );
    var response = new GenericResponseDto<>( member );
    when( this.memberService.create( any( MemberDto.class ) ) ).thenReturn( response );

    var result = this.memberFacade.create( member );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.MemberFacadeImpl#update(com.novus.branch.commons.dto.MemberDto)}.
   */
  @Test
  void testUpdate()
  {
    var member = this.createMember( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.memberService.update( any( MemberDto.class ) ) ).thenReturn( response );
    var result = this.memberFacade.update( member );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.MemberFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.memberService.delete( anyInt() ) ).thenReturn( response );

    var result = this.memberFacade.delete( 9 );
    assertNotNull( result );
  }

  private MemberDto createMember( int i )
  {
    var member = new MemberDto();
    member.setId( i );
    return member;
  }
}
