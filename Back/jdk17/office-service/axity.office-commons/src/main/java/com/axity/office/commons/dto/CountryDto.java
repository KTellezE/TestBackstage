package com.axity.office.commons.dto;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Country Transfer Object class
 * 
 * @author username@axity.com
 */
@Getter
@Setter
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
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    return gson.toJson( this );
  }
}
