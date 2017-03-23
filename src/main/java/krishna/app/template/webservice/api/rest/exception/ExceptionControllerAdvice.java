package boeing.app.template.webservice.api.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import boeing.app.template.webservice.api.rest.controller.AbstractController;
import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice extends AbstractController {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> NotFoundExceptionHandler(Exception ex) {
		ApiError err = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return this.notFound(err);
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ApiError> EntityExistsExceptionHandler(Exception ex) {
		ApiError err = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
		return this.conflict(err);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiError> BadRequestExceptionHandler(Exception ex) {
		ApiError err = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return this.badRequest(err);
	}
	
}
