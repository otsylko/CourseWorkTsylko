package course_work.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.elements.Button;
import miniSmart.webdriver.elements.TextBox;
import org.openqa.selenium.By;

public class LoginForm extends BaseForm {
    private final TextBox txbLogin = new TextBox(By.id("user_name"), "Username");
    private final TextBox txbPassword = new TextBox(By.id("password"), "Password");
    private final Button btnLogin = new Button(By.xpath("//input[contains(@type, \"submit\")]"), "Log on");

    public LoginForm() {
        super(By.id("user_name"), "login form");
    }

    public void doLogin(final String user, final String pwd) {
        txbLogin.setText(user);
        txbPassword.setText(pwd);
        btnLogin.click();
        browser.waitForPageToLoad();
    }
}
