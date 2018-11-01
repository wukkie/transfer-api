package pl.lukaszmatug.transfer.api.services;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.lukaszmatug.transfer.api.domain.Account;

public class MoneyOperationsTest {

	@Test
	public void testReduceCashFromAccount() {
		Account account = new Account();
		account.setBalance(2333.23);
		assertEquals(2000, MoneyOperations.reduceCashFromAccount(account, 333.23).getBalance(), 0);
	}

	@Test
	public void testAddCashToAccount() {
		Account account = new Account();
		account.setBalance(10);
		assertEquals(30, MoneyOperations.addCashToAccount(account, 20D).getBalance(), 0);
	}

	@Test
	public void testAddCashToAccountWithNullAccount() {
		Account account = new Account();
		assertEquals(-10, MoneyOperations.reduceCashFromAccount(account, 10D).getBalance(), 0);
	}
	
	@Test
	public void testAddCashToAccountWithNullAmount() {
		Account account = new Account();
		account.setBalance(10);
		assertEquals(10, MoneyOperations.reduceCashFromAccount(account, null).getBalance(), 0);
	}


}
