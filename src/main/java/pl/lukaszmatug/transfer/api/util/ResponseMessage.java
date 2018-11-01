package pl.lukaszmatug.transfer.api.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
	private int code;
	private String message;
}
