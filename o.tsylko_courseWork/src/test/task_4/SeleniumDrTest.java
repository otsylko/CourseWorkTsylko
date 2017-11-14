package task_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.concurrent.TimeUnit.SECONDS;
/*Необходимо реализовать автотест по приведённому ниже сценарию
1.Перейти на www.tut.by - проверить что открыта главная страница Tut by
2.Перейти  в раздел «Работа» - проверить что совершён переход в раздел "Работа"
3.В поле поиска ввести «специалист по тестированию», нажать кнопку «Найти» - проверить что отображаются результаты поиска
Подсчитать, какое количество записей на странице с результатом поиска в соответствует заданному запросу (Т.е. «Тестировщик» - не соответствует, «Специалист по тестированию мобильных приложений» - соответствует, и т.д) - вывести в текстовый файл в формате: "дата запуска | количество результатов"

Требования:
Проверку переходов по страницам проверять не сравниванием URL, а каким-то элементом на экране (например, на главной странице сайта отображается логотип http://joxi.net/8234B9Zt65aLYA)
Автотест должен работать на 2 браузерах - chrome и firefox, выбор браузера - из параметров тест сьюта*/

public class SeleniumDrTest {
    private WebDriver driver;
    private static int count = 0;
    private Pattern p;
    private Matcher m;

    @BeforeClass
    @Parameters({"browser", "searchKey"})
    public void start(String browser, String searchKey) {
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        driver.get("https://www.tut.by/");
        List <WebElement> logoElems = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<List<WebElement>>(){
            @Override
            public List <WebElement> apply(WebDriver driver) {
                return driver.findElements(By.xpath("//div[contains(@class, \"l-i\")]"));
            }});
        Assert.assertTrue(logoElems.size()!=0);
        WebElement elLinkJob = driver.findElement(By.xpath("//li[contains(@class, \"topbar__li\")]/a[contains(@title, \"Работа\")]"));
        elLinkJob.click();
        List <WebElement> elSearchRests = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<List<WebElement>>(){
                    @Override
                    public List <WebElement> apply(WebDriver driver) {
                        return driver.findElements(By.xpath("//div[contains(@class, \"bloko-control-group\")]"));
                    }});
        Assert.assertTrue(elSearchRests.size()!=0);//проверить что совершён переход в раздел "Работа"
        WebElement elSearch = driver.findElement(By.xpath("//div[contains(@class, \"bloko-control-group__main\")]/input"));
        elSearch.sendKeys(searchKey);
        WebElement elButton = driver.findElement(By.xpath("//div[contains(@class, \"navi-search-button\")]"));
        elButton.click();
    }
    @Test
    @Parameters({"jobTitleRegEx"})
    public void searchResultsFirstPage(String jobTitleRegEx){
        System.out.println(1+"-----PAGE----");
        List<WebElement> searchRests = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<List<WebElement>>(){
            @Override
            public List <WebElement> apply(WebDriver driver) {
                return driver.findElements(By.xpath("//a[contains(@class, \"search-result-item__name\")]"));
            }});
        Assert.assertTrue(searchRests.size()!=0);//проверить что отображаются результаты поиска (поиск на первой странице результатов)
        for (WebElement el : searchRests) {
            p = Pattern.compile(jobTitleRegEx);
            m = p.matcher(el.getText());
            if (m.find()){
                count++;
                System.out.println(el.getText());
            }
        }
    }
    @Test
    @Parameters({"jobTitleRegEx"})
    public void searchResultsOtherPages(String jobTitleRegEx) throws InterruptedException {
        List <WebElement> allPages = driver.findElements(By.xpath("//ul/li[contains(@data-qa, \"pager-page\")]/a"));
        for (int i = 0; i < allPages.size(); i++) {
            System.out.println(i+2+"----NEW PAGE----");
            WebElement numOfNextPage = driver.findElement(By.xpath("//ul/li[contains(@data-qa, \"pager-page\")]/a[contains(@data-page, "+(i+1)+")]"));
            numOfNextPage.click();
            Thread.sleep(9000);
            List<WebElement> nextPageElms =(new WebDriverWait(driver, 10)).until(new ExpectedCondition<List<WebElement>>(){
                @Override
                public List <WebElement> apply(WebDriver driver) {
                    return driver.findElements(By.xpath("//a[contains(@class, \"search-result-item__name\")]"));
                }});
            Assert.assertTrue(nextPageElms.size()!=0);
            for (WebElement el : nextPageElms) {
                p = Pattern.compile(jobTitleRegEx);
                m = p.matcher(el.getText());
                if (m.find()){
                    count++;
                    System.out.println(el.getText());
                }
            }
        }
    }
    public void writeInFile(){
        try{
        FileWriter writer = new FileWriter("src/test/resources/results.txt", false);
            Date date = new Date();
            writer.write(date.toString()+" | " + count);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    @AfterClass
    public void writerResultsAndQuit(){
        writeInFile();
        driver.quit();
    }
}