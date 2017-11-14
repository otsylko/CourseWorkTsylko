package task_5;

import miniSmart.webdriver.BaseTest;
import task_5.forms.*;


public class TestTask5 extends BaseTest {

    public void runTest(){
        logger.step(1);
        MainPage mPage = new MainPage();
        mPage.assertLogo();

        logger.step(2);
        MainPage.MyMenu.GAMES.selectMenu(MainPage.MyMenu.ACTION);

        logger.step(3);
        ActionPage aPage = new ActionPage();
        aPage.assertTabBar();

        logger.step(4);
        aPage.selectTab(ActionPage.Tab.SPECIALS);
        aPage.assertTableResults();
        aPage.result.selectItemwithMaxDisc();

        logger.step(5);
        AgeValidWind ageVal = new AgeValidWind();
        if(ageVal.getLoc()!=null) {
            AgeValidWind.openDetails.click();
        }

        logger.step(6);
        AgeSpecifPage ageSpecif = new AgeSpecifPage();
        if (ageSpecif.getLoc()!=null) {
            ageSpecif.ageDay.selectItem("16");
            ageSpecif.ageMonth.selectItem("May");
            ageSpecif.ageYear.selectItem("1986");
            AgeSpecifPage.submitDetails.click();
        }
        logger.step(7);
        GameDetails game = new GameDetails();
        game.assertDisc();
        game.assertPrice();
        game.checkDiscAndPrice();
        GameDetails.installSteam.click();

        logger.step(8);
        FileDownloadPage fileDownload = new FileDownloadPage();
        fileDownload.assertButton();
        fileDownload.downloadFile();
    }
}
