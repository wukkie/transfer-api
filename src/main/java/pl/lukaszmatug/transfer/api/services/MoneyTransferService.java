package pl.lukaszmatug.transfer.api.services;

import java.math.BigDecimal;
import java.util.Optional;

import pl.lukaszmatug.transfer.api.dao.IAccountDao;
import pl.lukaszmatug.transfer.api.dao.IExchangeRateDao;
import pl.lukaszmatug.transfer.api.dao.IFeeStrategyDao;
import pl.lukaszmatug.transfer.api.domain.Account;
import pl.lukaszmatug.transfer.api.domain.ExchangeRate;
import pl.lukaszmatug.transfer.api.domain.FeeStrategy;
import pl.lukaszmatug.transfer.api.domain.TransferSummary;
import pl.lukaszmatug.transfer.api.exception.BusinessLogicException;

public class MoneyTransferService {
	private IAccountDao accountDao; 

	private IMoneyTransferValidation validator;
	private IFeeStrategyDao feeStrategyDao;
	private IExchangeRateDao exchangeRateDao;
	
	public MoneyTransferService(IAccountDao accountDao, IFeeStrategyDao feeStrategyDao, IExchangeRateDao exchangeRateDao, IMoneyTransferValidation validator) {
		this.accountDao = accountDao;
		this.feeStrategyDao = feeStrategyDao;
		this.exchangeRateDao = exchangeRateDao;
		this.validator = validator;
	}

	public synchronized  Account checkAndLockAccount(Optional<String> accountId) throws BusinessLogicException{
		Account account = accountDao.findByNumber(accountId.get());
		if(!validator.accountExists(account)) throw new BusinessLogicException(404, String.format("Account %s from you like get money for transfer was not found", accountId.get())); 
		if(validator.accountIsLocked(account)) throw new BusinessLogicException(403, String.format("Account %s from you like get money are locked", accountId.get())); 
		account.setLocked(true);
		return account;
	}

	public synchronized Account unlockAccount(Optional<String> accountId) throws BusinessLogicException {
		Account account = accountDao.findByNumber(accountId.get());
		if(!validator.accountExists(account)) throw new BusinessLogicException(404, String.format("Account %s from you like get money for transfer was not found", accountId.get())); 
		account.setLocked(false);
		return account;
	}
	
	public synchronized TransferSummary transfer(Optional<String> from, Optional<String> to, Optional<BigDecimal> amount, Optional<String> feeStrategyId) throws BusinessLogicException {
		Account fromAccount = accountDao.findByNumber(from.get());
		Account toAccount = accountDao.findByNumber(to.get());
		FeeStrategy feeStrategy = feeStrategyDao.findById(feeStrategyId.get());
		
		if(!amount.isPresent()) throw new BusinessLogicException(400, String.format("Transfer amount can't be a null"));
		if(!validator.accountExists(fromAccount)) throw new BusinessLogicException(404, String.format("Account %s from you like get money for transfer was not found", from.get())); 
		if(!validator.accountExists(toAccount)) throw new BusinessLogicException(404, String.format("Account %s to you like transfer money was not found", to.get())); 
		if(!validator.feeStrategyExists(feeStrategy)) throw new BusinessLogicException(404, String.format("Fee method %s was not found", feeStrategyId.get())); 
		if(!validator.amountFormatCheck(amount.get())) throw new BusinessLogicException(400, String.format("Transfer amount  %s is not valid", feeStrategyId.get())); 
		if(!validator.enoughtMoney(fromAccount, amount.get())) throw new BusinessLogicException(400, String.format("Amount of money is bigger than account balance")); 

		ExchangeRate exchangeRate =  exchangeRateDao.findByIds(fromAccount.getCurrency(), toAccount.getCurrency());
		if(!validator.exchangeRateExists(exchangeRate)) throw new BusinessLogicException(500, String.format("Exchange Rate from %s to %s not exists", fromAccount.getCurrency().getId(), toAccount.getCurrency().getId())); 
		
		BigDecimal amountAfterExchange = MoneyOperations.exchange(amount.get(), exchangeRate);
		MoneyOperations.reduceCashFromAccount(fromAccount, amount.get());
		MoneyOperations.addCashToAccount(toAccount, amountAfterExchange);
		
		TransferSummary ts = new TransferSummary();
		ts.setFrom(fromAccount);
		ts.setTo(toAccount);
		ts.setFeeStrategy(feeStrategy);
		ts.setAmount(amount.get());
		ts.setExchangeRate(exchangeRate);
		ts.setAmountAfterExchange(amountAfterExchange);
		return ts;
	}


}
