package course_work;

import course_work.forms.*;
import miniSmart.webdriver.BaseTest;
import miniSmart.webdriver.Browser;

public class DeleteDraftValidation extends BaseTest {
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
        logger.info("Create new Draft");
        NewEmailCreationForm draftToDel = new NewEmailCreationForm();
        draftToDel.populateEmailFields(Browser.userEmail,"draft to delete",Browser.emailBody);
        draftToDel.btAdditionAction.click();
        draftToDel.selectItem(NewEmailCreationForm.AdditionalOptions.SAVEasDRAFT);

        logger.step(4);
        logger.info("Navigate to Draft section");
        Folder folder = new Folder();
        if (folder.getLoc()!=null) {
            mailBox.selectItem(MailBox.UserFolders.DRAFTS);
        }
        else {
            mailBox.lbUserFolders.click();
            mailBox.selectItem(MailBox.UserFolders.DRAFTS);
        }

        logger.step(5);
        logger.info("Delete the Draft and ensure that it is not present");
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.deleteEmail(folder.isEmailPresent("draft to delete"));
        folder.validEmailNotPresent("draft to delete");

        logger.step(6);
        logger.info("Validate that deleted draft is present in Deleted folder");
        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("draft to delete");

        logger.step(7);
        logger.info("Clean Data");
        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.deleteEmail(folder.isEmailPresent("draft to delete"));
    }
}
