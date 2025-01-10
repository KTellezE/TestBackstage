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

import com.novus.branch.commons.dto.RegionDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.impl.RegionFacadeImpl;
import com.novus.branch.service.RegionService;

/**
 * Class RegionFacadeTest
 * 
 * @author username@novus.com
 */
class RegionFacadeTest
{
  private RegionFacade regionFacade;
  private RegionService regionService;

  @BeforeEach
  public void setUp()
  {
    this.regionFacade = new RegionFacadeImpl();

    this.regionService = mock( RegionService.class );
    ReflectionTestUtils.setField( this.regionFacade, "regionService", this.regionService );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.RegionFacadeImpl#findRegions(com.novus.branch.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindRegions()
  {
    int pageSize = 20;

    var data = new ArrayList<RegionDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createRegion( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<RegionDto>( 0, pageSize, 50, data );
    when( this.regionService.findRegions( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.regionFacade.findRegions( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.RegionFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<RegionDto>( this.createRegion( 1 ) );
    when( this.regionService.find( anyInt() ) ).thenReturn( response );

    var result = this.regionFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.RegionFacadeImpl#create(com.novus.branch.commons.dto.RegionDto)}.
   */
  @Test
  void testCreate()
  {
    var region = this.createRegion( 8 );
    var response = new GenericResponseDto<>( region );
    when( this.regionService.create( any( RegionDto.class ) ) ).thenReturn( response );

    var result = this.regionFacade.create( region );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.RegionFacadeImpl#update(com.novus.branch.commons.dto.RegionDto)}.
   */
  @Test
  void testUpdate()
  {
    var region = this.createRegion( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.regionService.update( any( RegionDto.class ) ) ).thenReturn( response );
    var result = this.regionFacade.update( region );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.RegionFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.regionService.delete( anyInt() ) ).thenReturn( response );

    var result = this.regionFacade.delete( 9 );
    assertNotNull( result );
  }

  private RegionDto createRegion( int i )
  {
    var region = new RegionDto();
    region.setId( i );
    return region;
  }
}
