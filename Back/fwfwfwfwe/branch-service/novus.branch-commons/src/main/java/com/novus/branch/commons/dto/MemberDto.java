package com.novus.branch.commons.dto;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Member Transfer Object class
 * 
 * @author username@novus.com
 */
@Getter
@Setter
public class MemberDto implements Serializable
{
  private static final long serialVersionUID = 1L;

  
  @Schema(required = true, description = "Theid")
  private  Integer id;
  
  @Schema(required = true, description = "Theusername")
  private  String username;
  
  @Schema(required = true, description = "Theemail")
  @JsonIgnore
  private  String email;
  
  @Schema(required = true, description = "Thename")
  private  String name;
  
  @Schema(required = true, description = "Thelastname")
  private  String lastName;
  
  @Schema(required = true, description = "Thepositions")
  private  List<PositionDto> positions;
  

  

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
