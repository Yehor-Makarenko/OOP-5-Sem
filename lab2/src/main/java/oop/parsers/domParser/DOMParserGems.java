package oop.parsers.domParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import oop.gem.Gem;
import oop.gem.enums.GemColor;
import oop.gem.enums.GemPreciousness;
import oop.parsers.Parser;

public class DOMParserGems implements Parser {
  private DocumentBuilder db;
  private Document doc;    
  private Validator validator;

  public DOMParserGems(String xmlSchemaFilePath) {    
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema s = null;
    try {
      s = sf.newSchema(new File(xmlSchemaFilePath));
    } catch (SAXException e) {      
      e.printStackTrace();
    }
    validator = s.newValidator();
    dbf.setSchema(s);

    try {
      db = dbf.newDocumentBuilder();
      db.setErrorHandler(new XMLErrorHandler());
    } catch (ParserConfigurationException e) {      
      e.printStackTrace();
    }
  }

  public ArrayList<Gem> loadFromFile(String filePath) {
    try {
      doc = db.parse(new File(filePath));
    } catch (SAXException | IOException e) {            
      e.printStackTrace();        
    }
    Element root = doc.getDocumentElement();
    NodeList fileGems = root.getElementsByTagName("Gem");
    ArrayList<Gem> gems = new ArrayList<>();
    
    for (int i = 0; i < fileGems.getLength(); i++) {
      Element gemData = (Element) fileGems.item(i);
      
      String name = gemData.getElementsByTagName("Name").item(0).getTextContent();
      GemPreciousness preciousness = GemPreciousness.getPreciousness(gemData.getElementsByTagName("Preciousness").item(0).getTextContent());
      String origin = gemData.getElementsByTagName("Origin").item(0).getTextContent();
      Element visualParameters = (Element) gemData.getElementsByTagName("VisualParameters").item(0);
      GemColor color = GemColor.getColor(visualParameters.getElementsByTagName("Color").item(0).getTextContent());
      Double transparency = Double.parseDouble(visualParameters.getElementsByTagName("Transparency").item(0).getTextContent());
      Integer facetCount = Integer.parseInt(visualParameters.getElementsByTagName("FacetCount").item(0).getTextContent());
      Double value = Double.parseDouble(gemData.getElementsByTagName("Value").item(0).getTextContent());

      gems.add(new Gem(name, preciousness, origin, color, transparency, facetCount, value));      
    }      
    
    return gems;
  }

  public void saveToFile(String filePath, ArrayList<Gem> gems) {
    doc = db.newDocument();
    doc.appendChild(doc.createElement("Gems"));
    
    for (int i = 0; i < gems.size(); i++) {
      Gem gem = gems.get(i);
      Element gemNode = doc.createElement("Gem");
      gemNode.setAttribute("GemID", String.valueOf(i)); 
      
      addChildElement(doc, gemNode, "Name", gem.getName());
      addChildElement(doc, gemNode, "Preciousness", gem.getPreciousness().toString());
      addChildElement(doc, gemNode, "Origin", gem.getOrigin());

      Element visualParametersElement = doc.createElement("VisualParameters");
      gemNode.appendChild(visualParametersElement);

      addChildElement(doc, visualParametersElement, "Color", gem.getColor().toString());
      addChildElement(doc, visualParametersElement, "Transparency", String.valueOf(gem.getTransparency()));
      addChildElement(doc, visualParametersElement, "FacetCount", String.valueOf(gem.getFacetCount()));
      
      addChildElement(doc, gemNode, "Value", String.valueOf(gem.getValue()));

      doc.getDocumentElement().appendChild(gemNode);
    }
    
    Source domSource = new DOMSource(doc);
    try {
      validator.validate(domSource);
    } catch (SAXException | IOException e) {
      System.out.println(e.getMessage());
      return;
    }
    Result fileResult = new StreamResult(new File(filePath));
    TransformerFactory factory = TransformerFactory.newInstance();
    Transformer transformer = null;
    try {
      transformer = factory.newTransformer();
    } catch (TransformerConfigurationException e) {      
      e.printStackTrace();
    }
    transformer.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
    try {
      transformer.transform(domSource, fileResult);
    } catch (TransformerException e) {      
      e.printStackTrace();
    }
  }

  private static void addChildElement(Document doc, Element parentElement, String elementName, String textContent) {
    Element childElement = doc.createElement(elementName);
    childElement.setTextContent(textContent);
    parentElement.appendChild(childElement);
  }
}

