package boeing.app.template.webservice.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import boeing.app.template.webservice.api.rest.config.SwaggerConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SwaggerE2E {
	
	@Autowired
	private SwaggerConfig swagConfig;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void swaggerJsonTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(swagConfig.getSwaggerJsonPath())
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	
	@Test
	public void swaggerUiTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get("/swagger-ui.html")
				.accept(MediaType.TEXT_HTML)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML_VALUE));
		
	}
	
	@Test
	public void swaggerResourcesTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get("/swagger-resources")
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	
	@Test
	public void configurationUiTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get("/configuration/ui")
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}

	@Test
	public void configurationSecurityTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get("/configuration/security")
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	
}
