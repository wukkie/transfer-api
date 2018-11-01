package pl.lukaszmatug.transfer.api.dao;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import pl.lukaszmatug.transfer.api.domain.FeeStrategy;

public class FeeStrategyDao extends AbstractDAO<FeeStrategy> implements IFeeStrategyDao {

	public FeeStrategyDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public FeeStrategy findById(String id) {
		return get(id);
	}

}
