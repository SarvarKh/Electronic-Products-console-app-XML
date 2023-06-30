package org.example;

import org.example.controller.ConsoleController;
import org.example.dao.SAXProductHandler;
import org.example.dto.Product;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ReadXMLWithSAX {
    public static final String DATADIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");
        String inputXml = ConsoleController.getFileNameFromConsole(".xml");
        String filename = DATADIR + inputXml; //"computer_parts.xml";

        // Validate XML against XSD
        System.out.println("Validating XML file against XSD schema...");
        SchemaFactory factory2 = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory2.newSchema(new File("src/main/resources/computer_parts.xsd"));
        Validator validator = schema.newValidator();
        try {
            validator.validate(new StreamSource(new File("src/main/resources/computer_parts.xml")));
            System.out.println("=> XML file is valid.");
        } catch (SAXException e) {
            System.out.println("=> XML file is not valid: " + e.getMessage());
        }

        // Parse XML file
        SAXProductHandler saxHandler = new SAXProductHandler();
        List<Product> data = saxHandler.readDataFromXML(filename);
        System.out.println("Number of products: " + data.size());


        // Transform XML into plain text file
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(DATADIR + "computer_parts.xsl"));
        transformer.transform(new StreamSource(DATADIR + "computer_parts.xml"), new StreamResult(new FileOutputStream(new File("output.txt"))));
        System.out.println("=> XML document has been transformed into plain text format (.txt).");
        System.out.println("output.txt file has been created in project's root directory");
    }
}
