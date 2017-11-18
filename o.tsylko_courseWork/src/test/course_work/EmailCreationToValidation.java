package course_work;

import course_work.forms.*;
import miniSmart.webdriver.BaseTest;
import miniSmart.webdriver.Browser;

public class EmailCreationToValidation extends BaseTest {
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
        logger.info("Populate form and Submit");
        NewEmailCreationForm newEmail = new NewEmailCreationForm();
        newEmail.populateEmailFields(Browser.userEmail, "new email creation", Browser.emailBody);
        newEmail.btSend.click();

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
        folder.isEmailPresent("new email creation");

        logger.step(5);
        logger.info("Clean Data");
        mailBox.selectItem(MailBox.UserFolders.INBOX);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.deleteEmail(folder.isEmailPresent("new email creation"));
        mailBox.selectItem(MailBox.UserFolders.SENT);
        folder.deleteEmail(folder.isEmailPresent("new email creation"));
        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.deleteEmail(folder.isEmailPresent("new email creation"));


        /*logger.step(10);
        logger.info("Clean Data");
        mailBox.selectItem(MailBox.UserFolders.INBOX);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.deleteEmail(folder.isEmailPresent("new email creation"));
        folder.deleteEmail(folder.isEmailPresent("test email address"));
        folder.deleteEmail(folder.isEmailPresent("new email CC"));

        mailBox.selectItem(MailBox.UserFolders.SENT);
        folder.deleteEmail(folder.isEmailPresent("new email creation"));
        folder.deleteEmail(folder.isEmailPresent("test email address"));
        folder.deleteEmail(folder.isEmailPresent("new email CC"));

        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.deleteEmail(folder.isEmailPresent("new email creation"));
        folder.deleteEmail(folder.isEmailPresent("test email address"));
        folder.deleteEmail(folder.isEmailPresent("new email CC"));*/
    }
}
