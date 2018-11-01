package pl.lukaszmatug.transfer.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.lukaszmatug.transfer.api.util.ResponseMessage;

@Data
@AllArgsConstructor
public class BusinessLogicException extends Exception {
	ResponseMessage responseStatus;
	
	public BusinessLogicException(int code, String message){
		this.responseStatus = new ResponseMessage(code, message);
	}
}
