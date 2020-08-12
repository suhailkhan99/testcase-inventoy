package com.cg.drinkDelight.exception;

public class OutOfStockException extends Exception{

	@Override
	public String toString() {
		
		return "The Selected product is out of stock";
	}

	public OutOfStockException() {
		super();
		
	}

	public OutOfStockException(String message) {
		super(message);
		
	}
	
	

}
