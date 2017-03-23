package boeing.app.template.webservice.api.rest.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Abstract Filter class to provide hook-in methods for before and after requests
 * 
 * @author bx642d
 *
 */
public abstract class AbstractRequestResponseFilter implements Filter {

	public abstract void beforeRequest(HttpServletRequest request);

	public abstract void afterRequest(HttpServletRequest request, HttpServletResponse response);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		return;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		beforeRequest((HttpServletRequest) request);
		chain.doFilter(request, response);
		afterRequest((HttpServletRequest) request, (HttpServletResponse) response);
	}

	@Override
	public void destroy() {
		return;
	}

}
