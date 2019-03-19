package com.jasiczek.fitnessCenter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import database.SQLConnection;
import junit.framework.TestCase;

public class CustomerAccount {
	@Test
	public void correctCreationCustomerAccountTest() {
		SQLConnection sql = new SQLConnection();
		boolean status = sql.createCustomer("Piotr", "Kowalski", "PiotrKow", "piotrkowal", "piotrkowal");
		assertEquals(true, status);
	}

	@Test
	public void incorrectCreationCustomerAccountTest() {
		SQLConnection sql = new SQLConnection();
		boolean status = sql.createCustomer("Piotr", "Kowalski", "PiotrKow", "piotrkowall", "piotrkowal");
		assertEquals(false, status);
	}

	@Test
	public void correctLoginToAccount() {
		SQLConnection sql = new SQLConnection();
		long status = sql.customerLogin("PiotrKow", "piotrkowal");
		assertNotNull(status);
	}

	@Test
	public void incorrectLoginToAccount() {
		SQLConnection sql = new SQLConnection();
		long status = sql.customerLogin("PiotrKow", "piotr");
		assertEquals(0, status);
	}

	@Test
	public void addFundsToLoginAccount() {
		SQLConnection sql = new SQLConnection();
		String statusBeforeAddFunds = sql.theQuery("SELECT funds FROM customer WHERE customer_id=1", "funds");
		sql.addFundsToYourAccount(1, 300);
		String statusAfterAddFunds = sql.theQuery("SELECT funds FROM customer WHERE customer_id=1", "funds");
		assertEquals(Integer.parseInt(statusAfterAddFunds), Integer.parseInt(statusBeforeAddFunds) + 300);
	}

	@Test
	public void removeFundsToLoginAccount() {
		SQLConnection sql = new SQLConnection();
		String statusBeforeRemoveFunds = sql.theQuery("SELECT funds FROM customer WHERE customer_id=1", "funds");
		sql.removeFundsFromeYourAccount(1, 300);
		String statusAfterRemoveFunds = sql.theQuery("SELECT funds FROM customer WHERE customer_id=1", "funds");
		assertEquals(Integer.parseInt(statusAfterRemoveFunds), Integer.parseInt(statusBeforeRemoveFunds) - 300);
	}

}
