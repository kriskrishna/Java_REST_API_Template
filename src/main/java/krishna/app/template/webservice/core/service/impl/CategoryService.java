package boeing.app.template.webservice.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boeing.app.template.webservice.core.model.entity.Category;
import boeing.app.template.webservice.core.repository.CategoryRepository;
import boeing.app.template.webservice.core.service.CrudService;
import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;

@Service
public class CategoryService implements CrudService<Category, Integer>{

	@Autowired
	protected CategoryRepository repo;

	public static final String NotFoundMessage = "The Category was Not Found.";
	public static final String ConflictMessage = "The Category ID is in conflict with current data.";
	public static final String BadRequestMessage = "The Category is not formed correctly.";
	
	public List<Category> getAll() {
		return repo.findAll();
	}
	
	public Category getOne(Integer id) throws NotFoundException {
		Category retval = repo.findOne(id);
		if(retval == null)
			throw new NotFoundException(NotFoundMessage);
		return retval;
	}

	public Category create(Category entity) throws ConflictException {
		if (entity.getId() != null) {
			throw new ConflictException(ConflictMessage);
		}
		return repo.saveAndFlush(entity);
	}
	
	public Category update(Integer id, Category entity) throws NotFoundException, BadRequestException {
		if (!repo.exists(id)) {
			throw new NotFoundException(NotFoundMessage);
		}
		if (!id.equals(entity.getId())) {
			throw new BadRequestException(BadRequestMessage);
		}
		return repo.saveAndFlush(entity);
	}
	
	public Category delete(Integer id) throws NotFoundException {
		Category entity = repo.findOne(id);
		if (entity == null) {
			throw new NotFoundException(NotFoundMessage);
		}
		repo.delete(id);
		return entity;
	}
}
