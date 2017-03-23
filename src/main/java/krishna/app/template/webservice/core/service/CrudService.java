package boeing.app.template.webservice.core.service;

import java.util.List;

import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;

public interface CrudService<E,ID> {

	List<E> getAll();
	
	E getOne(ID id) throws NotFoundException;

	E create(E entity) throws ConflictException;
	
	E update(ID id, E entity) throws NotFoundException, BadRequestException;
	
	E delete(ID id) throws NotFoundException;
}
