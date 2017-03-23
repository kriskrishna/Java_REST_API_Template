package boeing.app.template.webservice.api.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Configuration class to enable/configure Swagger plugin
 * 
 * @author bx642d
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	@Value("${springfox.documentation.swagger.v2.path}")
	private String swaggerJsonPath;

	@Bean
	public Docket api() {
		Docket d = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/api/**")).build();
		return d;
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/swagger/resources", "/swagger-resources");
		registry.addRedirectViewController("/swagger/ui", "/swagger-ui.html");
		registry.addRedirectViewController("/swagger/ui/", "/swagger-ui.html");
	}
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    registry
//	        .addResourceHandler("/documentation/**").addResourceLocations("classpath:/META-INF/resources/");
//	}

	public String getSwaggerJsonPath() {
		return swaggerJsonPath;
	}
}
