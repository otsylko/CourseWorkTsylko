package by.aqa.task_3;


import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

public class TestZeroUnit6 {
    private Calc calc = new Calc();

    @BeforeClass
    public void startTest()throws Exception{
        System.out.println("Start TestZeroUnit6");
    }
    @BeforeMethod (enabled = false)
    public void setUp() throws Exception {
        System.out.println("Before method");
    }
    @AfterMethod (enabled = false)
    public void tearDown() throws Exception {
        System.out.println("AfterMethod");
    }
    @Test
    @Parameters({"expressionZeroMult", "resZero1"})
    public void testZeroForMult(String expression, double exResult) throws Exception {
        Assert.assertEquals(calc.count(expression),exResult);
        System.out.println("test count: Zero valid for multipl");
    }
    @Test
    @Parameters({"expressionZeroDivZero", "resZero2"})
    public void testZeroForDiv(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: Zero valid for division");
    }
    @Test(dataProvider = "calculatorDataProvider1", dataProviderClass = DataproviderClass.class)
    public void testZeroForSumAndSub(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: Zero valid for sum and sub");
    }
    @Test
    @Parameters({"expressionZeroDivOnZero", "resZero3"})
    public void testZeroForDivOnZero(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: Zero valid for div ON Zero!");
    }
}
