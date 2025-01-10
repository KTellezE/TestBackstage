package com.novus.branch.model;

import java.io.Serializable;


import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;


import lombok.Getter;
import lombok.Setter;
/**
 * Entity class of the table TBL_Branch
 * 
 * @author username@novus.com
 */
@Entity
@Table(name = "TBL_Branch")
@Getter
@Setter
public class BranchDO implements Serializable
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
  @JoinColumn(name = "cd_nation", referencedColumnName = "cd_id")
  private NationDO nation;
  
  
  @Column(name = "nb_postalCode")
  private String postalCode;
  

  

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
      BranchDO that = (BranchDO) object;

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