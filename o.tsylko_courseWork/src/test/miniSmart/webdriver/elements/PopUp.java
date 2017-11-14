package miniSmart.webdriver.elements;

import org.openqa.selenium.By;

public class PopUp extends BaseElement {
    public PopUp(final By locator, final String name) {
        super(locator, name);
    }
    public PopUp(String string, String name) {
        super(string, name);
    }
    public PopUp(By locator) {
        super(locator);
    }
    protected String getElementType() {
        return getLoc("loc.popUp");
    }
}
