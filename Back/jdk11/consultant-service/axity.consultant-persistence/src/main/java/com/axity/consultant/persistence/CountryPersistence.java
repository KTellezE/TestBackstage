package com.axity.consultant.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.consultant.model.CountryDO;

/**
 * Persistence interface of  de {@link com.axity.consultant.model.CountryDO}
 * 
 * @author KevinTellez@axity.com
 */
@Repository
public interface CountryPersistence extends JpaRepository<CountryDO, Integer>
{
  // Agregar consultas personalizadas
}
