package boeing.app.template.webservice.api.rest.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Simple Filter implementation to log requests and responses
 * 
 * @author bx642d
 *
 */
public class RequestResponseLoggingFilter extends AbstractRequestResponseFilter {

	protected static final Logger logger = LoggerFactory.getLogger(AbstractRequestResponseFilter.class);

	public void beforeRequest(HttpServletRequest request) {
		logger.debug("{} >> {} {}", request.getRemoteAddr(), request.getMethod(), request.getRequestURL());
	}

	public void afterRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("{} << {} {} {}", request.getRemoteAddr(), request.getMethod(), request.getRequestURL(), response.getStatus());
	}

}
