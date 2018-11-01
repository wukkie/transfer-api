package pl.lukaszmatug.transfer.api.domain;

import lombok.Data;

@Data
public class TransferSummary {
	private Long id;
	private Account from;
	private Account to;
	private FeeStrategy feeStrategy;
	private double amount; 
	private double feeCollected;
	private ExchangeRate exchangeRate;
	private double amountAfterExchange; 
}
