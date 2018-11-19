package pl.lukaszmatug.transfer.api.services;

import java.math.BigDecimal;

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
	public boolean amountFormatCheck(BigDecimal amount) {
		if(amount == null) return false;
		return (amount.compareTo(BigDecimal.ZERO) == 1);
	}

	@Override
	public boolean enoughtMoney(Account account, BigDecimal amount) {
		return (account.getBalance().compareTo(amount) == 1 );
	}

	@Override
	public boolean exchangeRateExists(ExchangeRate exchangeRate) {
		return !(exchangeRate == null);
	}

	@Override
	public boolean accountIsLocked(Account account) {
		return (account.isLocked());
	}

}
