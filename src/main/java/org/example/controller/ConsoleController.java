package org.example.controller;

import org.example.dto.Product;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleController {
    public static String getFileNameFromConsole(String fileType) throws IOException {
        System.out.println("Please enter file name with " + fileType + " extension (e.g. computer_parts.xml)");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String input = reader.readLine();

        if (!input.contains(fileType)) {
            System.out.println("File name must contain ["+ fileType +"] file extension. Please try again!");
            return getFileNameFromConsole(fileType);
        }
        return input;
    }

    public static void validateXMLagainstXSD(String filename) throws SAXException {
        System.out.println("Validating XML file against XSD schema...");
        SchemaFactory factory2 = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory2.newSchema(new File(
                filename.replaceAll(".xml", ".xsd")
        ));
        Validator validator = schema.newValidator();
        try {
            validator.validate(new StreamSource(new File(
                    filename
            )));
            System.out.println("=> XML file is valid.");
        } catch (SAXException | IOException e) {
            System.out.println("=> XML file is not valid: " + e.getMessage());
        }
    }

    public static List<String> getXPathNames() throws IOException {
        List<String> result = new ArrayList<>();
        System.out.println("Enter 1 if you want to input custom XPath");
        System.out.println("Enter any character if you want to go with 5 predefined XPaths");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String input = reader.readLine();

        if (input.equals("1")) {
            BufferedReader reader2 = new BufferedReader(
                    new InputStreamReader(System.in));
            String input2 = reader2.readLine();
            result.add(input2);
            return result;
        }

        // 5 XPaths as per task requirements
        result.add("/categories/category/@type");
        result.add("/categories/category/products/product/name/.");
        result.add("/categories/category/products/product/price[amount>500]");
        result.add("/categories/category/products/product/onSales/."); ///categories/category/products/product[onSales='Y']
        result.add("/categories/category/products/product[1]/name/.");
        return result;
    }
}
