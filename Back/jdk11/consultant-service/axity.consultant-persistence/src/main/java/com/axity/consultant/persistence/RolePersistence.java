package com.axity.consultant.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.consultant.model.RoleDO;

/**
 * Persistence interface of  de {@link com.axity.consultant.model.RoleDO}
 * 
 * @author KevinTellez@axity.com
 */
@Repository
public interface RolePersistence extends JpaRepository<RoleDO, Integer>
{
  // Agregar consultas personalizadas
}
