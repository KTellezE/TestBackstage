package com.axity.consultant.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.consultant.model.OfficeDO;

/**
 * Persistence interface of  de {@link com.axity.consultant.model.OfficeDO}
 * 
 * @author KevinTellez@axity.com
 */
@Repository
public interface OfficePersistence extends JpaRepository<OfficeDO, Integer>
{
  // Agregar consultas personalizadas
}
