package org.example;

import org.example.controller.ConsoleController;
import org.example.dao.XQueryCategoryDAO;
import org.example.service.XMLService;

import java.util.List;


public class ReadXMLWithSAX {
    public static final String DATADIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        // 1. Get file name from console
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");
        String xmlFileName = ConsoleController.getFileNameFromConsole(".xml");
        String xmlFileNamePath = DATADIR + xmlFileName; // computer_parts.xml

        // 2. Validate XML against XSD
        ConsoleController.validateXMLagainstXSD(xmlFileNamePath);

        // 3. Transform XML into plain text file
        XMLService.transformXMLtoText(DATADIR + "computer_parts.xsl", DATADIR + "computer_parts.xml");

        // 4.1 Get XPath names
        List<String> xPaths = ConsoleController.getXPathNames();

        // 4.2 Transform XML into plain text files based on Xpath
        XMLService.transformXMLtoTextBasedOnXPath(xPaths);

        // 5.1 Get XSL file name from console
        String xslFileName = ConsoleController.getFileNameFromConsole(".xsl"); // computer_parts_transform_1.xsl

        // 5.2 Transform XML into plain text files based on XSL
        XMLService.transformXMLtoAnotherFileBasedOnXSL(xslFileName);

        // 6. XQuery data from Postgresql DB
//        XQueryCategoryDAO.getCategories();

        System.out.println("Ending application...\n-=-=-=-=-*****-=-=-=-=-");
    }
}
