package pl.lukaszmatug.transfer.api.services;

import java.math.BigDecimal;

import pl.lukaszmatug.transfer.api.domain.Account;
import pl.lukaszmatug.transfer.api.domain.ExchangeRate;
import pl.lukaszmatug.transfer.api.domain.FeeStrategy;

public interface IMoneyTransferValidation {
	public boolean accountExists(Account account);
	public boolean feeStrategyExists(FeeStrategy feeStrategy); 
	public boolean amountFormatCheck(BigDecimal amount);
	public boolean enoughtMoney(Account account, BigDecimal amount); 
	public boolean exchangeRateExists(ExchangeRate exchangeRate); 
	public boolean accountIsLocked(Account account);
}
