package com.axity.consultant.model;

import java.io.Serializable;


import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;



/**
 * Entity class of the table TBL_Office
 * 
 * @author KevinTellez@axity.com
 */
@Entity
@Table(name = "TBL_Office")

public class OfficeDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "nb_city")
  private String city;
  
  @Column(name = "nb_phone")
  private String phone;
  
  @Column(name = "nb_addressLine1")
  private String addressLine1;
  
  @Column(name = "nb_addressLine2")
  private String addressLine2;
  
  @Column(name = "nb_state")
  private String state;
  
  @ManyToOne
  @JoinColumn(name = "cd_country", referencedColumnName = "cd_id")
  private CountryDO country;
  
  
  @Column(name = "nb_postalCode")
  private String postalCode;
  

  
    
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
  public CountryDO getCountry()
  {
     return this.country;
  }

  /**
   * Sets Country
   * @param country
   */
  public void setCountry(CountryDO country)
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
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      OfficeDO that = (OfficeDO) object;

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
