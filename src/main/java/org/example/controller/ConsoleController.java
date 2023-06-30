package org.example.controller;

import org.example.dto.Product;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
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

    public static boolean validateXmlFile(List<Product> data, String fileType) throws IOException {
        if (data.size() == 0) {
            System.out.println("There is no such data in "+fileType+" file");
            getFileNameFromConsole(fileType);
            return false;
        }
        return true;
    }


    public static void validateXmlFileAgainstDTD(String filename) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Create a new instance of the DocumentBuilderFactory class:
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Set the validation feature to true:
        factory.setValidating(true);

        // Create a new instance of the DocumentBuilder class:
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Set the ErrorHandler for the builder:
        builder.setErrorHandler(new ErrorHandler() {
            public void warning(SAXParseException e) throws SAXException {
                System.out.println("Warning: " + e.getMessage());
            }

            public void error(SAXParseException e) throws SAXException {
                System.out.println("Error: " + e.getMessage());
            }

            public void fatalError(SAXParseException e) throws SAXException {
                System.out.println("Fatal error: " + e.getMessage());
            }
        });

        // Parse the XML file:
        Document document = builder.parse(new File(filename)); // computer_parts.xml

        // Get the DocumentType object from the document:
        DocumentType docType = document.getDoctype();

        // Create a new instance of the DOMImplementationRegistry class:
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();

        // Get the DOMImplementationLS object from the registry:
        DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");

        // Create a new instance of the LSInput interface:
        LSInput input = impl.createLSInput();

        // Set the systemId and byteStream properties of the LSInput object:
        input.setSystemId(docType.getSystemId());
        input.setByteStream(new FileInputStream(filename.replaceAll(".xml", ".dtd")));

        // Create a new instance of the LSParser interface:
        LSParser parser = impl.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);

        // Set the validation feature to true:
        parser.getDomConfig().setParameter("validate", Boolean.TRUE);

        // Set the ErrorHandler for the parser:
        parser.getDomConfig().setParameter("error-handler", new DOMErrorHandler() {
            public boolean handleError(DOMError error) {
                System.out.println("Error: " + error.getMessage());
                return true;
            }
        });

        // Parse the LSInput object:
        parser.parse(input);
    }


    public static void validateXmlFileAgainstDTDConcise(String filename) throws ParserConfigurationException, IOException, SAXException {
        // Create a new instance of the DocumentBuilderFactory class:
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Set the validation feature to true:
        factory.setValidating(true);

        // Set the DTD validation feature to true:
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);

        // Create a new instance of the DocumentBuilder class:
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML file:
        Document document = builder.parse(new File(filename));
    }
}
