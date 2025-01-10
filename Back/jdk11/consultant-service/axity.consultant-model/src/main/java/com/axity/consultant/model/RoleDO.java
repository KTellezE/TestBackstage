package com.axity.consultant.model;

import java.io.Serializable;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import javax.persistence.Table;



/**
 * Entity class of the table TBL_Role
 * 
 * @author KevinTellez@axity.com
 */
@Entity
@Table(name = "TBL_Role")

public class RoleDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "nb_name")
  private String name;
  
  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private List<UserDO> users;
  
  

  
    
  /** 
   * Gets Id 
   */
  public Integer getId()
  {
     return this.id;
  }

  /**
   * Sets Id
   * @param id
   */
  public void setId(Integer id)
  {
    this.id = id;
  }
    
  /** 
   * Gets Name 
   */
  public String getName()
  {
     return this.name;
  }

  /**
   * Sets Name
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }
    
  /** 
   * Gets Users 
   */
  public List<UserDO> getUsers()
  {
     return this.users;
  }

  /**
   * Sets Users
   * @param users
   */
  public void setUsers(List<UserDO> users)
  {
    this.users = users;
  }
    
  

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      RoleDO that = (RoleDO) object;

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }

}
