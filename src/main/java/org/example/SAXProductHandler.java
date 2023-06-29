package org.example;

import org.example.dto.Product;
import org.example.dto.util.Currency;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXProductHandler extends DefaultHandler {
    private List<Product> data;
    private Product product;
    private String currentElement = "";
    private StringBuilder currentText;

    public List<Product> readDataFromXML(String filename) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(new File(filename), this);
        return data;
    }

    @Override
    public void startDocument() throws SAXException {
//        System.out.println("Start document");
        data = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("Start element: " + qName);
        currentElement = qName;
        switch (currentElement) {
            case "categories":
            case "category":
            case "products":
            case "price":
                break;
            case "product":
                product = new Product();
                data.add(product);
            case "recommended":
                boolean recommended = Boolean.parseBoolean(attributes.getValue("value"));
                product.setRecommended(recommended);
            default:
                currentText = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("End element: " + qName);
        switch (currentElement) {
            case "categories":
            case "category":
            case "products":
            case "product":
            case "price":
                break;
            default:
                String content = currentText.toString();
                switch (currentElement) {
                    case "name":
                        product.setName(content);
                        break;
                    case "currency":
                        product.setCurrency(Currency.valueOf(content));
                        break;
                    case "amount":
                        product.setAmount(Double.valueOf(content));
                        break;
                    case "availability":
                        product.setAvailability(Integer.parseInt(content));
                        break;
                    case "onSales":
                        product.setOnSales(content);
                        break;
                }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentText != null) {
            currentText.append(ch, start, length);
        }
//        System.out.println("Characters");
    }
}
