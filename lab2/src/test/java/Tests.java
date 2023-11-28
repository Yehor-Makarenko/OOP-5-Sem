import org.junit.Test;

import oop.gem.enums.GemColor;
import oop.gem.enums.GemPreciousness;
import oop.xmlParser.GemsParser;

import static org.junit.Assert.*;

public class Tests {
  private String xmlSchemaPath = "src/main/resources/gems.xsd";
  private String xmlFilePath = "src/main/resources/testGems.xml";
  private GemsParser gp;

  @Test
  public void testSchema() {
      try {
        gp = new GemsParser(xmlSchemaPath);
        assertEquals(1, 1);
      } catch (Exception e) {
        assertEquals(0, 1);
      }
  }

  @Test
  public void testSave() {
      try {
        gp = new GemsParser(xmlSchemaPath);
        gp.saveToFile(xmlFilePath);;
        assertEquals(1, 1);
      } catch (Exception e) {
        assertEquals(0, 1);
      }
  }

  @Test
  public void testLoad() {
      try {
        gp = new GemsParser(xmlSchemaPath);      
        gp.loadFromFile(xmlFilePath);
        assertEquals(1, 1);
      } catch (Exception e) {
        assertEquals(0, 1);
      }
  }

  @Test
  public void testAdd() {
      gp = new GemsParser(xmlSchemaPath);
      gp.addGem("A", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      gp.saveToFile(xmlFilePath);
      gp.loadFromFile(xmlFilePath);
      assertEquals(gp.getSize(), 1);
  }

  @Test
  public void testGet() {
      gp = new GemsParser(xmlSchemaPath);
      gp.addGem("A", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      gp.saveToFile(xmlFilePath);
      gp.loadFromFile(xmlFilePath);
      assertEquals(gp.getGem(0).getName(), "A");
  }

  @Test
  public void testDelete() {
      gp = new GemsParser(xmlSchemaPath);
      gp.addGem("A", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      gp.saveToFile(xmlFilePath);
      gp.loadFromFile(xmlFilePath);      
      gp.deleteGem(0);
      gp.saveToFile(xmlFilePath);
      gp.loadFromFile(xmlFilePath);
      assertEquals(gp.getSize(), 0);
  }

  @Test
  public void testSort() {
      gp = new GemsParser(xmlSchemaPath);
      gp.addGem("Z", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      gp.addGem("A", GemPreciousness.PRECIOUS, "B", GemColor.GREEN, 30, 7, 30);
      gp.saveToFile(xmlFilePath);
      gp.loadFromFile(xmlFilePath);      
      gp.sortByName();
      gp.saveToFile(xmlFilePath);
      gp.loadFromFile(xmlFilePath);
      assertEquals(gp.getGem(0).getName(), "A");
  }
}
