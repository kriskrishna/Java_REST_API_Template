package boeing.app.template.webservice.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boeing.app.template.webservice.core.model.entity.Asset;
import boeing.app.template.webservice.core.repository.AssetRepository;
import boeing.app.template.webservice.core.service.CrudService;
import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;

@Service
public class AssetService implements CrudService<Asset, Integer>{

	@Autowired
	protected AssetRepository repo;
	
	public static final String NotFoundMessage = "The Asset was Not Found.";
	public static final String ConflictMessage = "The Asset ID is in conflict with current data.";
	public static final String BadRequestMessage = "The Asset is not formed correctly.";
	
	public List<Asset> getAll() {
		return repo.findAll();
	}
	
	public Asset getOne(Integer id) throws NotFoundException {
		Asset retval = repo.findOne(id);
		if (retval == null) {
			throw new NotFoundException(NotFoundMessage);
		}
		return retval;
	}

	public Asset create(Asset entity) throws ConflictException {
		if (entity.getId() != null) {
			throw new ConflictException(ConflictMessage);
		}
		return repo.saveAndFlush(entity);
	}
	
	public Asset update(Integer id, Asset entity) throws NotFoundException, BadRequestException {
		if (!repo.exists(id)) {
			throw new NotFoundException(NotFoundMessage);
		}
		if (!id.equals(entity.getId())) {
			throw new BadRequestException(BadRequestMessage);
		}
		return repo.saveAndFlush(entity);
	}
	
	public Asset delete(Integer id) throws NotFoundException {
		Asset entity = repo.findOne(id);
		if (entity == null) {
			throw new NotFoundException(NotFoundMessage);
		}
		repo.delete(id);
		return entity;
	}
}
