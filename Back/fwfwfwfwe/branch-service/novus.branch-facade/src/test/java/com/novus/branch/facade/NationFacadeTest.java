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

import com.novus.branch.commons.dto.NationDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.impl.NationFacadeImpl;
import com.novus.branch.service.NationService;

/**
 * Class NationFacadeTest
 * 
 * @author username@novus.com
 */
class NationFacadeTest
{
  private NationFacade nationFacade;
  private NationService nationService;

  @BeforeEach
  public void setUp()
  {
    this.nationFacade = new NationFacadeImpl();

    this.nationService = mock( NationService.class );
    ReflectionTestUtils.setField( this.nationFacade, "nationService", this.nationService );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.NationFacadeImpl#findNations(com.novus.branch.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindNations()
  {
    int pageSize = 20;

    var data = new ArrayList<NationDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createNation( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<NationDto>( 0, pageSize, 50, data );
    when( this.nationService.findNations( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.nationFacade.findNations( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.NationFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<NationDto>( this.createNation( 1 ) );
    when( this.nationService.find( anyInt() ) ).thenReturn( response );

    var result = this.nationFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.NationFacadeImpl#create(com.novus.branch.commons.dto.NationDto)}.
   */
  @Test
  void testCreate()
  {
    var nation = this.createNation( 8 );
    var response = new GenericResponseDto<>( nation );
    when( this.nationService.create( any( NationDto.class ) ) ).thenReturn( response );

    var result = this.nationFacade.create( nation );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.NationFacadeImpl#update(com.novus.branch.commons.dto.NationDto)}.
   */
  @Test
  void testUpdate()
  {
    var nation = this.createNation( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.nationService.update( any( NationDto.class ) ) ).thenReturn( response );
    var result = this.nationFacade.update( nation );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.NationFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.nationService.delete( anyInt() ) ).thenReturn( response );

    var result = this.nationFacade.delete( 9 );
    assertNotNull( result );
  }

  private NationDto createNation( int i )
  {
    var nation = new NationDto();
    nation.setId( i );
    return nation;
  }
}
