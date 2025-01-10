package com.axity.office.commons.dto;

import java.io.Serializable;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Office Transfer Object class
 * 
 * @author username@axity.com
 */
@Getter
@Setter
public class OfficeDto implements Serializable
{
  private static final long serialVersionUID = 1L;

  
  @Schema(required = true, description = "Theid")
  private  Integer id;
  
  @Schema(required = true, description = "Thecity")
  private  String city;
  
  @Schema(required = true, description = "Thephone")
  private  String phone;
  
  @Schema(required = true, description = "Theaddressline1")
  private  String addressLine1;
  
  @Schema(required = true, description = "Theaddressline2")
  private  String addressLine2;
  
  @Schema(required = true, description = "Thestate")
  private  String state;
  
  @Schema(required = true, description = "Thecountry")
  private  CountryDto country;
  
  @Schema(required = true, description = "Thepostalcode")
  private  String postalCode;
  

  

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
