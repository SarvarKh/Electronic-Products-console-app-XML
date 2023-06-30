package org.example.controller;

import org.example.dto.Product;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.List;

public class ConsoleController {
    public static String getFileNameFromConsole(String fileType) throws IOException {
        System.out.println("Please enter file name with " + fileType + " extension");

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
}
