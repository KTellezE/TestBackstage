package com.axity.consultant.commons.enums;

/**
 * Error code enumeration
 * 
 * @author KevinTellez@axity.com
 */
public enum ErrorCode
{

  UNKNOWN_ERROR(0), REQUIRED_FIELD(1), EXCEEDS_MAX_LENGTH(2),

  // Validation errors
  CONSULTANT_ALREADY_EXISTS(100), CONSULTANT_NOT_FOUND(101);

  private int code;

  private ErrorCode( int code )
  {
    this.code = code;
  }

  /**
   * @return the code
   */
  public int getCode()
  {
    return code;
  }

}
