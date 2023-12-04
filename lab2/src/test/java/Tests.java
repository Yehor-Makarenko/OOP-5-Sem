import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import oop.gem.GemsController;
import oop.gem.enums.GemColor;
import oop.gem.enums.GemPreciousness;
import oop.parsers.Parser;
import oop.parsers.domParser.DOMParserGems;
import oop.parsers.saxParser.SAXParserGems;
import oop.parsers.staxParser.StAXParserGems;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Tests {  
  private String xmlFilePath = "src/main/resources/testGems.xml";
  private Parser parser;
  private GemsController controller;

  public Tests(Parser parser) {
    this.parser = parser;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    String xmlSchemaPath = "src/main/resources/gems.xsd";
    return Arrays.asList(new Object[][]{
      {new SAXParserGems(xmlSchemaPath)},
      {new DOMParserGems(xmlSchemaPath)},
      {new StAXParserGems(xmlSchemaPath)},
    });
  }

  @Test
  public void testSave() {
      try {        
        controller = new GemsController(parser);
        controller.saveToFile(xmlFilePath);;
        assertEquals(1, 1);
      } catch (Exception e) {
        assertEquals(0, 1);
      }
  }

  @Test
  public void testLoad() {
      try {        
        controller = new GemsController(parser);    
        controller.loadFromFile(xmlFilePath);
        assertEquals(1, 1);
      } catch (Exception e) {
        assertEquals(0, 1);
      }
  }

  @Test
  public void testAdd() {      
      controller = new GemsController(parser);
      controller.addGem("A", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      controller.saveToFile(xmlFilePath);
      controller.loadFromFile(xmlFilePath);
      assertEquals(controller.getSize(), 1);
  }

  @Test
  public void testGet() {      
      controller = new GemsController(parser);
      controller.addGem("A", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      controller.saveToFile(xmlFilePath);
      controller.loadFromFile(xmlFilePath);
      assertEquals(controller.getGem(0).getName(), "A");
  }

  @Test
  public void testDelete() {      
      controller = new GemsController(parser);
      controller.addGem("A", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      controller.saveToFile(xmlFilePath);
      controller.loadFromFile(xmlFilePath);      
      controller.deleteGem(0);
      controller.saveToFile(xmlFilePath);
      controller.loadFromFile(xmlFilePath);      
      assertEquals(0, controller.getSize());
  }

  @Test
  public void testSort() {      
      controller = new GemsController(parser); 
      controller.addGem("Z", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      controller.addGem("A", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      controller.saveToFile(xmlFilePath);
      controller.loadFromFile(xmlFilePath);      
      controller.sortByName();
      controller.saveToFile(xmlFilePath);
      controller.loadFromFile(xmlFilePath);
      assertEquals(controller.getGem(0).getName(), "A");
  }
}
