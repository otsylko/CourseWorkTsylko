package course_work;

import course_work.forms.*;
import miniSmart.webdriver.BaseTest;
import miniSmart.webdriver.Browser;

public class TestCourse extends BaseTest {
    public void runTest(){
        System.out.println("Test case: Validation of new email creation");
        logger.step(1);
        LoginForm loginForm = new LoginForm();
        loginForm.doLogin(Browser.userName, Browser.userPassword);

        logger.step(2);
        MailBox mailBox = new MailBox();
        mailBox.openNewMessageForm();

        logger.step(3);
        NewEmailCreationForm newEmail = new NewEmailCreationForm();
        newEmail.populateEmailFields(Browser.userEmail, "new email creation",Browser.emailBody);
        newEmail.btSend.click();

        logger.step(4);//Validation of email received
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
        mailBox.openNewMessageForm();
        NewEmailCreationForm newEmailCC = new NewEmailCreationForm();
        newEmailCC.populateEmailFields("ol53@yandex.ru", Browser.userEmail, "new email CC",Browser.emailBody);
        newEmailCC.btSend.click();

        logger.step(6);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("new email CC");

        System.out.println("Test case: Validation of Cancel button");
        logger.step(7);
        mailBox.openNewMessageForm();
        NewEmailCreationForm newEmailCancel = new NewEmailCreationForm();
        newEmailCancel.populateEmailFields(Browser.userEmail, "test cancel", Browser.emailBody);
        newEmailCancel.btCancel.click();
        newEmailCancel.assertPopUpConf();
        newEmailCancel.btCancelConf.click();

        logger.step(8);
        mailBox.selectItem(MailBox.UserFolders.SENT);
        folder.validEmailNotPresent("test cancel");

        System.out.println("Test case: Validation of draft creation");
        logger.step(9);
        mailBox.openNewMessageForm();
        NewEmailCreationForm newDraft = new NewEmailCreationForm();
        newDraft.populateEmailFields(Browser.userEmail, "draft creation", Browser.emailBody);
        newDraft.btAdditionAction.click();
        newDraft.selectItem(NewEmailCreationForm.AdditionalOptions.SAVEasDRAFT);

        logger.step(10);
        mailBox.selectItem(MailBox.UserFolders.DRAFTS);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("draft creation").click();
        NewEmailCreationForm savedDraft = new NewEmailCreationForm();
        savedDraft.validBodyOfEmail(Browser.emailBody);
        savedDraft.populateEmailFields("test draft updated");
        savedDraft.btSend.click();

        logger.step(11);
        mailBox.selectItem(MailBox.UserFolders.INBOX);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("draft creation");

        System.out.println("Test case: Validation of delete inbox email option");
        logger.step(12);
        mailBox.selectItem(MailBox.UserFolders.INBOX);
        folder.deleteEmail(folder.isEmailPresent("draft creation"));
        folder.validEmailNotPresent("draft creation");
        mailBox.selectItem(MailBox.UserFolders.DELETED);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.isEmailPresent("draft creation");

        System.out.println("Test case: Validation of delete draft option");
        logger.step(13);//pre-req: new draft to be created for Delete option validation
        mailBox.openNewMessageForm();
        NewEmailCreationForm draftToDel = new NewEmailCreationForm();
        draftToDel.populateEmailFields(Browser.userEmail,"draft to delete",Browser.emailBody);
        draftToDel.btAdditionAction.click();
        draftToDel.selectItem(NewEmailCreationForm.AdditionalOptions.SAVEasDRAFT);

        logger.step(14);
        mailBox.selectItem(MailBox.UserFolders.DRAFTS);
        folder.refreshSectionByFilter(Folder.FilterOptions.NOT_READ);
        folder.deleteEmail(folder.isEmailPresent("draft to delete"));
        folder.validEmailNotPresent("test draft to delete");

        System.out.println("Test case: Validation of fields for email addresses");
        logger.step(15);
        mailBox.openNewMessageForm();
        NewEmailCreationForm validAddress = new NewEmailCreationForm();
        validAddress.populateEmailFields("otsylko", "test email addresses", Browser.emailBody);
        validAddress.btSend.click();
        validAddress.assertPopUpErr();
        validAddress.txbAddressField.setText(Browser.userEmail);
        validAddress.btSend.click();
    }
}
