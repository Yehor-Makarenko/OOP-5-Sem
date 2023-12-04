package oop.parsers.staxParser;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import oop.gem.Gem;
import oop.gem.enums.GemColor;
import oop.gem.enums.GemPreciousness;
import oop.parsers.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StAXParserGems implements Parser {
    private ArrayList<Gem> gems;
    private Gem currentGem;
    private StringBuilder currentElementValue;    
    private Validator validator;

    public StAXParserGems(String xmlSchemaFilePath) {
        this.gems = new ArrayList<>();
        this.currentElementValue = new StringBuilder();        
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;

        try {
            schema = sf.newSchema(new File(xmlSchemaFilePath));
            validator = schema.newValidator();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Gem> loadFromFile(String filePath) {
        gems.clear();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);
            Source streamSource = new StreamSource(new File(filePath));
            try {
                validator.validate(streamSource);
            } catch (SAXException e) {                
                e.printStackTrace();
            }

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement(xmlStreamReader);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        characters(xmlStreamReader);
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        endElement(xmlStreamReader);
                        break;
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
        return gems;
    }

    private void startElement(XMLStreamReader xmlStreamReader) {
        currentElementValue.setLength(0);

        if ("Gem".equalsIgnoreCase(xmlStreamReader.getLocalName())) {
            currentGem = new Gem();
        }
    }

    private void characters(XMLStreamReader xmlStreamReader) {
        currentElementValue.append(xmlStreamReader.getText().trim());
    }

    private void endElement(XMLStreamReader xmlStreamReader) {
        String localName = xmlStreamReader.getLocalName();

        switch (localName.toLowerCase()) {
            case "name":
                currentGem.setName(currentElementValue.toString());
                break;
            case "preciousness":
                currentGem.setPreciousness(GemPreciousness.getPreciousness(currentElementValue.toString()));
                break;
            case "origin":
                currentGem.setOrigin(currentElementValue.toString());
                break;
            case "color":
                currentGem.setColor(GemColor.getColor(currentElementValue.toString()));
                break;
            case "transparency":
                currentGem.setTransparency(Double.parseDouble(currentElementValue.toString()));
                break;
            case "facetcount":
                currentGem.setFacetCount(Integer.parseInt(currentElementValue.toString()));
                break;
            case "value":
                currentGem.setValue(Double.parseDouble(currentElementValue.toString()));
                break;
            case "gem":
                gems.add(currentGem);
                break;
        }
    }

    @Override
    public void saveToFile(String filePath, ArrayList<Gem> gems) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(new File(filePath)), "UTF-8");

            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeStartElement("Gems");

            for (int i = 0; i < gems.size(); i++) {
                Gem gem = gems.get(i);

                xmlStreamWriter.writeStartElement("Gem");
                xmlStreamWriter.writeAttribute("GemID", String.valueOf(i));

                addChildElement(xmlStreamWriter, "Name", gem.getName());
                addChildElement(xmlStreamWriter, "Preciousness", gem.getPreciousness().toString());
                addChildElement(xmlStreamWriter, "Origin", gem.getOrigin());

                xmlStreamWriter.writeStartElement("VisualParameters");
                addChildElement(xmlStreamWriter, "Color", gem.getColor().toString());
                addChildElement(xmlStreamWriter, "Transparency", String.valueOf(gem.getTransparency()));
                addChildElement(xmlStreamWriter, "FacetCount", String.valueOf(gem.getFacetCount()));
                xmlStreamWriter.writeEndElement();

                addChildElement(xmlStreamWriter, "Value", String.valueOf(gem.getValue()));

                xmlStreamWriter.writeEndElement();
            }

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addChildElement(XMLStreamWriter xmlStreamWriter, String elementName, String textContent) throws Exception {
        xmlStreamWriter.writeStartElement(elementName);
        xmlStreamWriter.writeCharacters(textContent);
        xmlStreamWriter.writeEndElement();
    }
}

