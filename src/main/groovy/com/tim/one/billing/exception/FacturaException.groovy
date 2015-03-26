package com.tim.one.billing.exception

public class FacturaException extends RuntimeException {

  private static final long serialVersionUID = 1L

  public FacturaException(String msg){
    super(msg)
  }

  public FacturaException(String msg, Throwable cause) {
    super(msg, cause)
  }

}
