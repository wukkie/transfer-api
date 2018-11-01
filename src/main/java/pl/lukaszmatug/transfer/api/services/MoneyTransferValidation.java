package pl.lukaszmatug.transfer.api.services;

import pl.lukaszmatug.transfer.api.domain.Account;
import pl.lukaszmatug.transfer.api.domain.ExchangeRate;
import pl.lukaszmatug.transfer.api.domain.FeeStrategy;

public class MoneyTransferValidation implements IMoneyTransferValidation{

	@Override
	public boolean accountExists(Account account) {
		return !(account == null);
	}

	@Override
	public boolean feeStrategyExists(FeeStrategy feeStrategy) {
		return !(feeStrategy == null);
	}

	@Override
	public boolean amountFormatCheck(Double amount) {
		if(amount == null) return false;
		return (amount >= 0);
	}

	@Override
	public boolean enoughtMoney(Account account, Double amount) {
		return (account.getBalance() >= amount);
	}

	@Override
	public boolean exchangeRateExists(ExchangeRate exchangeRate) {
		return !(exchangeRate == null);
	}

}
