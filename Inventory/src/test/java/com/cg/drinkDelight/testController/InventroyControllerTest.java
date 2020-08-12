package com.cg.drinkDelight.testController;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.drinkDelight.entity.InventoryTxn;
import com.cg.drinkDelight.service.InventoryTxnServiceImpl;
import com.cg.drinkDelight.web.InventoryTxnController;

@WebMvcTest(InventoryTxnController.class)
public class InventroyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InventoryTxnServiceImpl ser;

	static List<InventoryTxn> list;

	@BeforeAll
	static void init() {
		list = new ArrayList<>(Arrays.asList(
				new InventoryTxn(11, 15, LocalDate.of(2020, 7, 12), "consumer"),
				new InventoryTxn(12, 15, LocalDate.of(2020, 4, 12), "consumer"),
				new InventoryTxn(13, 15, LocalDate.of(2020, 4, 12), "consumer")));
	}

	// test for view specific vendor transaction
	@Test
	public void getControllerTest() throws Exception {

		when(ser.viewSpecVendorTxn("5")).thenReturn(list);

		mockMvc.perform(get("/viewSpecVendorTxn/5"))

				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(3)))
				.andExpect(jsonPath("$[0].inventoryId", is(11))).andExpect(jsonPath("$[0].qty", is(15)))
				.andExpect(jsonPath("$[0].txtType", is("consumer")));

		verify(ser, times(1)).viewSpecVendorTxn("5");
	}

	// test for view All transaction
	@Test
	public void viewAllTransaction() throws Exception {
		when(ser.viewAllInventory()).thenReturn(list);
		mockMvc.perform(get("/viewAll")).andDo(print()).andExpect(status().isOk());
	}

	// test for consumer purchase for product id
	@Test
	public void viewByProductId() throws Exception {
		when(ser.viewConsumerForProductId(1001, "consumer")).thenReturn(list);
		mockMvc.perform(get("/viewSupplierPurchase/1001")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void viewByDate() throws Exception {
		when(ser.viewTxnByDateForConsumer(LocalDate.of(2020, 7, 1), LocalDate.of(2020, 8, 1), "consumer"))
				.thenReturn(list);
		mockMvc.perform(get("/viewTxnByDtCons/2020-7-1/2020-8-1")).andDo(print()).andExpect(status().isOk());
	}

}
