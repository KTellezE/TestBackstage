package com.axity.consultant.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.consultant.model.TerritoryDO;

/**
 * Persistence interface of  de {@link com.axity.consultant.model.TerritoryDO}
 * 
 * @author KevinTellez@axity.com
 */
@Repository
public interface TerritoryPersistence extends JpaRepository<TerritoryDO, Integer>
{
  // Agregar consultas personalizadas
}
