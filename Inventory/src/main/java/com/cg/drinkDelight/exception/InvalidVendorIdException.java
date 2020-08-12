package com.cg.drinkDelight.exception;

public class InvalidVendorIdException extends Exception{

	@Override
	public String toString() {
		
		return "Invalid Vendor Id";
	}

	public InvalidVendorIdException() {
		super();
		
	}

	public InvalidVendorIdException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
