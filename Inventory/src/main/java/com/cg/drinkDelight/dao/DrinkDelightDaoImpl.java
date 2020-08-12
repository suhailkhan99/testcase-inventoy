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

	

}
