package boeing.app.template.webservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	UnitTestSuite.class,
	IntegrationTestSuite.class,
	ComponentTestSuite.class,
	E2ETestSuite.class
})
public class AllTestSuite {

}
