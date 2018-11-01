package pl.lukaszmatug.transfer.api.dao;

import pl.lukaszmatug.transfer.api.domain.Currency;
import pl.lukaszmatug.transfer.api.domain.ExchangeRate;

public interface IExchangeRateDao {
	public ExchangeRate findByIds(Currency from, Currency to);

}
