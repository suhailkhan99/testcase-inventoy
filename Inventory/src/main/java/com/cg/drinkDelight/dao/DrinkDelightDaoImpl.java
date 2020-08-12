package com.cg.drinkDelight.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.drinkDelight.entity.InventoryTxn;

@Repository
public class DrinkDelightDaoImpl implements DrinkDelightDao{
   @PersistenceContext
    private EntityManager em;
	
	@Override
	public boolean addInventory(InventoryTxn inventoryTxn) {
		em.persist(inventoryTxn);
		return true;
	}

	@Override
	public List<InventoryTxn> viewInventory(String vendorId) {
		String jpql ="from InventoryTxn inv inner join fetch inv.vendor v where v.vendorId=:vid";
		TypedQuery<InventoryTxn> query = em.createQuery(jpql,InventoryTxn.class);
		query.setParameter("vid", vendorId);
		return query.getResultList();
	}

	@Override
	public List<InventoryTxn> viewInventoryForVendorType(String vendorType) {
		String jpql ="from InventoryTxn inv  inner join fetch inv.prod p inner join fetch inv.vendor v where v.vendorType=:vtype";
		TypedQuery<InventoryTxn> query = em.createQuery(jpql,InventoryTxn.class);
		query.setParameter("vname", vendorType);
		return query.getResultList();
	}

	@Override
	public List<InventoryTxn> viewInventoryForProductId(long productId, String vendorType) {
		String jpql ="from InventoryTxn inv  inner join fetch inv.prod p inner join fetch inv.vendor v where p.productId=:pid and v.vendorType=:vtype";
		TypedQuery<InventoryTxn> query = em.createQuery(jpql,InventoryTxn.class);
		query.setParameter("pid", productId);
		query.setParameter("vtype", vendorType);
		return query.getResultList();
		
	}
	@Override
	public List<InventoryTxn> viewInventory(LocalDate fromDt, LocalDate toDt, String vendorType) {
		String jpql ="from InventoryTxn inv  inner join fetch inv.prod p inner join fetch inv.vendor v where inv.dateOfTxn between :fromDt and :toDate and v.vendorType =:vtype order by inv.dateOfTxn desc";
		TypedQuery<InventoryTxn> query = em.createQuery(jpql,InventoryTxn.class);
		query.setParameter("fromDt", fromDt);
		query.setParameter("toDate", toDt);
		query.setParameter("vtype", vendorType);
		return query.getResultList();
	}

	@Override
	public long getMaxTxID() {
		String jpql ="select max(InventoryId) from InventoryTxn";
		TypedQuery<Long> query = em.createQuery(jpql,Long.class);
		return query.getSingleResult();
	}

	@Override
	public List<InventoryTxn> getAllInventory() {
		String jpql = "from InventoryTxn ";
		TypedQuery<InventoryTxn> query = em.createQuery(jpql,InventoryTxn.class);
		return query.getResultList();
	}
	
	@Override
	public boolean addProduct(Product prod) {
		em.persist(prod);
		return true;
	}

	@Override
	public boolean editProduct(Product prod) {
		em.merge(prod);
		return true;
	}

	@Override
	public Product viewProduct(long prodId) {
		
		return em.find(Product.class, prodId);
	}

	@Override
	public List<Product> viewProducts() {
		String jpql = "from Product ";
		TypedQuery<Product> query = em.createQuery(jpql, Product.class);
		return query.getResultList();
	}
	
	@Override
	public boolean deleteProduct(Product product) {
		em.remove(product);
		return true;
	}


	@Override
	public Vendor viewVendor(long vendorId) {
		return em.find(Vendor.class , vendorId);
	}

	@Override
	public List<Vendor> viewVendors() {
		String jpql= "from Vendor v ";
		TypedQuery<Vendor> query = em.createQuery(jpql, Vendor.class);
		return query.getResultList();
		
	}

	@Override
	public List<Vendor> viewVendorbyType(String VendorType) {
		String jpql = "from Vendor v where v.vendortype = :vtype";
		TypedQuery<Vendor> query = em.createQuery(jpql, Vendor.class);
		query.setParameter("vtype", VendorType);
		return query.getResultList();

	}


	

}
