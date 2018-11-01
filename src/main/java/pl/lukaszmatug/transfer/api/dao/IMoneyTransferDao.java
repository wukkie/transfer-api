package pl.lukaszmatug.transfer.api.dao;

import pl.lukaszmatug.transfer.api.domain.TransferSummary;

public interface IMoneyTransferDao {
	public TransferSummary forecast(String accountFrom, String accountTo, Double amount, String feeStrategy);
	public TransferSummary transfer(String accountFrom, String accountTo, Double amount, String feeStrategy);
}
