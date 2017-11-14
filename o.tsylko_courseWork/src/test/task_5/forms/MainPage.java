package task_5.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.BrowserFactory;
import miniSmart.webdriver.elements.Label;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {
    Label mainLogo = new Label(By.xpath("//div[contains(@class, \"logo\")]"), "Logo");
    public MainPage(){
        super(By.xpath("//div[contains(@class, \"store_nav\")]"), "search form");
    }
    public void assertLogo(){
        assert(mainLogo.isPresent());
    }

    public enum MyMenu { ACTION(BrowserFactory.propResMang.getProperty("loc.submenu")),GAMES(BrowserFactory.propResMang.getProperty("loc.mainmenu"));
        private String pattern = "//a[contains(text(),'%s')]"; // "//a[@text()='%s']"
        private String uniqLocator;

        MyMenu(String uniqLocator) {
            this.uniqLocator = uniqLocator;
        }

        public void selectMenu(MyMenu itemName) {
            Label menu = new Label(By.xpath(String.format(pattern, this.uniqLocator, "menuItem")), "subMenuItem");
            menu.mouseHover(menu.getLocator());
            Label el = new Label(By.xpath(String.format(pattern, itemName.uniqLocator, "menuItem")), "subMenuItem");
            el.click();
        }
    }
}
