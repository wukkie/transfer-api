package pl.lukaszmatug.transfer.api.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferSummary {
	private Long id;
	private Account from;
	private Account to;
	private FeeStrategy feeStrategy;
	private BigDecimal amount; 
	private BigDecimal feeCollected;
	private ExchangeRate exchangeRate;
	private BigDecimal amountAfterExchange; 
}
