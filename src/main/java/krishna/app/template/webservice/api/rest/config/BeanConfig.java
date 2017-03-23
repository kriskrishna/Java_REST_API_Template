package boeing.app.template.webservice.api.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Configuration class to store custom bean definition
 * 
 * @author bx642d
 *
 */
@Configuration
public class BeanConfig {
	
	@Bean
	public RequestResponseLoggingFilter setRequestResponseLoggingFilter() {
		return new RequestResponseLoggingFilter();
	}
	
}
