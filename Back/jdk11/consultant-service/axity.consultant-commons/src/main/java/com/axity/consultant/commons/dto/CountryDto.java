package com.axity.consultant.commons.dto;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Country Transfer Object class
 * 
 * @author KevinTellez@axity.com
 */

public class CountryDto implements Serializable
{
  private static final long serialVersionUID = 1L;

  
  @Schema(required = true, description = "Theid")
  private  Integer id;
  
  @Schema(required = true, description = "Thename")
  private  String name;
  
  @Schema(required = true, description = "Theterritory")
  private  TerritoryDto territory;
  
  @Schema(required = true, description = "Theoffices")
  @JsonIgnore
  private transient List<OfficeDto> offices;
  

  
    
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
   * Gets Territory 
   */
  public TerritoryDto getTerritory()
  {
     return this.territory;
  }

  /**
   * Sets Territory
   * @param territory
   */
  public void setTerritory(TerritoryDto territory)
  {
    this.territory = territory;
  }
    
  /** 
   * Gets Offices 
   */
  public List<OfficeDto> getOffices()
  {
     return this.offices;
  }

  /**
   * Sets Offices
   * @param offices
   */
  public void setOffices(List<OfficeDto> offices)
  {
    this.offices = offices;
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
