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
	
	public boolean addProduct(Product prod);
	public boolean editProduct(Product prod);
	public Product viewProduct(long prodId);
	public List<Product> viewProducts();
	public boolean deleteProduct(Product product);
	
	public Vendor viewVendor(long vendorId);
	public List<Vendor> viewVendors();
	public List<Vendor> viewVendorbyType(String VendorType);
	
}
