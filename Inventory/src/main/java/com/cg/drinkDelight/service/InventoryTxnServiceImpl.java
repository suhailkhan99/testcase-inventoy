package com.cg.drinkDelight.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.drinkDelight.exception.InvalidProdIdException;
import com.cg.drinkDelight.exception.InvalidVendorIdException;
import com.cg.drinkDelight.exception.InventoryTxnException;
import com.cg.drinkDelight.exception.OutOfStockException;
import com.cg.drinkDelight.dao.DrinkDelightDao;
import com.cg.drinkDelight.dto.InventoryForm;
import com.cg.drinkDelight.entity.InventoryTxn;
import com.cg.drinkDelight.util.DrinkDelightConstant;

@Service
public class InventoryTxnServiceImpl implements InventoryTxnService{
	
//	Logger log = LoggerFactory.getLogger(InventoryTxnServiceImpl.class);
	
	@Autowired
	private DrinkDelightDao dao;

	@Override
	public boolean addInventoryTxn(InventoryForm form)
			throws InvalidProdIdException, InvalidVendorIdException, OutOfStockException {InventoryTxn txn = new InventoryTxn();

		Vendor vendor = dao.viewVendor(form.getVendorId());
		if (vendor == null || !dao.viewVendors().contains(vendor))
			throw new InvalidVendorIdException(DrinkDelightConstant.INVALID_VENDOR);

		Product prod = dao.viewProduct(form.getProductId());
		if (prod == null || !dao.viewProducts().contains(prod))
			throw new InvalidProdIdException(DrinkDelightConstant.INVALID_PRODUCT);

		if ((txn.getQty() > prod.getStock()) && form.getVendortype().equals(DrinkDelightConstant.CONSUMER))
			throw new OutOfStockException(DrinkDelightConstant.OUT_OF_STOCK);

		long txnId = dao.getMaxTxID() + 1;
		txn.setInventoryId(txnId);
		txn.setProd(prod);
		txn.setVendor(vendor);
		txn.setTxtType(form.getVendortype());
		txn.setDateOfTxn(LocalDate.now());
		txn.setQty(form.getQty());
		if (txn.getVendor().getVendorType().equals(DrinkDelightConstant.SUPPLIER))
			prod.setStock(prod.getStock() + form.getQty());

		else
			prod.setStock(prod.getStock() - form.getQty());

		dao.editProduct(prod);
		dao.addInventory(txn);
		return true; // Order placed Successfully

	}

	@Override
	public List<InventoryTxn> viewSpecVendorTxn(String vendorId) throws InvalidVendorIdException {
		List<InventoryTxn> txnlst = dao.viewInventory(vendorId);
		txnlst.sort((t1, t2) ->t2.getDateOfTxn().compareTo(t1.getDateOfTxn()));
//		log.info(txnlst.toString());
		if(txnlst.isEmpty())
			throw new InvalidVendorIdException(DrinkDelightConstant.NO_TRANSACTIONS);
		return txnlst;
		
	}

	@Override
	public List<InventoryTxn> viewSupplierForProductId(long productId,String VendorType) throws InvalidProdIdException {
		List<InventoryTxn> supplierlst = dao.viewInventoryForProductId(productId,DrinkDelightConstant.SUPPLIER);
		if(supplierlst.isEmpty())
			throw new InvalidProdIdException(DrinkDelightConstant.NOSUPPLIER);
		supplierlst.sort((t1,t2) -> t2.getDateOfTxn().compareTo(t1.getDateOfTxn()));
		return supplierlst;
	}

	@Override
	public List<InventoryTxn> viewConsumerForProductId(long productId,String VendorType) throws InvalidProdIdException {
		List<InventoryTxn> consumerlst = dao.viewInventoryForProductId(productId,DrinkDelightConstant.CONSUMER);
		if(consumerlst.isEmpty())
			throw new InvalidProdIdException(DrinkDelightConstant.NOCONSUMER);
		consumerlst.sort((t1,t2) -> t2.getDateOfTxn().compareTo(t1.getDateOfTxn()));
		return consumerlst;
	}

	@Override
	public List<InventoryTxn> viewTxnByDateForConsumer(LocalDate fromdt, LocalDate toDt,String VendorType) throws InventoryTxnException {
		List<InventoryTxn> txnlst = dao.viewInventory(fromdt, toDt, DrinkDelightConstant.CONSUMER);
		if(txnlst.isEmpty())
			throw new InventoryTxnException(DrinkDelightConstant.NO_TRANSACTIONS);
		txnlst.sort((t1,t2) -> t2.getDateOfTxn().compareTo(t1.getDateOfTxn()));
		return txnlst;
	}

	@Override
	public List<InventoryTxn> viewTxnByDateForSupplier(LocalDate fromdt, LocalDate toDt,String VendorType) throws InventoryTxnException {
		List<InventoryTxn> txnlst = dao.viewInventory(fromdt, toDt, DrinkDelightConstant.SUPPLIER);
		if(txnlst.isEmpty())
			throw new InventoryTxnException(DrinkDelightConstant.NO_TRANSACTIONS);
		txnlst.sort((t1,t2) -> t2.getDateOfTxn().compareTo(t1.getDateOfTxn()));
		return txnlst;
		
	}

	@Override
	public List<InventoryTxn> viewAllInventory() throws InventoryTxnException {
		List<InventoryTxn> list = dao.getAllInventory();
		if(list.isEmpty())
			throw new InventoryTxnException(DrinkDelightConstant.NO_TRANSACTIONS);
		return list;
	}
	
}
