package miniSmart.webdriver;

import miniSmart.webdriver.elements.Label;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Base form
 */
public abstract class BaseForm extends BaseEntity {
	/**
	 * @uml.property name="titleLocator"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	protected By titleLocator; // detect form opening locator
	/**
	 * @uml.property name="title"
	 */
	protected String title; // title of a form
	/**
	 * @uml.property name="name"
	 */
	protected String name; // full name of form that outputted to log, for example, "Form 'Login'"
	/**
	 * Contructor
	 * @param locator Locator
	 * @param formTitle Name
	 */
	protected BaseForm(final By locator, final String formTitle) {
		init(locator, formTitle);
		assertIsOpen();
	}
	//Added by Tsylko
	/**
	 * Contructor
	 * @param locator Locator
	 */
	protected BaseForm(final By locator){
		if (isFormPresent(locator)){
			titleLocator = locator;
		}
	}


	/** Contructor
	 * @param formlocator formlocator
	 * @param formTitle formTitle
	 */
	public BaseForm(final String formlocator, final String formTitle) {
		long before = new Date().getTime();
		title = formTitle;
		Label titlePicture = (Label) new Label(formlocator,title);
		try{
			Assert.assertTrue(titlePicture.isPresent());
			
			long openTime = new Date().getTime() - before;
			
				info(String.format(getLoc("loc.form.appears"), title) + String.format(" in %smsec",openTime));
			
		} catch (Throwable e) {
			fatal(String.format(getLoc("loc.form.doesnt.appears"), title));
		}
	}

	/**
	 * For logs
	 * @param message Message
	 * @return Message
	 */
	protected String formatLogMsg(final String message) {
		return message;
	}

    /**
     * In report: If "true": when opening page screenshot is taken
     * @return boolean
     */
    
	/**
	 * Init
	 * @param locator Locator
	 * @param formTitle Name
	 */
	private void init(final By locator, final String formTitle) {
		titleLocator = locator;
		title = formTitle;
		name = String.format(getLoc("loc.form") + " '%1$s'", this.title);
	}

	/**
	 * Check the opening form If the form is not open, the test stops working
	 */
	public void assertIsOpen() {
		long before = new Date().getTime();
		Label elem = new Label(titleLocator, title);
		try {
			elem.waitForIsElementPresent();
			long openTime = new Date().getTime() - before;
			info(String.format(getLoc("loc.form.appears"), title) + String.format(" in %smsec",openTime));
		} catch (Throwable e) {
			fatal(String.format(getLoc("loc.form.doesnt.appears"), title));
		}
	}


	//Added by Tsylko
	/**
	 * Check for is optional element present on the page.
	 * @return Is element present
	 */
	public boolean isFormPresent(By loc){
		boolean isPresent = false;
		List<WebElement> list =(new WebDriverWait(browser.getDriver(), 10))
				.until(new ExpectedCondition<List<WebElement>>(){
					@Override
					public List <WebElement> apply(WebDriver driver) {
						return driver.findElements(loc);
					}});
		if (list.size()>0){
			isPresent = true;
		}
		return isPresent;
	}
}
