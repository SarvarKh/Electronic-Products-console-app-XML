package org.example;

import org.example.controller.ConsoleController;
import org.example.service.XMLService;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


public class ReadXMLWithSAX {
    public static final String DATADIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        // get File Name From Console
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");
        String inputXml = ConsoleController.getFileNameFromConsole(".xml");
        String filename = DATADIR + inputXml; //"computer_parts.xml";

        // Validate XML against XSD
        ConsoleController.validateXMLagainstXSD(filename);

        // get XPath names
        List<String> xPaths = ConsoleController.getXPathNames();

        // Transform XML into plain text file
        XMLService.transformXMLtoText(DATADIR + "computer_parts.xsl", DATADIR + "computer_parts.xml");

        // Transform XML into plain text files based on Xpath
        XMLService.transformXMLtoTextBasedOnXPath(xPaths);
        System.out.println("Ending application...\n-=-=-=-=-*****-=-=-=-=-");
    }
}
