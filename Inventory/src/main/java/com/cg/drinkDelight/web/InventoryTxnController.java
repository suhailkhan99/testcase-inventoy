package com.cg.drinkDelight.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.drinkDelight.exception.InvalidProdIdException;
import com.cg.drinkDelight.exception.InvalidVendorIdException;
import com.cg.drinkDelight.exception.InventoryTxnException;
import com.cg.drinkDelight.exception.OutOfStockException;
import com.cg.drinkDelight.dto.InventoryForm;
import com.cg.drinkDelight.dto.InventoryMessage;
import com.cg.drinkDelight.entity.InventoryTxn;
import com.cg.drinkDelight.service.InventoryTxnService;
import com.cg.drinkDelight.util.DrinkDelightConstant;

@RestController
@CrossOrigin("http://localhost:4200")
public class InventoryTxnController {

    @Autowired
    public InventoryTxnService ser;
    
    @PostMapping("/addInventoryTxn")
    public InventoryMessage addInvTxn(@RequestBody InventoryForm form) throws InvalidProdIdException, InvalidVendorIdException,OutOfStockException{
    	ser.addInventoryTxn(form);
    	InventoryMessage msg = new InventoryMessage();
    	msg.setMessage(DrinkDelightConstant.SUCCESS_TXN);
		return msg;
    	
    }
    
    @GetMapping("/viewSpecVendorTxn/{vendorId}")
    public List<InventoryTxn> getSpecVendor(@PathVariable String vendorId) throws InvalidVendorIdException{
    	List<InventoryTxn> lstTxn = ser.viewSpecVendorTxn(vendorId);
		return lstTxn;
    }
    
    @GetMapping("/viewSupplierPurchase/{productId}")
    public List<InventoryTxn> viewSuppPurchase(@PathVariable long productId) throws InvalidProdIdException {
    	return ser.viewSupplierForProductId(productId, DrinkDelightConstant.SUPPLIER);

    }
    
   @GetMapping("/viewConsumerPurchase/{productId}")
   public List<InventoryTxn> viewConsPurchase(@PathVariable long productId) throws InvalidProdIdException{
	   return ser.viewConsumerForProductId(productId, DrinkDelightConstant.CONSUMER);
   }
   
  @GetMapping("/viewTxnByDtCons/{fromdt}/{todt}")
  
  public List<InventoryTxn> viewSales(@PathVariable("fromdt")  @DateTimeFormat(pattern="yyyy-M-d") LocalDate fromDt ,
		                   @PathVariable("todt")  @DateTimeFormat(pattern="yyyy-M-d") LocalDate toDt) throws InventoryTxnException{
	  return ser.viewTxnByDateForConsumer(fromDt, toDt,DrinkDelightConstant.CONSUMER);
  }
   
  @GetMapping("/viewTxnByDtSupp/{fromdt}/{todt}")
  public List<InventoryTxn> viewPurchase(@PathVariable("fromdt")  @DateTimeFormat(pattern="yyyy-M-d") LocalDate fromDt ,
		               @PathVariable("todt")  @DateTimeFormat(pattern="yyyy-M-d") LocalDate toDt) throws InventoryTxnException{
	  return ser.viewTxnByDateForSupplier(fromDt,toDt,DrinkDelightConstant.SUPPLIER);

  }
  
  @GetMapping("/viewAll")
  public List<InventoryTxn> viewTxn() throws InventoryTxnException{
	  return ser.viewAllInventory();
  }
   
    

}
