package skate.tracker.controller.error;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * Global error handler that will gracefully throw an error
 * and not throw the generic 500 error telling you what the issue is.
 * @author Demitria Jefferson
 * @version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerErrorHandler {
	private enum LogStatus {
		STACK_TRACE, MESSAGE_ONLY
	}
	
	@Data
	private class ExceptionMessage {
		private String message;
		private String statusReason;
		private int statusCode;
		private String timeStamp;
		private String uri;
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionMessage handleNoSuchElementExcpetion(NoSuchElementException ex, WebRequest webRequest) {		
		return buildExceptionMessage(ex, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
		
	}

	private ExceptionMessage buildExceptionMessage(NoSuchElementException ex, HttpStatus status,
			WebRequest webRequest, LogStatus logStatsu) {

		String message = ex.toString();
		String statusReason = status.getReasonPhrase();
		
		int statusCode = status.value();
		
		return null;
	}
}
