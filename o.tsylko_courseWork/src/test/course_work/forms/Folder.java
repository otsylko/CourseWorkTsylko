package course_work.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.elements.Button;
import miniSmart.webdriver.elements.Email;
import miniSmart.webdriver.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Folder extends BaseForm {
    private By locForSub = By.xpath("//span[contains(@class, \"_lvv_T\")]");//span[contains(@autoid, "_lvv_6")]
    private String pattern = "//span[contains(text(),'%s')]";
    private String patternFilter = "//div[contains(@class, \"_fce_z ms-font-s ms-fwt-sl\")]//span[contains(text(),'%s')]";//button[contains(@autoid, "_fce_7")]
    public Email email = new Email(locForSub, "email");
    public Button btDelete = new Button(By.xpath("//button[contains(@title, \"Удалить\")]"), "Delete button");
    public Label lbFilter = new Label(By.xpath("//span[contains(@class, \"_db_p2\")]"), "Filter");//button[contains(@autoid, "_db_s")]

    public Folder() {
        super(By.xpath("//div[contains(@class, \"subfolders\")]//span[contains(@autoid, \"_n_21\")]"));
    }
    public By getLoc() {
        return this.titleLocator;
    }
    public Email isEmailPresent(String subject) {
        if (!email.isEmailPresent(pattern, subject)){
            email.waitForIsElementPresent();
        }
        Assert.assertTrue(email.isEmailPresent(pattern, subject), "Error: Email is not received");
        email = new Email(By.xpath(String.format(pattern, subject)), "email");
        return email;
    }
    public void validEmailNotPresent(String subject) {
        Assert.assertFalse(email.isEmailPresent(pattern, subject), "Error: Email is present");
    }
    public void deleteEmail(Email em){
        em.mouseHover(em.getLocator());
        btDelete.click();
        browser.getDriver().navigate().refresh();
    }
    public enum FilterOptions {NOT_READ("Непрочтенные");
        private String uniqueLocator;
        FilterOptions(String uniqueLocator) {
            this.uniqueLocator = uniqueLocator;
        }
    }
    public void applyFilter(FilterOptions itemName) {
        lbFilter.click();
        Label option = new Label(By.xpath(String.format(patternFilter, itemName.uniqueLocator)), "Filter");
        option.click();
    }
    public void resetFilter() {
        lbFilter.click();
    }
    public void refreshSectionByFilter(FilterOptions itemName) {
        if (lbFilter.isPresent()) {
            applyFilter(itemName);
        }
        resetFilter();
    }
}
