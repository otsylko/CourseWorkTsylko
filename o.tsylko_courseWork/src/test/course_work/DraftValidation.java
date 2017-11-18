package course_work;

import course_work.forms.*;
import miniSmart.webdriver.BaseTest;
import miniSmart.webdriver.Browser;

public class DraftValidation extends BaseTest {
    public void runTest() {
        logger.step(1);
        logger.info("Authorization");
        LoginForm loginForm = new LoginForm();
        loginForm.doLogin(Browser.userName, Browser.userPassword);

        logger.step(2);
        logger.info("Populate email form and save as Draft");
        MailBox mailBox = new MailBox();
        mailBox.openNewMessageForm();
        NewEmailCreationForm newDraft = new NewEmailCreationForm();
        newDraft.populateEmailFields(Browser.userEmail, "draft creation", Browser.emailBody);
        newDraft.btAdditionAction.click();
        newDraft.selectItem(NewEmailCreationForm.AdditionalOptions.SAVEasDRAFT);

        logger.step(3);
        logger.info("Validate that Draft is saved ");
        Folder folder = new Folder();
        if (folder.getLoc()!=null) {
            mailBox.selectItem(MailBox.UserFolders.DRAFTS);
        }
        else {
            mailBox.lbUserFolders.click();
            mailBox.selectItem(MailBox.UserFolders.DRAFTS);
        }
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("draft creation").click();

        logger.step(4);
        logger.info("Validate the body of saved Draft");
        NewEmailCreationForm savedDraft = new NewEmailCreationForm();
        savedDraft.validBodyOfEmail(Browser.emailBody);

        logger.step(5);
        logger.info("Update the body of saved Draft and Submit");
        savedDraft.populateEmailFields("test draft updated");
        savedDraft.btSend.click();

        logger.step(6);
        logger.info("Validate that email is received");
        mailBox.selectItem(MailBox.UserFolders.INBOX);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("draft creation");

        logger.step(7);
        logger.info("Clean Data");
        mailBox.selectItem(MailBox.UserFolders.INBOX);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.deleteEmail(folder.isEmailPresent("draft creation"));
        mailBox.selectItem(MailBox.UserFolders.SENT);
        folder.deleteEmail(folder.isEmailPresent("draft creation"));
        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.deleteEmail(folder.isEmailPresent("draft creation"));
    }
}
