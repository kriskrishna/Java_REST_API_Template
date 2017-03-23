package boeing.app.template.webservice.api.rest.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 
 * Customize the object mapper used by hibernate to de-/serialize models
 * 
 * @author bx642d
 *
 */
@Primary
@Component
public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 6372187646655711366L;

	public HibernateAwareObjectMapper() {
		setSerializationInclusion(JsonInclude.Include.ALWAYS);
		/*
		Hibernate4Module hibernate4Module = new Hibernate4Module();
		hibernate4Module.configure(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
		hibernate4Module.configure(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION, false);
		registerModule(hibernate4Module);
 		*/
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.configure(SerializationFeature.INDENT_OUTPUT, true);
	}
}

