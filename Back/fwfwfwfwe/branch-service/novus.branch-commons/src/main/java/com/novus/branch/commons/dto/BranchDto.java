package com.novus.branch.commons.dto;

import java.io.Serializable;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Branch Transfer Object class
 * 
 * @author username@novus.com
 */
@Getter
@Setter
public class BranchDto implements Serializable
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
  
  @Schema(required = true, description = "Thenation")
  private  NationDto nation;
  
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
