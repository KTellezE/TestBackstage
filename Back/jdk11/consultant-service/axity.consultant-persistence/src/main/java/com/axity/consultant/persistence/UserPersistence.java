package com.axity.consultant.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.consultant.model.UserDO;

/**
 * Persistence interface of  de {@link com.axity.consultant.model.UserDO}
 * 
 * @author KevinTellez@axity.com
 */
@Repository
public interface UserPersistence extends JpaRepository<UserDO, Integer>
{
  // Agregar consultas personalizadas
}
