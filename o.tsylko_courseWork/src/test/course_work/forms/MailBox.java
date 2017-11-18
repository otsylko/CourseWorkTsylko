package course_work.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.elements.Button;
import miniSmart.webdriver.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MailBox extends BaseForm {
    public Button btCreate = new Button(By.xpath("//button[contains(@title, \"Отобразить больше новых команд\")]"), "create email");
    public Button btNewMessage = new Button(By.xpath("//button[contains(@title, \"Создать новое сообщение (N)\")]"), "New message");
    public Label lbUserFolders = new Label(By.xpath("//span[contains(@class, \"_n_v4 treeNodeRowElement\")]//span[contains(@class, \"_n_u4 owaimg ms-Icon--chevronDown ms-icon-font-size-14 ms-fcl-ns-b\")]"), "User options");
    private String pattern = "//span[contains(text(),'%s')]";
    private String pattern2 = "//div[contains(@class, \"_n_j1 ms-fcl-ns\")]/span[contains(text(), '%s')]";

    public MailBox() {
        super(By.xpath("//div[contains(@class, \"_fce_e ms-bgc-tlr-h\")]"), "Mail box");
    }
    public void openNewMessageForm(){
        btCreate.click();
        btNewMessage.click();
        browser.waitForPageToLoad();
    }
    public void selectItem(UserFolders itemName) {
        Label folderName = new Label(By.xpath(String.format(pattern, itemName.uniqueLocator)), "folderName");
        folderName.click();
        Label sectionName = new Label(By.xpath(String.format(pattern2, itemName.uniqueLocator)), "sectionName");
        Assert.assertTrue(sectionName.isPresent());
    }
    public enum UserFolders { INBOX("Входящие"), DRAFTS("Черновики"), SENT("Отправленные"), DELETED("Удаленные");
        private String uniqueLocator;
        UserFolders(String uniqueLocator) {
            this.uniqueLocator = uniqueLocator;
        }
    }
}
