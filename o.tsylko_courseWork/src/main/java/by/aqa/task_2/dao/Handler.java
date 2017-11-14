package by.aqa.task_2.dao;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {
    private List<String> stringrows;
    private StringBuilder builder;
    private String text;

    @Override
    public void startDocument() throws SAXException {
        stringrows = new ArrayList<String>();
    }

    @Override
    public void endDocument() throws SAXException {
        //System.out.println(stringrows);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        builder = new StringBuilder();
        text = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("string".equals(qName)) {
            stringrows.add(text);

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = builder.append(ch, start, length).toString().trim();
    }

    public List<String> getStringrows() {
        return stringrows;
    }
}
