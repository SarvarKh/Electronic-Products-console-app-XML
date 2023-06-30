package org.example;

import org.example.controller.ConsoleController;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;


public class ReadXMLWithSAX {
    public static final String DATADIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");
        String inputXml = ConsoleController.getFileNameFromConsole(".xml");
        String filename = DATADIR + inputXml; //"computer_parts.xml";

        // Validate XML against XSD
        ConsoleController.validateXMLagainstXSD(filename);

        // Transform XML into plain text file
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(DATADIR + "computer_parts.xsl"));
        transformer.transform(new StreamSource(DATADIR + "computer_parts.xml"), new StreamResult(new FileOutputStream(new File("output.txt"))));
        System.out.println("=> XML document has been transformed into plain text format (.txt).");
        System.out.println("=> output.txt file has been created in project's root directory");
        System.out.println("Ending application...\n-=-=-=-=-*****-=-=-=-=-");
    }
}
