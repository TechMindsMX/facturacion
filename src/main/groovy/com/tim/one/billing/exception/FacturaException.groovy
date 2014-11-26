package com.tim.one.billing.exception

// TODO: Ver porque causa error al crearse
public class FacturaException extends RuntimeException {

  private static final long serialVersionUID = 1L

  public FacturaException(String msg){
    super(msg)
  }

  public FacturaException(String msg, Throwable cause) {
    super(msg, cause)
  }

}
