package pl.lukaszmatug.transfer.api.dao;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import pl.lukaszmatug.transfer.api.domain.Currency;
import pl.lukaszmatug.transfer.api.domain.ExchangeRate;

public class ExchangeRateDao extends AbstractDAO<ExchangeRate> implements IExchangeRateDao {

	public ExchangeRateDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public ExchangeRate findByIds(Currency from, Currency to) {
		return uniqueResult(namedQuery("pl.lukaszmatug.transfer.api.domain.ExchangeRate.findByIds")
				.setParameter("from", from)
				.setParameter("to", to)
				);
	}
}
