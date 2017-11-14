package by.aqa.task_3;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestFractNumbsUnit2 {
    private Calc calc = new Calc();

    @BeforeClass
    public void startTest()throws Exception{
        System.out.println("Start TestFractNumbsUnit2 validation");
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
    @Parameters({"expressionFrSum", "resultFrSum"})
    public void test2fractionalSum(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Sum of fractional numbers");
    }
    @Test
    @Parameters({"expressionFrSub", "resultFrSub"})
    public void testfractionalSub(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Subtraction of fractional numbers");
    }
    @Test
    @Parameters({"expressionFrMul", "resultFrMul"})
    public void testfractionalMul(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Multiplication of fractional numbers");
    }
    @Test
    @Parameters({"expressionFrDiv", "resultFrDiv"})
    public void testfractionalDiv(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Division of fractional numbers");
    }
}
