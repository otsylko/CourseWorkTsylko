package course_work;

import course_work.forms.*;
import miniSmart.webdriver.BaseTest;
import miniSmart.webdriver.Browser;

public class DeleteEmailValidation extends BaseTest {
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
        logger.info("Create new Email");
        NewEmailCreationForm newEmail = new NewEmailCreationForm();
        newEmail.populateEmailFields(Browser.userEmail, "new email to delete", Browser.emailBody);
        newEmail.btSend.click();

        logger.step(4);
        logger.info("Navigate to Inbox section");
        Folder folder = new Folder();
        if (folder.getLoc()!=null) {
            mailBox.selectItem(MailBox.UserFolders.INBOX);
        }
        else {
            mailBox.lbUserFolders.click();
            mailBox.selectItem(MailBox.UserFolders.INBOX);
        }

        logger.step(5);
        logger.info("Delete existing email and ensure that it is not present");
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.deleteEmail(folder.isEmailPresent("new email to delete"));
        folder.validEmailNotPresent("new email to delete");

        logger.step(6);
        logger.info("Validate that deleted email is present in Deleted folder");
        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("new email to delete");

        logger.step(7);
        logger.info("Clean Data");
        mailBox.selectItem(MailBox.UserFolders.SENT);
        folder.deleteEmail(folder.isEmailPresent("new email to delete"));
        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.deleteEmail(folder.isEmailPresent("new email to delete"));
    }
}
