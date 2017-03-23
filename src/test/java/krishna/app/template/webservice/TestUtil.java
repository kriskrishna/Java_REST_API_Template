package boeing.app.template.webservice;

import java.util.Random;
import java.util.UUID;

public final class TestUtil {
	
	private static Random r = new Random();
	
	public static String dummyString() {
		return UUID.randomUUID().toString();
	}
	
	public static Integer dummyInteger() {
		return r.nextInt();
	}
	
}
