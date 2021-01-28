package com.felipemelo.pandemicsystem.domain.model.exception;

public class NegociacaoInvalidaException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public NegociacaoInvalidaException(String msg) {
		super(msg);
	}
	
	public NegociacaoInvalidaException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
