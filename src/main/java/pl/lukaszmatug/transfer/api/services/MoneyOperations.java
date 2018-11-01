package pl.lukaszmatug.transfer.api.services;

import pl.lukaszmatug.transfer.api.domain.Account;
import pl.lukaszmatug.transfer.api.domain.ExchangeRate;

public class MoneyOperations {

	public static Account reduceCashFromAccount(Account account, Double amount) {
		if (amount == null) return account;
		account.setBalance(account.getBalance() - amount);
		return account;
	}
	
	public static Account addCashToAccount(Account account, Double amount) {
		account.setBalance(account.getBalance() + amount);
		return account;
	}
	
	public static Double exchange(Double amount, ExchangeRate er) {
		return amount * er.getRate();
	}
}
