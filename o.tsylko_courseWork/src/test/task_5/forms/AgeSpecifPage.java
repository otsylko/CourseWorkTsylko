package task_5.forms;


import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.elements.Button;
import miniSmart.webdriver.elements.DropDown;
import org.openqa.selenium.By;

public class AgeSpecifPage extends BaseForm {
    public DropDown ageDay = new DropDown(By.xpath("//select[contains(@name, \"ageDay\")]"), "select age");
    public DropDown ageMonth = new DropDown(By.xpath("//select[contains(@name, \"ageMonth\")]"), "select month");
    public DropDown ageYear = new DropDown(By.xpath("//select[contains(@name, \"ageYear\")]"), "select age");
    public static Button submitDetails = new Button(By.xpath("//a[contains(@class, \"btnv6_blue_hoverfade btn_small\")]"), "openDetailButton");
    public AgeSpecifPage(){
        super(By.xpath("//form[contains(@id, \"agecheck_form\")]"));
    }

    public By getLoc(){
        return this.titleLocator;
    }
}
