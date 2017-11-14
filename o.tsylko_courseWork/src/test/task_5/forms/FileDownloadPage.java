package task_5.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.Browser;
import miniSmart.webdriver.elements.Button;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import java.io.File;

public class FileDownloadPage extends BaseForm {
    public static Button download = new Button(By.xpath("//a[contains(@class, \"btn_medium btn_green_white_innerfade\")]"), "download");
    public FileDownloadPage(){
        super(By.xpath("//div[contains(@id, \"about_greeting_ctn\")]"), "download form");//a[contains(@class, "btn_medium btn_green_white_innerfade")]
    }
    public void assertButton(){
        assert(download.isPresent());
    }
    public void downloadFile(){
        download.click();
        browser.waitForLoad();
        File getLatestFile = getLatestFilefromDir(Browser.downloadPath);
        String actualFileName = getLatestFile.getName().toLowerCase();
        Assert.assertTrue(actualFileName.contains(Browser.fileName), "Downloaded file name is not matching with expected file name");

        //Second way
        /*File getLatestFile = getLatestFilefromDir(Browser.downloadPath);
            try {
                waitForDownloadToComplete(getLatestFile, Browser.fileName);
            }
            catch(Exception e){
                System.out.println("File Downloaded error");
            }
        String actualFileName = getLatestFile.getName().toLowerCase();
        Assert.assertTrue(actualFileName.contains(Browser.fileName), "Downloaded file name is not matching with expected file name");*/
    }

    private File getLatestFilefromDir(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }
        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

   /*public static File waitForDownloadToComplete(File downloadPath, String fileName) throws Exception {
        boolean isFileFound = false;
        int waitCounter = 0;
        while (!isFileFound) {
            logger.info("Waiting For Download To Complete....");
            for (File tempFile : downloadPath.listFiles()) {
                if (tempFile.getName().contains(fileName)) {
                    String tempEx = FilenameUtils.getExtension(tempFile.getName());
                    //crdownload - For Chrome, part - For Firefox
                    if (tempEx.equalsIgnoreCase("crdownload") || tempEx.equalsIgnoreCase("part")) {
                        Thread.sleep(1000);
                    } else {
                        isFileFound = true;
                        logger.info("Download To Completed....");
                        return tempFile;
                    }
                }
            }
            Thread.sleep(1000);
            waitCounter++;
            if (waitCounter > 25) {
                isFileFound = true;
            }
        }
        throw new Exception("File Not Downloaded");
   }*/
}
