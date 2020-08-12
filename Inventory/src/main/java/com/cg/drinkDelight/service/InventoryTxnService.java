package com.cg.drinkDelight.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.drinkDelight.exception.InvalidProdIdException;
import com.cg.drinkDelight.exception.InvalidVendorIdException;
import com.cg.drinkDelight.exception.InventoryTxnException;
import com.cg.drinkDelight.exception.OutOfStockException;
import com.cg.drinkDelight.dto.InventoryForm;
import com.cg.drinkDelight.entity.InventoryTxn;

public interface InventoryTxnService {
	public boolean addInventoryTxn(InventoryForm form) throws InvalidProdIdException, InvalidVendorIdException, OutOfStockException;
	public List<InventoryTxn> viewSpecVendorTxn(String vendorId) throws InvalidVendorIdException;
	public List<InventoryTxn> viewSupplierForProductId(long productId,String VendorType) throws InvalidProdIdException;
	public List<InventoryTxn> viewConsumerForProductId(long productId, String VendorType ) throws InvalidProdIdException;
	public List<InventoryTxn> viewTxnByDateForConsumer(LocalDate fromdt, LocalDate toDt,String vendorType) throws InventoryTxnException;
	public List<InventoryTxn> viewTxnByDateForSupplier(LocalDate fromdt, LocalDate toDt,String vendorType) throws InventoryTxnException;
	public List<InventoryTxn>  viewAllInventory() throws InventoryTxnException;

}
