package course_work;

import course_work.forms.*;
import miniSmart.webdriver.BaseTest;
import miniSmart.webdriver.Browser;

public class EmailAddressValidation extends BaseTest {
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
        logger.info("Validation of email addresses field");
        NewEmailCreationForm validAddress = new NewEmailCreationForm();
        validAddress.populateEmailFields("otsylko", "test email address", Browser.emailBody);
        validAddress.btSend.click();
        validAddress.assertPopUpErr();
        validAddress.txbAddressField.setText(Browser.userEmail);
        validAddress.btSend.click();

        logger.step(4);
        logger.info("Validation that email is received");
        Folder folder = new Folder();
        if (folder.getLoc()!=null) {
            mailBox.selectItem(MailBox.UserFolders.INBOX);
        }
        else {
            mailBox.lbUserFolders.click();
            mailBox.selectItem(MailBox.UserFolders.INBOX);
        }
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("test email address");

        logger.step(5);
        logger.info("Clean Data");
        mailBox.selectItem(MailBox.UserFolders.INBOX);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.deleteEmail(folder.isEmailPresent("test email address"));
        mailBox.selectItem(MailBox.UserFolders.SENT);
        folder.deleteEmail(folder.isEmailPresent("test email address"));
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.deleteEmail(folder.isEmailPresent("test email address"));
    }
}
