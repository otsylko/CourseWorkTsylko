package by.aqa.task_3;

import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class TestList {
    private Calc calc = new Calc();
    URL path = TestList.class.getClass().getResource("/configtest.xml");

    @BeforeClass
    public void startTest()throws Exception{
        System.out.println("Start TestList validation");
    }
    @BeforeMethod(enabled = false)
    public void setUp() throws Exception {
        System.out.println("Start method");
    }
    @AfterMethod(enabled = false)
    public void tearDown() throws Exception {
        System.out.println("AfterMethod");
    }
    @Test
    public void testCreateList() throws Exception {
        System.out.println("test Create List");
        Assert.assertNotNull(String.valueOf(calc.createList(path)), "List is null!");
    }
}
