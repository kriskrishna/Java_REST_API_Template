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

import boeing.app.template.webservice.ComponentTestBase;
import boeing.app.template.webservice.api.rest.controller.AssetController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssetE2E extends ComponentTestBase {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllTest() throws Exception {
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(AssetController.PATH)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
		
	}
	
}
