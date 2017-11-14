package by.aqa.task_2.dao;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class DaoTest {
    public List<String> readFile(URL path) {
        List<String> test = new ArrayList<String>();
        try {
            Handler handler = new Handler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(String.valueOf(path));
            test = handler.getStringrows();
        } catch (SAXException e) {
            e.getException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return test;
    }
}
