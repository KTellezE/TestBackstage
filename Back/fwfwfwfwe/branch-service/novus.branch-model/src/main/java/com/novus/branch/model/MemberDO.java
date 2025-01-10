package com.novus.branch.model;

import java.io.Serializable;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


import jakarta.persistence.Table;


import lombok.Getter;
import lombok.Setter;
/**
 * Entity class of the table TBL_Member
 * 
 * @author username@novus.com
 */
@Entity
@Table(name = "TBL_Member")
@Getter
@Setter
public class MemberDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "cd_username")
  private String username;
  
  @Column(name = "cd_email")
  private String email;
  
  @Column(name = "nb_name")
  private String name;
  
  @Column(name = "nb_lastname")
  private String lastName;
  
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "TRL_MemberPosition", joinColumns = {
    @JoinColumn(name = "cd_member", referencedColumnName = "cd_id") }, inverseJoinColumns = {
        @JoinColumn(name = "cd_position", referencedColumnName = "cd_id") })
  private List<PositionDO> positions;
  
  
  

  

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
      MemberDO that = (MemberDO) object;

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
