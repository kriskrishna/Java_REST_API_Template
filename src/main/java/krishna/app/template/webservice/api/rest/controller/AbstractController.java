package boeing.app.template.webservice.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 
 * Define common generic return methods
 * 
 * @author bx642d
 *
 */
public abstract class AbstractController {

	protected <D> ResponseEntity<D> ok(D data) {
		return new ResponseEntity<D>(data, HttpStatus.OK);
	}

	protected <D> ResponseEntity<D> notFound(D error) {
		return new ResponseEntity<D>(error, HttpStatus.NOT_FOUND);
	}

	protected <D> ResponseEntity<D> conflict(D error) {
		return new ResponseEntity<D>(error, HttpStatus.CONFLICT);
	}

	protected <D> ResponseEntity<D> created(D data) {
		return new ResponseEntity<D>(data, HttpStatus.CREATED);
	}

	protected <D> ResponseEntity<D> badRequest(D error) {
		return new ResponseEntity<D>(error, HttpStatus.BAD_REQUEST);
	}

}
