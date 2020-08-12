package com.cg.drinkDelight.web;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.drinkDelight.exception.InvalidProdIdException;
import com.cg.drinkDelight.exception.InvalidVendorIdException;
import com.cg.drinkDelight.exception.InventoryTxnException;
import com.cg.drinkDelight.exception.LoginException;
import com.cg.drinkDelight.exception.OutOfStockException;

public class VendorTypeException {
Logger logger = LoggerFactory.getLogger(VendorTypeException.class);

	@ExceptionHandler(value = {InvalidProdIdException.class})
	@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="Unknown Product")
	public void  handleException(Exception ex) {
		logger.error(ex.getMessage());
	}

	@ExceptionHandler(value = {InvalidVendorIdException.class})
	@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="Unknown Vendor")
	public void  handleException2(Exception ex) {
		logger.error(ex.getMessage());	
	}
	
	@ExceptionHandler(value = {OutOfStockException.class, InventoryTxnException.class, LoginException.class})
	@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason="out of stock")
	public ErrorInfo  handleExceptio3(Exception ex) {
		logger.error(ex.getMessage());
		ErrorInfo error = new ErrorInfo();
		error.setMessage(ex.getMessage());
		return error;
	}
	

}
