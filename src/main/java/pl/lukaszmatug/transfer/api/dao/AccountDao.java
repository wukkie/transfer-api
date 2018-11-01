package pl.lukaszmatug.transfer.api.dao;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import pl.lukaszmatug.transfer.api.domain.Account;

public class AccountDao extends AbstractDAO<Account> implements IAccountDao {

	public AccountDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Account findByNumber(String id) {
		return get(id);
	}
	
	public Account update(Account account) {
		return persist(account);
	}

}
