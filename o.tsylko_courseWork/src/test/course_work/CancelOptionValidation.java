package course_work;

import course_work.forms.*;
import miniSmart.webdriver.BaseTest;
import miniSmart.webdriver.Browser;

public class CancelOptionValidation extends BaseTest {
    public void runTest() {
        logger.step(1);
        logger.info("Authorization");
        LoginForm loginForm = new LoginForm();
        loginForm.doLogin(Browser.userName, Browser.userPassword);

        logger.step(2);
        logger.info("Open New Message form");
        MailBox mailBox = new MailBox();
        mailBox.openNewMessageForm();

        logger.step(3);
        logger.info("Validation of Cancel button");
        NewEmailCreationForm newEmailCancel = new NewEmailCreationForm();
        newEmailCancel.populateEmailFields(Browser.userEmail, "test cancel", Browser.emailBody);
        newEmailCancel.btCancel.click();
        newEmailCancel.assertPopUpConf();
        newEmailCancel.btCancelConf.click();

        logger.step(4);
        logger.info("Validation that email is not submitted after clicking Cancel");
        Folder folder = new Folder();
        if (folder.getLoc()!=null) {
            mailBox.selectItem(MailBox.UserFolders.SENT);
        }
        else {
            mailBox.lbUserFolders.click();
            mailBox.selectItem(MailBox.UserFolders.SENT);
        }
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.validEmailNotPresent("test cancel");
    }
}
