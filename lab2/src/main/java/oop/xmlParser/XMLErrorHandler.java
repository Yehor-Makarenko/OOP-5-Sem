package oop.xmlParser;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class XMLErrorHandler implements ErrorHandler {  
  @Override
  public void warning(SAXParseException e) throws SAXException {
    System.out.println("Рядок" + e.getLineNumber() + ":");
    System.out.println(e.getMessage());
  }
  
  @Override
  public void error(SAXParseException e) throws SAXException {
    System.out.println("Рядок" + e.getLineNumber() + ":");
    System.out.println(e.getMessage());
  }
  
  @Override
  public void fatalError(SAXParseException e) throws SAXException {
    System.out.println("Рядок" + e.getLineNumber() + ":");
    System.out.println(e.getMessage());
  }
}