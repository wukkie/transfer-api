package pl.lukaszmatug.transfer.api.services;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestEntity {
	@JsonProperty
	private String from;
	@JsonProperty
	private String to;
	@JsonProperty
	private String amount;
	@JsonProperty
	private String feeStrategy;
}
