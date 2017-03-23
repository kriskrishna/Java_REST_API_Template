package boeing.app.template.webservice.core.service.exception;

public class BadRequestException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String errorMessage) {
		super(errorMessage);
	}

}
