package task_5.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.elements.Button;
import org.openqa.selenium.By;

public class AgeValidWind extends BaseForm {
    public static Button openDetails = new Button(By.xpath("//a[contains(@class, \"btn_grey_white_innerfade btn_medium\")]"), "openDetailsButton");

    public AgeValidWind(){
        super(By.id("app_agegate"));
    }

    public By getLoc(){
        return this.titleLocator;
    }
}
