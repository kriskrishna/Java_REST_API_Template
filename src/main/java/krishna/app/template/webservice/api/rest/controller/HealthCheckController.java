package boeing.app.template.webservice.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boeing.app.template.webservice.core.healthcheck.Health;
import boeing.app.template.webservice.core.healthcheck.HealthCheckService;

@RestController
public class HealthCheckController extends AbstractController {

	@Autowired
	private HealthCheckService healthCheckService;

	@RequestMapping(value = "/health")
	public Health healthCheck() throws Exception {
		return healthCheckService.healthCheck();
	}

}
