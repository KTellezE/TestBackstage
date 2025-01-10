package com.axity.consultant.commons.dto;

import java.io.Serializable;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Office Transfer Object class
 * 
 * @author KevinTellez@axity.com
 */

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
   * Gets City 
   */
  public String getCity()
  {
     return this.city;
  }

  /**
   * Sets City
   * @param city
   */
  public void setCity(String city)
  {
    this.city = city;
  }
    
  /** 
   * Gets Phone 
   */
  public String getPhone()
  {
     return this.phone;
  }

  /**
   * Sets Phone
   * @param phone
   */
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
    
  /** 
   * Gets AddressLine1 
   */
  public String getAddressLine1()
  {
     return this.addressLine1;
  }

  /**
   * Sets AddressLine1
   * @param addressLine1
   */
  public void setAddressLine1(String addressLine1)
  {
    this.addressLine1 = addressLine1;
  }
    
  /** 
   * Gets AddressLine2 
   */
  public String getAddressLine2()
  {
     return this.addressLine2;
  }

  /**
   * Sets AddressLine2
   * @param addressLine2
   */
  public void setAddressLine2(String addressLine2)
  {
    this.addressLine2 = addressLine2;
  }
    
  /** 
   * Gets State 
   */
  public String getState()
  {
     return this.state;
  }

  /**
   * Sets State
   * @param state
   */
  public void setState(String state)
  {
    this.state = state;
  }
    
  /** 
   * Gets Country 
   */
  public CountryDto getCountry()
  {
     return this.country;
  }

  /**
   * Sets Country
   * @param country
   */
  public void setCountry(CountryDto country)
  {
    this.country = country;
  }
    
  /** 
   * Gets PostalCode 
   */
  public String getPostalCode()
  {
     return this.postalCode;
  }

  /**
   * Sets PostalCode
   * @param postalCode
   */
  public void setPostalCode(String postalCode)
  {
    this.postalCode = postalCode;
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
