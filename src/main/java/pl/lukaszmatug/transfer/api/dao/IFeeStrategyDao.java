package pl.lukaszmatug.transfer.api.dao;

import pl.lukaszmatug.transfer.api.domain.FeeStrategy;

public interface IFeeStrategyDao {
	public FeeStrategy findById(String id);
}
