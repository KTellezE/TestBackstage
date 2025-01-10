package com.novus.branch.service;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.novus.branch.persistence.BranchPersistence;
import com.novus.branch.model.BranchDO;
import com.novus.branch.commons.dto.BranchDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;

/**
 * Class BranchServiceTest
 * 
 * @author username@novus.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class BranchServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( BranchServiceTest.class );

  @Autowired
  private BranchService branchService;

  @MockBean
  private BranchPersistence branchPersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindBranchs()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<BranchDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(branchPersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var branchs = this.branchService.findBranchs( request );

    LOG.info( "Response: {}", branchs );

    assertNotNull( branchs );
    assertNotNull( branchs.getData() );
    assertTrue( branchs.getData().isEmpty() );
  }

  @Test
  void testFindBranch()
  {
    Integer id = 2;
    BranchDO branchDo = new BranchDO();
    branchDo.setId(id);

    when(branchPersistence.findById(id)).thenReturn(Optional.of(branchDo));

    var branchs = this.branchService.find( id );

    LOG.info( "Response: {}", branchs );

    assertNotNull( branchs );

    assertNotNull(branchs.getBody());

    assertEquals(branchDo.getId(), branchs.getBody().getId());

    verify(branchPersistence, times(1)).findById(id);
  }

  @Test
  void testFindBranchWhenIdNotPresent() {
    Integer id = 2;
    when(branchPersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<BranchDto> response = branchService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateBranch() {
    String agentName = "Tester";
    BranchDto branchDto = new BranchDto();

    BranchDO branchDo = new BranchDO();

    when(branchPersistence.save(any(BranchDO.class))).thenReturn(branchDo);

    GenericResponseDto<BranchDto> response = branchService.create(branchDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(branchDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateBranch() {
    Integer id = 2;
    BranchDto branchDto = new BranchDto();
    branchDto.setId(id);

    BranchDO branchDo = new BranchDO();
    branchDo.setId(id);

    when(branchPersistence.findById(id)).thenReturn(Optional.of(branchDo));

    when(branchPersistence.save(any(BranchDO.class))).thenReturn(branchDo);

    GenericResponseDto<Boolean> response = branchService.update(branchDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(branchPersistence, times(1)).save(any(BranchDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateBranchNotFound() {
    Integer id = 2;
    BranchDto branchDto = new BranchDto();
    branchDto.setId(id);

    when(branchPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> branchService.update(branchDto));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteBranch() {
    Integer id = 2;

    BranchDO branchDo = new BranchDO();
    branchDo.setId(id);

    when(branchPersistence.findById(id)).thenReturn(Optional.of(branchDo));

    doNothing().when(branchPersistence).delete(any(BranchDO.class));

    GenericResponseDto<Boolean> response = branchService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(branchPersistence, times(1)).delete(any(BranchDO.class));

  }

  @Test
  void testDeleteBranchNotFound() {
    Integer id = 2;

    when(branchPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> branchService.delete(id));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

}
