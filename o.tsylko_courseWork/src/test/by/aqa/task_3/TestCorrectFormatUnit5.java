package by.aqa.task_3;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestCorrectFormatUnit5 {
    private Calc calc = new Calc();

    @BeforeClass
    public void startTest()throws Exception{
        System.out.println("Start TestCorrectFormatUnit5 validation");
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
    @Parameters({"expressionInct", "value"})
    public void test5IncorrtFormatSign(String expression, double exResult) throws Exception {
        Assert.assertEquals(calc.count(expression),exResult);
        System.out.println("test count: Format validation for Sign (++)");
    }
    @Test
    @Parameters({"expressionInct0", "value0"})
    public void test5IncorrtFormatSign2(String expression, double exResult) throws Exception {
        Assert.assertEquals(calc.count(expression),exResult);
        System.out.println("test count: Format validation for Sign(+-)");
    }
    @Test
    @Parameters({"expressionInct1", "value1"})
    public void test5IncorrtFormatSign3(String expression, double exResult) throws Exception {
        Assert.assertEquals(calc.count(expression),exResult);
        System.out.println("test count: Format validation for Sign (--before the first numb)");
    }
    @Test
    @Parameters({"expressionInct2", "value2"})
    public void test5IncorrtFormatChar(String expression, double exResult) throws Exception {
        Assert.assertEquals(calc.count(expression),exResult);
        System.out.println("test count: Format validation for Char");
    }
    @Test
    @Parameters({"expressionInct3", "value3"})
    public void test5IncorrtFormatNumbsAfterComma(String expression, double exResult) throws Exception {
        Assert.assertEquals(calc.count(expression),exResult);
        System.out.println("test count: Format validation for numbers after comma");
    }
    @Test(dataProvider = "calculatorDataProvider1", dataProviderClass = DataproviderClass.class)
    public void test5IncorrtFormatBoundVal(String expression, double exResult) throws Exception {
        Assert.assertEquals(calc.count(expression),exResult);
        System.out.println("test count: Format validation for bound values");
    }
}
