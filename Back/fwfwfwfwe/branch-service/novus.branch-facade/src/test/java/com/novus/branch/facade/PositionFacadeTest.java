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

import com.novus.branch.commons.dto.PositionDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.impl.PositionFacadeImpl;
import com.novus.branch.service.PositionService;

/**
 * Class PositionFacadeTest
 * 
 * @author username@novus.com
 */
class PositionFacadeTest
{
  private PositionFacade positionFacade;
  private PositionService positionService;

  @BeforeEach
  public void setUp()
  {
    this.positionFacade = new PositionFacadeImpl();

    this.positionService = mock( PositionService.class );
    ReflectionTestUtils.setField( this.positionFacade, "positionService", this.positionService );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.PositionFacadeImpl#findPositions(com.novus.branch.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindPositions()
  {
    int pageSize = 20;

    var data = new ArrayList<PositionDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createPosition( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<PositionDto>( 0, pageSize, 50, data );
    when( this.positionService.findPositions( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.positionFacade.findPositions( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.PositionFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<PositionDto>( this.createPosition( 1 ) );
    when( this.positionService.find( anyInt() ) ).thenReturn( response );

    var result = this.positionFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.PositionFacadeImpl#create(com.novus.branch.commons.dto.PositionDto)}.
   */
  @Test
  void testCreate()
  {
    var position = this.createPosition( 8 );
    var response = new GenericResponseDto<>( position );
    when( this.positionService.create( any( PositionDto.class ) ) ).thenReturn( response );

    var result = this.positionFacade.create( position );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.PositionFacadeImpl#update(com.novus.branch.commons.dto.PositionDto)}.
   */
  @Test
  void testUpdate()
  {
    var position = this.createPosition( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.positionService.update( any( PositionDto.class ) ) ).thenReturn( response );
    var result = this.positionFacade.update( position );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.PositionFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.positionService.delete( anyInt() ) ).thenReturn( response );

    var result = this.positionFacade.delete( 9 );
    assertNotNull( result );
  }

  private PositionDto createPosition( int i )
  {
    var position = new PositionDto();
    position.setId( i );
    return position;
  }
}
