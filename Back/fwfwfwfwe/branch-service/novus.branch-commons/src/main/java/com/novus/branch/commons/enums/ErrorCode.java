package com.novus.branch.commons.enums;

/**
 * Error code enumeration
 * 
 * @author username@novus.com
 */
public enum ErrorCode
{

  UNKNOWN_ERROR(0), REQUIRED_FIELD(1), EXCEEDS_MAX_LENGTH(2),

  // Validation errors
  BRANCH_ALREADY_EXISTS(100), BRANCH_NOT_FOUND(101);

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
