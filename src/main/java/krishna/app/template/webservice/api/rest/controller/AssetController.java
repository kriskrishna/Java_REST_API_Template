package boeing.app.template.webservice.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import boeing.app.template.webservice.core.model.entity.Asset;
import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;
import boeing.app.template.webservice.core.service.impl.AssetService;

@RequestMapping(AssetController.PATH)
@RestController
public class AssetController extends AbstractController implements CrudController<Asset,Integer> {
	
	public static final String PATH = "/api/asset";
	
	@Autowired
	protected AssetService svc;
	
	@RequestMapping(
			method = RequestMethod.GET)
	public ResponseEntity<List<Asset>> getAll() {
		return ok(svc.getAll());
	}

	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<Asset> getOne(@PathVariable("id") Integer id) throws NotFoundException {
		Asset entity = svc.getOne(id);
		return ok(entity);
	}
	
	@RequestMapping(
			method = RequestMethod.POST)
	public ResponseEntity<Asset> post(@RequestBody Asset entity) throws ConflictException {
		entity = svc.create(entity);
		return created(entity);
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.PUT)
	public ResponseEntity<Asset> put(@PathVariable("id") Integer id, @RequestBody Asset entity) throws BadRequestException, NotFoundException {
		entity = svc.update(id, entity);
		return ok(entity);
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<Asset> delete(@PathVariable("id") Integer id) throws NotFoundException {
		Asset entity = svc.delete(id);
		return ok(entity);
	}

}
