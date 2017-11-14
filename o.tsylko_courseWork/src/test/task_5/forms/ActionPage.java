package task_5.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.BrowserFactory;
import miniSmart.webdriver.elements.Label;
import org.openqa.selenium.By;
import task_5.ActionResultTable;

public class ActionPage extends BaseForm {
    private String pattern = "//div[contains(text(),'%s')]";
    private Label tabbar = new Label(By.xpath("//div[contains(@class, \"tabbar store_horizontal_minislider\")]"), "main tabbar");
    public ActionResultTable result = new ActionResultTable(By.id("DiscountsRows"), "result row");

    public ActionPage(){
        super(By.xpath("//div[contains(@class, \"tabbar_ctn\")]"), "tabbar");
    }

    public void assertTabBar(){
        assert(tabbar.isPresent());
    }

    public void assertTableResults (){//TBU
        assert(result.isPresent());
    }

    public void selectTab(Tab itemName){
        Label el = new Label(By.xpath(String.format(pattern, itemName.getUniqlocator(), "menuItem")),"gameItem");
        el.click();
        browser.waitForPageToLoad();
    }
    public enum Tab {SPECIALS(BrowserFactory.propResMang.getProperty("loc.tabname"));
        private String uniqlocator;

        public String getUniqlocator() {
            return uniqlocator;
        }

        Tab(String uniqlocator) {
            this.uniqlocator = uniqlocator;

        }
    }
}
