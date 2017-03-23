package boeing.app.template.webservice.core.healthcheck;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.DataSourceHealthIndicator;

@Repository
public class HealthCheckRepository{
	
	@Autowired
	private DataSource dataSource;

	public Health isDatabaseUp() throws Exception {
		DataSourceHealthIndicator d = new DataSourceHealthIndicator(dataSource);
		Health h = new Health();
		h.setStatus(d.health().getStatus().toString());
		return h;
		
	}

}
