package com.axity.consultant.commons.dto;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * User Transfer Object class
 * 
 * @author KevinTellez@axity.com
 */

public class UserDto implements Serializable
{
  private static final long serialVersionUID = 1L;

  
  @Schema(required = true, description = "Theid")
  private  Integer id;
  
  @Schema(required = true, description = "Theusername")
  private  String username;
  
  @Schema(required = true, description = "Theemail")
  @JsonIgnore
  private  String email;
  
  @Schema(required = true, description = "Thename")
  private  String name;
  
  @Schema(required = true, description = "Thelastname")
  private  String lastName;
  
  @Schema(required = true, description = "Theroles")
  private  List<RoleDto> roles;
  

  
    
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
  public List<RoleDto> getRoles()
  {
     return this.roles;
  }

  /**
   * Sets Roles
   * @param roles
   */
  public void setRoles(List<RoleDto> roles)
  {
    this.roles = roles;
  }
    
  

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    return gson.toJson( this );
  }
}
