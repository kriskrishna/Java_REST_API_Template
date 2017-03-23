package boeing.app.template.webservice.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import boeing.app.template.webservice.core.model.entity.Category;
import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;
import boeing.app.template.webservice.core.service.impl.CategoryService;

@RequestMapping(CategoryController.PATH)
@RestController
public class CategoryController extends AbstractController implements CrudController<Category,Integer> {

	public static final String PATH = "/api/category";
	
	@Autowired
	protected CategoryService svc;
	
	@RequestMapping(
			method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAll() {
		return ok(svc.getAll());
	}

	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<Category> getOne(@PathVariable("id") Integer id) throws NotFoundException {
		Category entity = svc.getOne(id);
		return ok(entity);
	}

	@RequestMapping(
			method = RequestMethod.POST)
	public ResponseEntity<Category> post(@RequestBody Category entity) throws ConflictException {
		entity = svc.create(entity);
		return created(entity);
	}

	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.PUT)
	public ResponseEntity<Category> put(@PathVariable("id") Integer id, @RequestBody Category entity) throws BadRequestException, NotFoundException {
		entity = svc.update(id, entity);
		return ok(entity);
	}

	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<Category> delete(@PathVariable("id") Integer id) throws NotFoundException {
		Category entity = svc.delete(id);
		return ok(entity);
	}

}
