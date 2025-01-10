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

import com.novus.branch.commons.dto.BranchDto;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;
import com.novus.branch.commons.response.PaginatedResponseDto;
import com.novus.branch.facade.impl.BranchFacadeImpl;
import com.novus.branch.service.BranchService;

/**
 * Class BranchFacadeTest
 * 
 * @author username@novus.com
 */
class BranchFacadeTest
{
  private BranchFacade branchFacade;
  private BranchService branchService;

  @BeforeEach
  public void setUp()
  {
    this.branchFacade = new BranchFacadeImpl();

    this.branchService = mock( BranchService.class );
    ReflectionTestUtils.setField( this.branchFacade, "branchService", this.branchService );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.BranchFacadeImpl#findBranchs(com.novus.branch.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindBranchs()
  {
    int pageSize = 20;

    var data = new ArrayList<BranchDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createBranch( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<BranchDto>( 0, pageSize, 50, data );
    when( this.branchService.findBranchs( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.branchFacade.findBranchs( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.BranchFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<BranchDto>( this.createBranch( 1 ) );
    when( this.branchService.find( anyInt() ) ).thenReturn( response );

    var result = this.branchFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.BranchFacadeImpl#create(com.novus.branch.commons.dto.BranchDto)}.
   */
  @Test
  void testCreate()
  {
    var branch = this.createBranch( 8 );
    var response = new GenericResponseDto<>( branch );
    when( this.branchService.create( any( BranchDto.class ) ) ).thenReturn( response );

    var result = this.branchFacade.create( branch );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.novus.branch.facade.impl.BranchFacadeImpl#update(com.novus.branch.commons.dto.BranchDto)}.
   */
  @Test
  void testUpdate()
  {
    var branch = this.createBranch( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.branchService.update( any( BranchDto.class ) ) ).thenReturn( response );
    var result = this.branchFacade.update( branch );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.novus.branch.facade.impl.BranchFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.branchService.delete( anyInt() ) ).thenReturn( response );

    var result = this.branchFacade.delete( 9 );
    assertNotNull( result );
  }

  private BranchDto createBranch( int i )
  {
    var branch = new BranchDto();
    branch.setId( i );
    return branch;
  }
}
