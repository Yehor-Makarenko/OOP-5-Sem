package oop.xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

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

public class GemsParser {
  private ArrayList<Gem> gems = new ArrayList<>();
  private DocumentBuilder db;
  private Document doc;    
  private Validator validator;

  public GemsParser(String xmlSchemaFilePath) {    
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

  public int getSize() {
    return gems.size();
  }

  public Gem getGem(int index) {
    return gems.get(index);
  }

  public void addGem(String name, GemPreciousness preciousness, String origin, GemColor color, double transparency, int facetCount, double value) {
    gems.add(new Gem(name, preciousness, origin, color, transparency, facetCount, value));
  }

  public void deleteGem(int index) {
    gems.remove(gems.get(index));
  }

  public void sortByName() {
    Comparator<Gem> comp = Comparator.comparing(gem -> gem.getName());
    gems.sort(comp);
  } 

  public void sortByPreciousness() {
    Comparator<Gem> comp = Comparator.comparing(gem -> gem.getPreciousness().toString());
    gems.sort(comp);
  } 

  public void sortByOrigin() {
    Comparator<Gem> comp = Comparator.comparing(gem -> gem.getOrigin());
    gems.sort(comp);
  } 

  public void sortByColor() {
    Comparator<Gem> comp = Comparator.comparing(gem -> gem.getColor().toString());
    gems.sort(comp);
  } 

  public void sortByTransparency() {
    Comparator<Gem> comp = Comparator.comparing(gem -> gem.getTransparency());
    gems.sort(comp);
  }

  public void sortByFacetCount() {
    Comparator<Gem> comp = Comparator.comparing(gem -> gem.getFacetCount());
    gems.sort(comp);
  }

  public void sortByValue() {
    Comparator<Gem> comp = Comparator.comparing(gem -> gem.getValue());
    gems.sort(comp);
  }

  public void loadFromFile(String filePath) {
    try {
      doc = db.parse(new File(filePath));
    } catch (SAXException | IOException e) {            
      e.printStackTrace();        
    }
    Element root = doc.getDocumentElement();
    NodeList fileGems = root.getElementsByTagName("Gem");
    gems.clear();
    
    for (int i = 0; i < fileGems.getLength(); i++) {
      Element gemData = (Element) fileGems.item(i);      
      addGem(gemData.getAttribute("Name"), GemPreciousness.getPreciousness(gemData.getAttribute("Preciousness")), 
        gemData.getAttribute("Origin"), GemColor.getColor(gemData.getAttribute("Color")), 
        Double.parseDouble(gemData.getAttribute("Transparency")), Integer.parseInt(gemData.getAttribute("FacetCount")),
        Double.parseDouble(gemData.getAttribute("Value")));      
    }        
  }

  public void saveToFile(String filePath) {
    doc = db.newDocument();    
    doc.appendChild(doc.createElement("Gems"));
    
    for (int i = 0; i < gems.size(); i++) {
      Gem gem = gems.get(i);
      Element gemNode = doc.createElement("Gem");
      gemNode.setAttribute("GemID", String.valueOf(i));
      gemNode.setAttribute("Name", gem.getName());
      gemNode.setAttribute("Preciousness", gem.getPreciousness().toString());
      gemNode.setAttribute("Origin", gem.getOrigin());
      gemNode.setAttribute("Color", gem.getColor().toString());
      gemNode.setAttribute("Transparency", String.valueOf(gem.getTransparency()));
      gemNode.setAttribute("FacetCount", String.valueOf(gem.getFacetCount()));
      gemNode.setAttribute("Value", String.valueOf(gem.getValue()));

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

  public void print() {
    System.out.println("Gems:");
    for (Gem gem: gems) {
      System.out.println("\t" + gem);
    }
  }
}

