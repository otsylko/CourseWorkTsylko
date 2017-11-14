package by.aqa.task_3;
import by.aqa.task_2.dao.DaoTest;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Шаг 1 - реализовать класс калькулятор (Calc) с конструктором и одним методом count(String expression)
expression - строка в виде арифметического действия, например 2.00+3.00
Метод count должен вернуть результат выполнения действия
Требования:
Работа с дробными числами
Числа в выражении - только от -10 до 10 (не включая 10 и -10), формат записи - два числа после точки (то есть точность - до сотых)
Результат выполнения - округлить до 3 чисел после точки (до тысячных).
Реализованы должны быть 4 действия - сложение, вычитание, деление, умножение
*/
public class Calc {
    private List<String> list;
    private Pattern p;
    private Matcher m;
    public Calc(){
    }
    public List<String> createList(URL path){
        DaoTest dao = new DaoTest();
        list = dao.readFile(path);
        return list;
    }
    private boolean checkFormat(String expression){
        boolean ifCorrect = false;
        p = Pattern.compile("^[-]?[0-9][.][0-9]{2}[+*/-][-]?[0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (!m.matches()){
            System.out.println("Incorrect format of expression");
            ifCorrect = false;
        }
        else ifCorrect = true;
        return ifCorrect;
    }
    public double count(String expression){
        double result = 0;
        if (checkFormat(expression)) {
            String[] array = expression.split("[+/*-]");
            String[] arr = new String[array.length];
            int j = 0;
            for (int i = 0; i < array.length; i++) {
                if (!array[i].equals("")) {
                    arr[j] = array[i];
                    j++;
                }
            }
            double val1 = Double.parseDouble(arr[0]);
            double val2 = Double.parseDouble(arr[1]);
            Pattern p1 = Pattern.compile("[*/]");
            Pattern p2 = Pattern.compile("[/]");
            Matcher m1 = p1.matcher(expression);
            Matcher m2 = p2.matcher(expression);
            if (m1.find()) {
                if (m2.find()) {
                    if (val2 != 0) {
                        result = division(val1, val2, expression);
                    } else {
                        System.out.println("Divider can not be zero!");
                    }
                } else {
                    result = multiplication(val1, val2, expression);
                }
            } else {
                result = sum(val1, val2, expression);
            }
        }
        return Math.round(result * 1000.0) / 1000.0;
    }

    private double sum(double val1, double val2, String expression){
        double result = 0;
        p = Pattern.compile("^[0-9][.][0-9]{2}[+][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()) {
            result = val1+val2;
        }
        p = Pattern.compile("^[0-9][.][0-9]{2}[+][-][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()) {
            result = val1-val2;
        }
        p = Pattern.compile("^[-][0-9][.][0-9]{2}[+][-][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()){
            result = -(val1+val2);
        }
        p = Pattern.compile("^[-][0-9][.][0-9]{2}[+][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()){
            result = val2-val1;
        }
        p = Pattern.compile("^[0-9][.][0-9]{2}[-][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()) {
            result = val1-val2;
        }
        p = Pattern.compile("^[-][0-9][.][0-9]{2}[-][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()) {
            result = -val1-val2;
        }
        p = Pattern.compile("^[-][0-9][.][0-9]{2}[-][-][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()){
            result = val2-val1;
        }
        p = Pattern.compile("^[0-9][.][0-9]{2}[-][-][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()){
            result = val1+val2;
        }
        return result;
    }
    private double multiplication(double val1, double val2, String expression){
        double result = 0;
        p = Pattern.compile("^[0-9][.][0-9]{2}[*][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()){
            result = val1*val2;
        }
        p = Pattern.compile("^[-][0-9][.][0-9]{2}[*][-][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()){
            result = val1*val2;
        }
        p = Pattern.compile("^[-][0-9][.][0-9]{2}[*][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()){
            result = -(val1*val2);
        }
        p = Pattern.compile("^[0-9][.][0-9]{2}[*][-][0-9][.][0-9]{2}");
        m = p.matcher(expression);
        if (m.matches()){
            result = -(val1*val2);
        }
        return result;
    }
   private double division(double val1, double val2, String expression) {
       double result = 0;
       p = Pattern.compile("^[0-9][.][0-9]{2}[/][0-9][.][0-9]{2}");
       m = p.matcher(expression);
       if (m.matches()) {
           result = val1/val2;
       }
       p = Pattern.compile("^[-][0-9][.][0-9]{2}[/][-][0-9][.][0-9]{2}");
       m = p.matcher(expression);
       if (m.matches()) {
           result = val1/val2;
       }
       p = Pattern.compile("^[-][0-9][.][0-9]{2}[/][0-9][.][0-9]{2}");
       m = p.matcher(expression);
       if (m.matches()) {
           result = -(val1/val2);
       }
       p = Pattern.compile("^[0-9][.][0-9]{2}[/][-][0-9][.][0-9]{2}");
       m = p.matcher(expression);
       if (m.matches()) {
           result = -(val1/val2);
       }
       return result;
   }
}
