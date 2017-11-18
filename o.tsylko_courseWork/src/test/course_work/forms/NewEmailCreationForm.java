package course_work.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.elements.Button;
import miniSmart.webdriver.elements.Label;
import miniSmart.webdriver.elements.PopUp;
import miniSmart.webdriver.elements.TextBox;
import org.openqa.selenium.By;
import org.testng.Assert;

public class NewEmailCreationForm extends BaseForm {
    public TextBox txbAddressField = new TextBox(By.xpath("//input[contains(@aria-label, \"Кому\")]"), "Address field");
    public TextBox txtCopyField = new TextBox(By.xpath("//input[contains(@aria-label, \"Копия\")]"), "Copy field");
    public TextBox txtSubjectField = new TextBox(By.xpath("//input[contains(@placeholder, \"Добавьте тему\")]"), "Subject");
    public TextBox txtBodyOfEmail = new TextBox(By.xpath("//div[contains(@aria-label, \"Тело сообщения\")]"), "Body of email");
    public Button btSend = new Button(By.xpath("//button[contains(@aria-label, \"Отправить\")]"), "Send button");
    private PopUp ppMessErr = new PopUp(By.xpath("//span[contains(text(), 'Совпадения не найдены.')]"), "PopUp notification");
    private PopUp ppMessConf = new PopUp(By.xpath("//span[contains(text(), 'Удалить сообщение')]"), "PopUp notification");
    public Button btCancel = new Button(By.xpath("//div[contains(@class, \"_mcp_H2\")]//button[contains(@aria-label, \"Отменить\")]"), "Cancel button");
    public Button btCancelConf = new Button(By.xpath("//div[contains(@class, \"listview\")]//span[contains(text(), 'Отменить')]"), "Confirm Cancel button");
    public Button btAdditionAction = new Button(By.xpath("//button[contains(@class, \"_mcp_72 o365button\")]//span[contains(@class, \"_fc_3 owaimg ms-Icon--chevronDown ms-icon-font-size-21 ms-fcl-ns-b\")]"), "Additional Actions button");
    private String pattern = "//div[contains(@class, \"_fce_R ms-fwt-r ms-bgc-w\")]//span[contains(text(),'%s')]";
    public NewEmailCreationForm() {
        super(By.xpath("//div[contains(@class, \"_fce_e\")]"), "New Email creation page");
    }
    public void assertPopUpErr() {
        ppMessErr.waitForIsElementPresent();
        Assert.assertTrue(ppMessErr.isPresent());
    }
    public void assertPopUpConf() {
        ppMessConf.waitForIsElementPresent();
        Assert.assertTrue(ppMessConf.isPresent());
    }
    public void populateEmailFields(String emailAddress, String copy,  String subject, String body) {
        this.txbAddressField.setText(emailAddress);
        this.txtCopyField.setText(copy);
        this.txtSubjectField.setText(subject);
        this.txtBodyOfEmail.setText(body);
    }
    public void populateEmailFields(String emailAddress, String subject, String body) {
        this.txbAddressField.setText(emailAddress);
        this.txtSubjectField.setText(subject);
        this.txtBodyOfEmail.setText(body);
    }
    public void populateEmailFields(String body) {
        this.txtBodyOfEmail.setText(body);
    }
    public void validBodyOfEmail(String text) {
        Assert.assertTrue(txtBodyOfEmail.isPresent());
        Assert.assertTrue(txtBodyOfEmail.getText().equals(text), "Incorrect Body of Email");
    }
    public void selectItem(AdditionalOptions itemName) {
        Label el = new Label(By.xpath(String.format(pattern, itemName.uniqueLocator)), "Additional option");
        el.click();
    }
    public enum AdditionalOptions {SAVEasDRAFT("Сохранить черновик");
        private String uniqueLocator;
        AdditionalOptions(String uniqueLocator) {
            this.uniqueLocator = uniqueLocator;
        }
    }
}
