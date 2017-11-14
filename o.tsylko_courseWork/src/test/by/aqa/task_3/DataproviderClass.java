package by.aqa.task_3;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataproviderClass {
    @DataProvider(name = "calculatorDataProvider1")
    public static Object[][] createData(Method m) {
       if (m.getName().equalsIgnoreCase("testSubstract")) {
            return new Object[][]{
                    {"1.00-1.00", 0.00},
                    {"9.00-8.00", 1.00},
            };
        }
        if (m.getName().equalsIgnoreCase("test5IncorrtFormatBoundVal")) {
            return new Object[][]{
                    {"-9.00*1.00", -9.0},
                    {"9.00*1.00", 9.0},
                    {"-10.00/1.00", 0.0},
                    {"10.00/1.00", 0.0},
            };
        }
        if (m.getName().equalsIgnoreCase("testRound")) {
            return new Object[][]{
                    {"8.00/9.00", 0.889},
                    {"7.00/8.00", 0.875},
                    {"5.00/6.00", 0.833},
            };
        }
        if (m.getName().equalsIgnoreCase("testZeroForSumAndSub")) {
            return new Object[][]{
                    {"8.00-0.00", 8.0},
                    {"3.00+0.00", 3.0},
                    {"0.00-6.00", -6.0},
            };
        }
        else return null;
    }
}

