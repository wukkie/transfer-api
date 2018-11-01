package pl.lukaszmatug.transfer.api.dao;

import pl.lukaszmatug.transfer.api.domain.Account;

public interface IAccountDao {
	public Account findByNumber(String id);
}
