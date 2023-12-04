package oop.parsers.saxParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import oop.gem.Gem;
import oop.gem.enums.GemColor;
import oop.gem.enums.GemPreciousness;
import oop.parsers.Parser;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SAXParserGems implements Parser {
    private ArrayList<Gem> gems;
    private Gem currentGem;
    private StringBuilder currentElementValue;
    private SAXParser parser;

    public SAXParserGems(String xmlSchemaFilePath) {
        gems = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;

        try {
            schema = sf.newSchema(new File(xmlSchemaFilePath));
        } catch (SAXException e) {
            e.printStackTrace();
        }

        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setSchema(schema);
        
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {            
            e.printStackTrace();
        }   
    }

    @Override
    public ArrayList<Gem> loadFromFile(String filePath) {
        gems.clear();
        try {            
            parser.parse(new File(filePath), new GemHandler());
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return gems;
    }

    private class GemHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElementValue = new StringBuilder();

            if (qName.equalsIgnoreCase("Gem")) {
                currentGem = new Gem();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            currentElementValue.append(new String(ch, start, length).trim());
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName.toLowerCase()) {
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
    }

    @Override
    public void saveToFile(String filePath, ArrayList<Gem> gems) {
        try {
            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler handler = tf.newTransformerHandler();
            Transformer serializer = handler.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult(new File(filePath));            
            handler.setResult(result);
            handler.startDocument();
            handler.startElement("", "", "Gems", new AttributesImpl());

            for (int i = 0; i < gems.size(); i++) {
                Gem gem = gems.get(i);

                AttributesImpl attributes = new AttributesImpl();
                attributes.addAttribute("", "", "GemID", "CDATA", String.valueOf(i));

                handler.startElement("", "", "Gem", attributes);

                addChildElement(handler, "Name", gem.getName());
                addChildElement(handler, "Preciousness", gem.getPreciousness().toString());
                addChildElement(handler, "Origin", gem.getOrigin());

                handler.startElement("", "", "VisualParameters", new AttributesImpl());

                addChildElement(handler, "Color", gem.getColor().toString());
                addChildElement(handler, "Transparency", String.valueOf(gem.getTransparency()));
                addChildElement(handler, "FacetCount", String.valueOf(gem.getFacetCount()));

                handler.endElement("", "", "VisualParameters");

                addChildElement(handler, "Value", String.valueOf(gem.getValue()));

                handler.endElement("", "", "Gem");
            }

            handler.endElement("", "", "Gems");
            handler.endDocument();
        } catch (TransformerConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void addChildElement(TransformerHandler handler, String elementName, String textContent)
            throws SAXException {
        handler.startElement("", "", elementName, new AttributesImpl());
        handler.characters(textContent.toCharArray(), 0, textContent.length());
        handler.endElement("", "", elementName);
    }
}
