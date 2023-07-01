package org.example.service;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;
import java.io.*;
import java.util.List;

public class XMLService {

    public static void transformXMLtoText(String xslFilePath, String xmlFilePath) throws TransformerException, FileNotFoundException {
        System.out.println("Transforming XML to Text file...");
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslFilePath));
        transformer.transform(new StreamSource(xmlFilePath), new StreamResult(new FileOutputStream(new File("output.txt"))));
        System.out.println("=> XML document has been transformed into plain text format (.txt).");
        System.out.println("=> output.txt file has been created in project's root directory");
    }

    public static void transformXMLtoTextBasedOnXPath(List<String> xpaths) throws TransformerException, IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        System.out.println("Evaluating XPaths and transforming their results to separate Text file...");
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource("src/main/resources/computer_parts.xsl"));
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File("src/main/resources/computer_parts.xml"));
        for (int i = 0; i < xpaths.size(); i++) {
            XPathExpression expr = xpath.compile(xpaths.get(i));
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output" + (i+1) + ".txt"));
            for (int j = 0; j < nodes.getLength(); j++) {
                writer.write(nodes.item(j).getTextContent());
                writer.newLine();
            }
            writer.close();
        }
        System.out.println("=> "+ xpaths.size() +" XPaths have been evaluated and transformed into plain text format (.txt).");
        System.out.println("=> output.txt files have been created in project's root directory");
    }

    public static void transformXMLtoAnotherFileBasedOnXSL(String xslFileName) throws TransformerException, FileNotFoundException {
        String xslFilePath = "src/main/resources/"+xslFileName;
        String xmlFilePath = "src/main/resources/computer_parts.xml";
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslFilePath));
        String outputFile = "outputOf"+xslFileName.replaceAll(".xsl", "")+".txt";
        transformer.transform(new StreamSource(xmlFilePath), new StreamResult(new FileOutputStream(new File(outputFile))));
        System.out.println("=> "+ xslFileName +" have been transformed into plain text format (.txt).");
        System.out.println("=> "+ outputFile +" file has been created in project's root directory");
    }
}
