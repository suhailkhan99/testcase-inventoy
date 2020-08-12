package com.cg.drinkDelight.exception;

public class InventoryTxnException extends Exception{

	@Override
	public String toString() {
		return "Transaction Id already exist";
	}

	public InventoryTxnException() {
		super();
		
	}

	public InventoryTxnException(String message) {
		super(message);
		
	}
	
	
	

}
