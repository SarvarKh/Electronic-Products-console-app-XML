package org.example;

import org.example.controller.ConsoleController;
import org.example.dao.SAXProductHandler;
import org.example.dto.Product;
import org.example.service.XmlValidator;

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
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");
        String inputXml = ConsoleController.getFileNameFromConsole(".xml");
        String filename = DATADIR + inputXml; //"computer_parts.xml";

        // Validate XML against DTD
//        ConsoleController.validateXmlFileAgainstDTD(filename);
        boolean valid = new XmlValidator(inputXml, inputXml.replaceAll(".xml", ".dtd")).isValid();
        System.out.println("Boolean: " +valid);

        // Parse XML file
        SAXProductHandler saxHandler = new SAXProductHandler();
        List<Product> data = saxHandler.readDataFromXML(filename);
        boolean dataExist = ConsoleController.validateXmlFile(data, ".xml");
//        System.out.println("Number of products: " + data.size());

        // Print products
        for (Product product: data) {
            System.out.println(product);
        }

        // Transform XML into plain text file
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(DATADIR + "computer_parts.xsl"));
        transformer.transform(new StreamSource(DATADIR + "computer_parts.xml"), new StreamResult(new FileOutputStream(new File("output.txt"))));
    }
}
