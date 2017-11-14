package miniSmart.demo;
import miniSmart.demo.forms.TutSearchForm;
import miniSmart.webdriver.BaseTest;

public class DemoTest extends BaseTest {

	public void runTest() {
		logger.step(1);
		TutSearchForm tsf = new TutSearchForm();
		tsf.assertLogo();

		logger.step(2);
		tsf.searchFor("A1QA");

		logger.step(3);
		tsf.assertA1QAString();
	}
}


