package pl.lukaszmatug.transfer.api.domain;

public class TransferOrder {
	private Long id;
	private Account from;
	private Account to;
	private String feeStrategy;
	private double amount; 
	private double feeCollected;
}
	