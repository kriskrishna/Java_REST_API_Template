package boeing.app.template.webservice.api.rest.exception;

import org.springframework.http.HttpStatus;

public class ApiError {
	private final Integer statusCode;
	private final String status;
	private final String message;

	public ApiError(HttpStatus status, String message) {
		this.statusCode = status.value();
		this.status = status.toString();
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
