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

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * Entity class of the table TBL_Country
 * 
 * @author KevinTellez@axity.com
 */
@Entity
@Table(name = "TBL_Country")

public class CountryDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "nb_name")
  private String name;
  
  @ManyToOne
  @JoinColumn(name = "cd_territory", referencedColumnName = "cd_id")
  private TerritoryDO territory;
  
  
  @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
  private List<OfficeDO> offices;
  
  

  
    
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
  public TerritoryDO getTerritory()
  {
     return this.territory;
  }

  /**
   * Sets Territory
   * @param territory
   */
  public void setTerritory(TerritoryDO territory)
  {
    this.territory = territory;
  }
    
  /** 
   * Gets Offices 
   */
  public List<OfficeDO> getOffices()
  {
     return this.offices;
  }

  /**
   * Sets Offices
   * @param offices
   */
  public void setOffices(List<OfficeDO> offices)
  {
    this.offices = offices;
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
      CountryDO that = (CountryDO) object;

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
