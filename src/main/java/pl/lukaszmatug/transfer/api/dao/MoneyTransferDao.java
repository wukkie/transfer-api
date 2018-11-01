package pl.lukaszmatug.transfer.api.dao;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import pl.lukaszmatug.transfer.api.domain.TransferOrder;
import pl.lukaszmatug.transfer.api.domain.TransferSummary;

public class MoneyTransferDao extends AbstractDAO<TransferOrder> implements IMoneyTransferDao {

	public MoneyTransferDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public TransferSummary forecast(String accountFrom, String accountTo, Double amount, String feeStrategy) {
		return null;
	}

	@Override
	public TransferSummary transfer(String accountFrom, String accountTo, Double amount, String feeStrategy) {
		return null;
	}

}
