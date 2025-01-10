package com.novus.branch.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novus.branch.model.RegionDO;

/**
 * Persistence interface of  de {@link com.novus.branch.model.RegionDO}
 * 
 * @author username@novus.com
 */
@Repository
public interface RegionPersistence extends JpaRepository<RegionDO, Integer>
{
  // Agregar consultas personalizadas
}
