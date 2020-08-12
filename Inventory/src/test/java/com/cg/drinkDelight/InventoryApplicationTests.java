package com.cg.drinkDelight;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.drinkDelight.exception.InventoryTxnException;

@SpringBootTest()
class InventoryApplicationTests {
	
	@Test
	void contextLoads() throws InventoryTxnException {
		assertTrue(String.valueOf(true), true);
	}

}
