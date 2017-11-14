package miniSmart.webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TableResults extends BaseElement {
    List <WebElement> listOfElements;
    public TableResults(final By locator, final String name) {
        super(locator, name);
    }

    public TableResults(String string, String name) {
        super(string, name);
    }

    public TableResults(By locator) {
        super(locator);
    }

    protected String getElementType() {
        return getLoc("loc.table");
    }

    public List<WebElement> getElements(By loc) {
        listOfElements = new ArrayList<>();
        super.locator = loc;
        isPresent();
        listOfElements = (new WebDriverWait(browser.getDriver(), 60))
                .until(new ExpectedCondition<List<WebElement>>(){
                    @Override
                    public List <WebElement> apply(WebDriver driver) {
                        return driver.findElements(loc);
                    }});
        return listOfElements;
    }
}
