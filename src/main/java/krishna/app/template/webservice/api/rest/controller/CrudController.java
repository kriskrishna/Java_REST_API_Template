package boeing.app.template.webservice.api.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;

public interface CrudController<E,ID> {
	
	ResponseEntity<List<E>> getAll();

	ResponseEntity<E> getOne(@PathVariable("id") ID id) throws NotFoundException;

	ResponseEntity<E> post(@RequestBody E entity) throws ConflictException;
	
	ResponseEntity<E> put(@PathVariable("id") ID id, @RequestBody E entity) throws NotFoundException, BadRequestException;
	
	ResponseEntity<E> delete(@PathVariable("id") ID id) throws NotFoundException;
}
