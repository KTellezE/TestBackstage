package com.axity.consultant.commons.dto;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Territory Transfer Object class
 * 
 * @author KevinTellez@axity.com
 */

public class TerritoryDto implements Serializable
{
  private static final long serialVersionUID = 1L;

  
  @Schema(required = true, description = "Theid")
  private  Integer id;
  
  @Schema(required = true, description = "Thename")
  private  String name;
  
  @Schema(required = true, description = "Thecountries")
  @JsonIgnore
  private transient List<CountryDto> countries;
  

  
    
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
   * Gets Countries 
   */
  public List<CountryDto> getCountries()
  {
     return this.countries;
  }

  /**
   * Sets Countries
   * @param countries
   */
  public void setCountries(List<CountryDto> countries)
  {
    this.countries = countries;
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
