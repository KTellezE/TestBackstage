package com.novus.branch.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novus.branch.model.MemberDO;

/**
 * Persistence interface of  de {@link com.novus.branch.model.MemberDO}
 * 
 * @author username@novus.com
 */
@Repository
public interface MemberPersistence extends JpaRepository<MemberDO, Integer>
{
  // Agregar consultas personalizadas
}
