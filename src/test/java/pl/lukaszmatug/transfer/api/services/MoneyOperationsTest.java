package pl.lukaszmatug.transfer.api.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import pl.lukaszmatug.transfer.api.domain.Account;

public class MoneyOperationsTest {

	@Test
	public void testReduceCashFromAccount() {
		BigDecimal balanceBefore = BigDecimal.valueOf(2333.23); 
		BigDecimal amount = BigDecimal.valueOf(333.23);
		BigDecimal afterBalance = BigDecimal.valueOf(2000);
		Account account = new Account();
		account.setBalance(balanceBefore);
		
		assertTrue(afterBalance.compareTo( MoneyOperations.reduceCashFromAccount(account, amount ).getBalance()) == 0 );
	}

	@Test
	public void testAddCashToAccount() {
		Account account = new Account();
		account.setBalance(BigDecimal.valueOf(10));
		assertTrue(BigDecimal.valueOf(30).compareTo( MoneyOperations.addCashToAccount(account, BigDecimal.valueOf(20) ).getBalance()) == 0 );
	}


}
