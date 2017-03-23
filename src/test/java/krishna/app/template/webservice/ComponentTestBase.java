package boeing.app.template.webservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ComponentTestBase {

	@Autowired
	private ObjectMapper objectMapper;

	public String serializeJSON(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}
	
}
