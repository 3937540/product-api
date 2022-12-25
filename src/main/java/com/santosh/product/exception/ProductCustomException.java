package com.santosh.product.exception;

public class ProductCustomException extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	public ProductCustomException(String message) {
		super(message);
	}
	
	public ProductCustomException(Throwable t) {
		super(t);
	}
	
	public ProductCustomException(String message, Throwable t) {
		super(message, t);
	}

}
