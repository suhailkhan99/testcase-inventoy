package com.cg.drinkDelight.exception;

public class InvalidProdIdException extends Exception{
	
	

	@Override
	public String toString() {
		
		return "Invalid Product Id";
	}

	public InvalidProdIdException() {
		super();
		
	}

	public InvalidProdIdException(String arg0) {
		super(arg0);
		
	}
	
	

}
