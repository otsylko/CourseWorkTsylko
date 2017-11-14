package by.aqa.task_3;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestNegativeNumbsUnit3 {
    private Calc calc = new Calc();

    @BeforeClass
    public void startTest()throws Exception{
        System.out.println("Start TestNegativeNumbsUnit3 validation");
    }

    @BeforeMethod(enabled = false)
    public void setUp() throws Exception {
        System.out.println("Before method");
    }
    @AfterMethod(enabled = false)
    public void tearDown() throws Exception {
        System.out.println("AfterMethod");
    }
    @Test
    @Parameters({"expressionNeg1", "resultNeg1"})
    public void test3NegatSum1(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Sum of Negative numbers (-a+-b)");
    }
    @Test
    @Parameters({"expressionNegN", "resultNegN"})
    public void test3NegatSum2(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Sum of Negative numbers (a+-b)");
    }
    @Test
    @Parameters({"expressionNeg0", "resultNeg0"})
    public void test3NegatSum3(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Sum of Negative numbers (-a+b)");
    }
    @Test
    @Parameters({"expressionNeg", "resultNeg"})
    public void test3NegatSub1(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Subtraction of Negative numbers (-a-b)");
    }
    @Test
    @Parameters({"expressionNeg2", "resultNeg2"})
    public void test3NegatSub2(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Subtraction of Negative numbers (-a--b)");
    }
    @Test
    @Parameters({"expressionNegN2", "resultNegN2"})
    public void test3NegatSub3(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Subtraction of Negative numbers (a--b)");
    }
    @Test
    @Parameters({"expressionNeg3", "resultNeg3"})
    public void test3NegatMultOneMin(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Multiplication of Negative numbers: one min (-a*b)");
    }
    @Test
    @Parameters({"expressionNeg7", "resultNeg7"})
    public void test3NegatMultOneMin2(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Multiplication of Negative numbers: one min (a*-b)");
    }
    @Test
    @Parameters({"expressionNeg4", "resultNeg4"})
    public void test3NegatMultTwoMin(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Multiplication of Negative numbers: two min");
    }

    @Test
    @Parameters({"expressionNeg5", "resultNeg5"})
    public void test3NegatDivOneMin(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Division of Negative numbers: one min (-a/b)");
    }
    @Test
    @Parameters({"expressionNeg8", "resultNeg8"})
    public void test3NegatDivOneMin2(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Division of Negative numbers: one min (a/-b)");
    }
    @Test
    @Parameters({"expressionNeg6", "resultNeg6"})
    public void test3NegatDivTwoMin(String expression, double result) throws Exception {
        Assert.assertEquals(calc.count(expression), result, "Error!");
        System.out.println("test count: Division of Negative numbers: two min");
    }
}
