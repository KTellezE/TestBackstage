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
 * Entity class of the table TBL_User
 * 
 * @author KevinTellez@axity.com
 */
@Entity
@Table(name = "TBL_User")

public class UserDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "cd_username")
  private String username;
  
  @Column(name = "cd_email")
  private String email;
  
  @Column(name = "nb_name")
  private String name;
  
  @Column(name = "nb_lastname")
  private String lastName;
  
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "TRL_UserRole", joinColumns = {
    @JoinColumn(name = "cd_user", referencedColumnName = "cd_id") }, inverseJoinColumns = {
        @JoinColumn(name = "cd_role", referencedColumnName = "cd_id") })
  private List<RoleDO> roles;
  
  
  

  
    
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
   * Gets Username 
   */
  public String getUsername()
  {
     return this.username;
  }

  /**
   * Sets Username
   * @param username
   */
  public void setUsername(String username)
  {
    this.username = username;
  }
    
  /** 
   * Gets Email 
   */
  public String getEmail()
  {
     return this.email;
  }

  /**
   * Sets Email
   * @param email
   */
  public void setEmail(String email)
  {
    this.email = email;
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
   * Gets LastName 
   */
  public String getLastName()
  {
     return this.lastName;
  }

  /**
   * Sets LastName
   * @param lastName
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
    
  /** 
   * Gets Roles 
   */
  public List<RoleDO> getRoles()
  {
     return this.roles;
  }

  /**
   * Sets Roles
   * @param roles
   */
  public void setRoles(List<RoleDO> roles)
  {
    this.roles = roles;
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
      UserDO that = (UserDO) object;

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
