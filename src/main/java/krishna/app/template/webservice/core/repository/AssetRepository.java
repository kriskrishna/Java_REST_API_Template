package boeing.app.template.webservice.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boeing.app.template.webservice.core.model.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Integer> {
	
}
