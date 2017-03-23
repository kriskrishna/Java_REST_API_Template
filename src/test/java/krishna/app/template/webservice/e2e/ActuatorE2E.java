package boeing.app.template.webservice.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActuatorE2E {

	@Autowired
	private MockMvc mockMvc;

	@Value("${endpoints.autoconfig.path}")
	private String autoConfigPath;
	
	@Value("${endpoints.beans.path}")
	private String beansPath;
	
	@Value("${endpoints.configprops.path}")
	private String configPropertiesPath;
	
	@Value("${endpoints.docs.path}")
	private String docsPath;
	
	@Value("${endpoints.dump.path}")
	private String dumpPath;
	
	@Value("${endpoints.env.path}")
	private String envPath;
	
	@Value("${endpoints.health.path}")
	private String healthPath;
	
	@Value("${endpoints.heapdump.path}")
	private String heapdumpPath;
	
	@Value("${endpoints.info.path}")
	private String infoPath;
	
	@Value("${endpoints.mappings.path}")
	private String mappingsPath;
	
	@Value("${endpoints.metrics.path}")
	private String metricsPath;
	
	@Value("${endpoints.trace.path}")
	private String tracePath;

	@Test
	public void AutoConfigTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(autoConfigPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}

	@Test
	public void BeansTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(beansPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}

	@Test
	public void ConfigPropertiesTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(configPropertiesPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	
	/* TODO : actuator docs
	@Test
	public void DocsTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(actConfig.getDocsPath())
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	*/

	@Test
	public void DumpTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(dumpPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}

	@Test
	public void EnvTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(envPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}

	@Test
	public void HealthTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(healthPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}

	@Test
	public void HeapdumpTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(heapdumpPath)
				.accept(MediaType.APPLICATION_OCTET_STREAM)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_OCTET_STREAM_VALUE));
		
	}

	@Test
	public void InfoTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(infoPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	
	/* TODO : actuator logfile
	@Test
	public void LogfileTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(actConfig.getLogfilePath())
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	*/
	
	@Test
	public void MappingsTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(mappingsPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}

	@Test
	public void MetricsTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(metricsPath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}

	@Test
	public void TraceTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(tracePath)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	
}
