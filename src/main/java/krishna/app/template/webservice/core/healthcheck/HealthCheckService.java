package boeing.app.template.webservice.core.healthcheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {
	
	@Autowired
	private HealthCheckRepository healthCheckRepo;
	
	public Health healthCheck() throws Exception {
		return healthCheckRepo.isDatabaseUp();
	}
	
}
