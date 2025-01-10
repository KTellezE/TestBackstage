package com.novus.branch.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novus.branch.model.NationDO;

/**
 * Persistence interface of  de {@link com.novus.branch.model.NationDO}
 * 
 * @author username@novus.com
 */
@Repository
public interface NationPersistence extends JpaRepository<NationDO, Integer>
{
  // Agregar consultas personalizadas
}
