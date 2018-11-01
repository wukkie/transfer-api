package pl.lukaszmatug.transfer.api.resources;

import java.util.Optional;

import javax.ws.rs.core.Response;

public interface ITransferResource {
	public Response getForecast(Optional<String> from, 
			Optional<String> to,
			Optional<Double> amount, 
			Optional<String> feeStrategy);
	
	public Response transfer(Optional<String> from, 
			Optional<String> to,
			Optional<Double> amount, 
			Optional<String> feeStrategy);

}
