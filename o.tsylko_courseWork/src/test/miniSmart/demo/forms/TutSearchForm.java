package miniSmart.demo.forms;

import miniSmart.webdriver.elements.Button;
import miniSmart.webdriver.elements.Label;
import miniSmart.webdriver.elements.TextBox;
import miniSmart.webdriver.BaseForm;

import org.openqa.selenium.By;

import org.testng.Assert;

import java.util.Date;

public class TutSearchForm extends BaseForm {

	private TextBox txbSearchBar = new TextBox(By.id("search_from_str"),"Search bar");
	private Label lbLogo = new Label(By.xpath("//img[@id='pageLogo']"),"tut.by logo");
	private Button btnSubmitSearch = new Button (By.xpath("//input[@name='search']"),"Search");
	private Label lblTesting = new Label(By.xpath("//a[contains(@href,'a1qa')]"),"A1QA");

	public TutSearchForm() {
		super(By.id("search_from_str"), "Tut by");
	}

	public void assertLogo(){
		assert(lbLogo.isPresent());
	}
	
    public void searchFor(String text) {
    	txbSearchBar.type(text);
    	btnSubmitSearch.click();
		browser.waitForPageToLoad();

    }
	
	public void assertA1QAString(){
		lblTesting.waitForIsElementPresent();
		Assert.assertTrue(lblTesting.isPresent());
	}
}
