package by.aqa.task_3;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

/*Шаг 2 - покрыть класс юнит тестами
Юнит тесты должны проверять требования, изложенные выше, то есть
1) Простой юнит тест, проверяющий работу с целыми числами и арифм. операциями
2) Юнит тест проверяющий работу с дробными числами
3) Юнит тест проверяющий работу с отрицательными числами
4) Юнит тест проверяющий округление результата
5) Юнит тест, проверяющий ограничение по формату чисел в выражении
6) Юнит тест, проверяющий корректность работы метода с числом 0*/
public class TestWholeNumbsUnit1 {

    private Calc calc = new Calc();

    @BeforeClass
    public void startTest()throws Exception{
        System.out.println("Start TestWholeNumbsUnit1 validation");
    }
    @BeforeMethod (enabled = false)
    public void setUp() throws Exception {
        System.out.println("Start method");
    }
    @AfterMethod (enabled = false)
    public void tearDown() throws Exception {
        System.out.println("AfterMethod");
    }
    @Test
    @Parameters({"expressionSum1", "resultSum1"})
    public void testSumOfEqualNumb(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: Sum of equal numbers");
    }
    @Test
    @Parameters({"expressionSum2", "resultSum2"})
    public void testSumOfDiffNumb(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: Sum of different numbers");
    }
    @Test
    @Parameters({"expressionSum3", "resultSum3"})
    public void testSumPermutNumb(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: Sum_permutation of numbers");
    }
    @Test (dataProvider = "calculatorDataProvider1", dataProviderClass = DataproviderClass.class)
    public void testSubstract(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: Subtraction of numbers (same/diff)");
    }
    @Test
    @Parameters({"expressionMult1", "resultMult1"})
    public void testMultiplEqualNumb(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: multiplication of equal numbers");
    }
    @Test
    @Parameters({"expressionMult2", "resultMult2"})
    public void testMultiplDiffNumb(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: multiplication of different numbers");
    }
    @Test
    @Parameters({"expressionMult3", "resultMult3"})
    public void testMultiplPermutNumb(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: Multiplication_permutation of numbers");
    }
    @Test
    @Parameters({"expressionDiv1", "resultDiv1"})
    public void testDevisionEqualNumb(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: division of equal numbers");
    }
    @Test
    @Parameters({"expressionDiv2", "resultDiv2"})
    public void testDevisionDiffNumb(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: division of different numbers");
    }
    @Test
    @Parameters({"expressionDiv3", "resultDiv3"})
    public void testDevisionOnOne(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: division by 1");
    }
    @Test
    @Parameters({"expressionDiv4", "resultDiv4"})
    public void testDevisionRemnant(String expression, double exResult) throws Exception {
        Assert.assertEquals(exResult, calc.count(expression), "Error!");
        System.out.println("test count: division with remnant");
    }
}