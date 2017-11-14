package task_5;

import miniSmart.webdriver.elements.Label;
import miniSmart.webdriver.elements.TableResults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class ActionResultTable extends TableResults {
    private List<WebElement> listOfgames;
    private List<WebElement> listOfDiscVal;
    private List<WebElement> listOfprices;
    {
        listOfgames = new ArrayList<>();
        listOfDiscVal = new ArrayList<>();
        listOfprices = new ArrayList<>();

    }
    private By locatorForGames = By.xpath("//div[contains(@id, \"DiscountsRows\")]/a[contains(@class, \"tab_item\")]");
    private By locatorForDiscount = By.xpath("//div[contains(@id, \"DiscountsRows\")]//div[contains(@class, \"discount_pct\")]");
    private By locatorForPrices = By.xpath("//div[contains(@id, \"DiscountsRows\")]//div[contains(@class, \"discount_final_price\")]");
    public static int maxDisc = 0;
    public static double priceOfMaxDisc = 0;

    public ActionResultTable(By loc, String nameOf) {
        super(loc, nameOf);
    }

    public String getElementType(){//TBU
        return this.name;
    }

    public void selectItemwithMaxDisc() {
        listOfgames = getElements(locatorForGames);
        listOfDiscVal = getElements(locatorForDiscount);
        listOfprices = getElements(locatorForPrices);
        int index = 0;
        for (WebElement disc: listOfDiscVal){
            String discMod = disc.getText().substring(1, disc.getText().length()-1);
            int discVal = Integer.parseInt(discMod);
            if (discVal > maxDisc){
                maxDisc = discVal;
                index = listOfDiscVal.indexOf(disc);
            }
        }
        getPrice(listOfprices.get(index));
        WebElement el =  listOfgames.get(index);
        waitForIsElementPresent();
        el.click();
        browser.waitForPageToLoad();
    }
    private Double getPrice(WebElement el){
        String priceMod = el.getText().replaceAll("[$A-z/s]", "");//("\\$", "")
        priceOfMaxDisc = Double.parseDouble(priceMod);
        return priceOfMaxDisc;
    }
    public static double getPrice(Label el){
        String priceMod = el.getText().replaceAll("[$A-z/s]", "");
        return Double.parseDouble(priceMod);
    }
    public static int getDisc(Label el) {
        String discMod = el.getText().substring(1, el.getText().length()-1);
        return Integer.parseInt(discMod);
    }

}