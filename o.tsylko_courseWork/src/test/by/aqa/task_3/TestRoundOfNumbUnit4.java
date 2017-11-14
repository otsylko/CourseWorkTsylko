package by.aqa.task_3;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestRoundOfNumbUnit4 {
    private Calc calc = new Calc();

    @BeforeClass
    public void startTest()throws Exception{
        System.out.println("Start TestRoundOfNumbUnit4");
    }
    @BeforeMethod (enabled = false)
    public void setUp() throws Exception {
        System.out.println("Before method");
    }
    @AfterMethod(enabled = false)
    public void tearDown() throws Exception {
        System.out.println("AfterMethod");
    }
    @Test(dataProvider = "calculatorDataProvider1", dataProviderClass = DataproviderClass.class)
    public void testRound(String expression, double exResult) throws Exception {
        Assert.assertEquals(calc.count(expression),exResult);
        System.out.println("test count: Round");
    }
}
