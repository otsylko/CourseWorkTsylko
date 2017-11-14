package by.aqa.task_2;
import by.aqa.task_3.Calc;
import java.net.URL;
import java.util.List;

public class Start {
    public static void main (String [] args){
        URL path = Start.class.getClass().getResource("/config2.xml");
        Calc test = new Calc();
        List<String> list = test.createList(path);
        System.out.println(list);
        Calc calc1 = new Calc();
        System.out.println(calc1.count(list.get(0)));

    }
}
