package org.example;

import org.example.dto.Product;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ReadXMLWithSAX {
    public static void main(String[] args) throws Exception {
        String filename = DataProvider.DATADIR + "computer_parts.xml";

        // Parse XML file
        SAXProductHandler saxHandler = new SAXProductHandler();
        List<Product> data = saxHandler.readDataFromXML(filename);
        System.out.println("Number of products: " + data.size());

        // Print products
        for (Product product: data) {
            System.out.println(product);
        }

        // Transform XML into plain text file
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(DataProvider.DATADIR + "computer_parts_transform.xsl"));
        transformer.transform(new StreamSource(DataProvider.DATADIR + "computer_parts.xml"), new StreamResult(new FileOutputStream(new File("output.txt"))));
    }
}
