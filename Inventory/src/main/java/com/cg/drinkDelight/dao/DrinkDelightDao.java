package com.cg.drinkDelight.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.drinkDelight.entity.InventoryTxn;

public interface DrinkDelightDao {
	public boolean addInventory(InventoryTxn inventoryTxn);
	public List<InventoryTxn> viewInventory(String vendorId);
	public List<InventoryTxn> viewInventoryForVendorType(String vendorType);
	public List<InventoryTxn> viewInventoryForProductId(long productId, String vendorType);
	public List<InventoryTxn> viewInventory(LocalDate fromDt, LocalDate toDt, String vendorType);
	public List<InventoryTxn> getAllInventory();
	public long getMaxTxID();
}
