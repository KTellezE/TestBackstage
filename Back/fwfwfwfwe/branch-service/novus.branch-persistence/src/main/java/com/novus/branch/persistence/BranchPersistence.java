package com.novus.branch.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novus.branch.model.BranchDO;

/**
 * Persistence interface of  de {@link com.novus.branch.model.BranchDO}
 * 
 * @author username@novus.com
 */
@Repository
public interface BranchPersistence extends JpaRepository<BranchDO, Integer>
{
  // Agregar consultas personalizadas
}
