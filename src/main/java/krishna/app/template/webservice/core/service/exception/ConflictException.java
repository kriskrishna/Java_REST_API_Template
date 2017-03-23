package boeing.app.template.webservice.core.service.exception;

public class ConflictException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ConflictException(String errorMessage) {
		super(errorMessage);
	}

}
