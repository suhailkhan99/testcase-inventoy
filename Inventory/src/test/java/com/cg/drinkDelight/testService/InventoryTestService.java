package com.cg.drinkDelight.testService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.drinkDelight.dao.DrinkDelightDaoImpl;
import com.cg.drinkDelight.entity.InventoryTxn;
import com.cg.drinkDelight.service.InventoryTxnServiceImpl;

@SpringBootTest
public class InventoryTestService {
	
	@Autowired
	private InventoryTxnServiceImpl ser;
	
	@Mock
	private DrinkDelightDaoImpl dao;
	
	
	@Test
	public void getTestService() throws Exception {
		List<InventoryTxn> list = Arrays.asList(
				new InventoryTxn(11,15, LocalDate.of(2020, 7, 12), "consumer"));
		
		when(dao.viewInventory("5")).thenReturn(list);
		
		List<InventoryTxn> txnlst = ser.viewSpecVendorTxn("5");
		
		assertEquals(1, txnlst.size());
		verify(dao, times(1)).viewInventory("5");
		
	}
	

}
