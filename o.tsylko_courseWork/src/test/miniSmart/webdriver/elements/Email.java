package miniSmart.webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Email extends BaseElement {//Экземпляр таблицы писем
    public Email(final By locator, final String name) {
        super(locator, name);
    }
    public Email(String string, String name) {
        super(string, name);
    }
    public Email(By locator) {
        super(locator);
    }
    protected String getElementType() {
        return getLoc("loc.email");
    }

    public boolean isEmailPresent(String pattern, String subject){
        boolean isPresent = false;
        List<WebElement> list =(new WebDriverWait(browser.getDriver(), 10))
                .until(new ExpectedCondition<List<WebElement>>(){
                    @Override
                    public List <WebElement> apply(WebDriver driver) {
                        return driver.findElements(By.xpath(String.format(pattern, subject)));
                    }});
        if (list.size()>0){
            isPresent = true;
        }
        return isPresent;
    }
}
