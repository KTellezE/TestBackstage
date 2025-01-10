package com.novus.branch.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novus.branch.model.PositionDO;

/**
 * Persistence interface of  de {@link com.novus.branch.model.PositionDO}
 * 
 * @author username@novus.com
 */
@Repository
public interface PositionPersistence extends JpaRepository<PositionDO, Integer>
{
  // Agregar consultas personalizadas
}
