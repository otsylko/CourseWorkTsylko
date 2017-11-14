package task_5.forms;

import miniSmart.webdriver.BaseForm;
import miniSmart.webdriver.elements.Button;
import miniSmart.webdriver.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;
import task_5.ActionResultTable;

public class GameDetails extends BaseForm {
    private Label disc = new Label(By.xpath("//div[contains(@class, \"game_purchase_action\")]//div[contains(@class, \"discount_pct\")]"));
    private Label price = new Label(By.xpath("//div[contains(@class, \"game_purchase_action\")]//div[contains(@class, \"discount_final_price\")]"));
    public GameDetails(){
        super(By.xpath("//div[contains(@class, \"game_area_purchase_game\")]"), "gameDetails");
    }
    public static Button installSteam = new Button(By.xpath("//a[contains(@class, \"header_installsteam_btn_content\")]"), "install Steam");

    public void assertDisc(){
        assert(disc.isPresent());
    }
    public void assertPrice(){
        assert(price.isPresent());
    }

    public void checkDiscAndPrice() {
        boolean b = false;
        double priceDetls = ActionResultTable.getPrice(price);
        int discountDetls = ActionResultTable.getDisc(disc);
        Assert.assertTrue(priceDetls == ActionResultTable.priceOfMaxDisc && discountDetls == ActionResultTable.maxDisc);
    }
}
