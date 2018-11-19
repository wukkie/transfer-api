package pl.lukaszmatug.transfer.api.services;

import java.math.BigDecimal;

import pl.lukaszmatug.transfer.api.domain.Account;
import pl.lukaszmatug.transfer.api.domain.ExchangeRate;

public class MoneyOperations {

	public static Account reduceCashFromAccount(Account account, BigDecimal amount) {
		if (amount == null) return account;
		account.setBalance(account.getBalance().subtract(amount));
		return account;
	}
	
	public static Account addCashToAccount(Account account, BigDecimal amount) {
		account.setBalance(account.getBalance().add(amount));
		return account;
	}
	
	public static BigDecimal exchange(BigDecimal amount, ExchangeRate er) {
		return amount.multiply( er.getRate() );
	}
}
