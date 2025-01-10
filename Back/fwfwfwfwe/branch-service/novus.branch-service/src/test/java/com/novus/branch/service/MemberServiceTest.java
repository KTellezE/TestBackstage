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

import com.novus.branch.persistence.MemberPersistence;
import com.novus.branch.model.MemberDO;
import com.novus.branch.commons.dto.MemberDto;
import com.novus.branch.commons.enums.ErrorCode;
import com.novus.branch.commons.exception.BusinessException;
import com.novus.branch.commons.request.PaginatedRequestDto;
import com.novus.branch.commons.response.GenericResponseDto;

/**
 * Class MemberServiceTest
 * 
 * @author username@novus.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class MemberServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( MemberServiceTest.class );

  @Autowired
  private MemberService memberService;

  @MockBean
  private MemberPersistence memberPersistence;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindMembers()
  {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    Page<MemberDO> mockPage = new PageImpl<>(new ArrayList<>());
    when(memberPersistence.findAll(any(Pageable.class))).thenReturn(mockPage);
    var members = this.memberService.findMembers( request );

    LOG.info( "Response: {}", members );

    assertNotNull( members );
    assertNotNull( members.getData() );
    assertTrue( members.getData().isEmpty() );
  }

  @Test
  void testFindMember()
  {
    Integer id = 2;
    MemberDO memberDo = new MemberDO();
    memberDo.setId(id);

    when(memberPersistence.findById(id)).thenReturn(Optional.of(memberDo));

    var members = this.memberService.find( id );

    LOG.info( "Response: {}", members );

    assertNotNull( members );

    assertNotNull(members.getBody());

    assertEquals(memberDo.getId(), members.getBody().getId());

    verify(memberPersistence, times(1)).findById(id);
  }

  @Test
  void testFindMemberWhenIdNotPresent() {
    Integer id = 2;
    when(memberPersistence.findById(id)).thenReturn(Optional.empty());

    GenericResponseDto<MemberDto> response = memberService.find(id);

    // Assert
    assertNull(response);
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreateMember() {
    String agentName = "Tester";
    MemberDto memberDto = new MemberDto();

    MemberDO memberDo = new MemberDO();

    when(memberPersistence.save(any(MemberDO.class))).thenReturn(memberDo);

    GenericResponseDto<MemberDto> response = memberService.create(memberDto);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(memberDto.getClass(), response.getBody().getClass());
  }

  @Test
  // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateMember() {
    Integer id = 2;
    MemberDto memberDto = new MemberDto();
    memberDto.setId(id);

    MemberDO memberDo = new MemberDO();
    memberDo.setId(id);

    when(memberPersistence.findById(id)).thenReturn(Optional.of(memberDo));

    when(memberPersistence.save(any(MemberDO.class))).thenReturn(memberDo);

    GenericResponseDto<Boolean> response = memberService.update(memberDto);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody());

    verify(memberPersistence, times(1)).save(any(MemberDO.class));

  }

  @Test
    // TODO: Actualizar la prueba de acuerdo a la entidad
  void testUpdateMemberNotFound() {
    Integer id = 2;
    MemberDto memberDto = new MemberDto();
    memberDto.setId(id);

    when(memberPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> memberService.update(memberDto));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

  @Test
  void testDeleteMember() {
    Integer id = 2;

    MemberDO memberDo = new MemberDO();
    memberDo.setId(id);

    when(memberPersistence.findById(id)).thenReturn(Optional.of(memberDo));

    doNothing().when(memberPersistence).delete(any(MemberDO.class));

    GenericResponseDto<Boolean> response = memberService.delete(id);

    LOG.info( "Response: {}", response );
    assertNotNull(response);

    assertTrue(response.getBody()); //

    verify(memberPersistence, times(1)).delete(any(MemberDO.class));

  }

  @Test
  void testDeleteMemberNotFound() {
    Integer id = 2;

    when(memberPersistence.findById(id)).thenReturn(Optional.empty());

    BusinessException exception = assertThrows(BusinessException.class,
            () -> memberService.delete(id));
    assertEquals(ErrorCode.BRANCH_NOT_FOUND.getCode(), exception.getCode());

  }

}
