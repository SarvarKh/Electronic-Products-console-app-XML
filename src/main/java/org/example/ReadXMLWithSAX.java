package org.example;

import org.example.controller.ConsoleController;
import org.example.dao.XQueryCategoryDAO;
import org.example.service.XMLService;

import java.util.List;


public class ReadXMLWithSAX {
    public static final String DATADIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");
        // 0. Select options from Menu
        Integer optionNumber = ConsoleController.getOptionNumber();

        switch (optionNumber) {
            case 1:
                // 1.1 Get file name from console
                String xmlFileName = ConsoleController.getFileNameFromConsole(".xml");
                String xmlFileNamePath = DATADIR + xmlFileName; // computer_parts.xml
                // 1.2 Validate XML against XSD
                ConsoleController.validateXMLagainstXSD(xmlFileNamePath);
                break;
            case 2:
                // 2. Transform XML into plain text file
                XMLService.transformXMLtoText(DATADIR + "computer_parts.xsl", DATADIR + "computer_parts.xml");
                break;
            case 3:
                // 3.1 Get XPath names
                List<String> xPaths = ConsoleController.getXPathNames();
                // 3.2 Transform XML into plain text files based on Xpath
                XMLService.transformXMLtoTextBasedOnXPath(xPaths);
                break;
            case 4:
                // 4.1 Get XSL file name from console
                String xslFileName = ConsoleController.getFileNameFromConsole(".xsl"); // computer_parts_transform_1.xsl
                // 4.2 Transform XML into plain text files based on XSL
                XMLService.transformXMLtoAnotherFileBasedOnXSL(xslFileName);
                break;
            default:
                System.out.println("Sorry, this option is not available currently");
        }


        System.out.println("Ending application...\n-=-=-=-=-*****-=-=-=-=-");
    }
}
