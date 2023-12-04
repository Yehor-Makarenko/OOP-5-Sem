package oop.gem;

import java.util.ArrayList;
import java.util.Comparator;

import oop.gem.enums.GemColor;
import oop.gem.enums.GemPreciousness;
import oop.parsers.Parser;

public class GemsController {
  private ArrayList<Gem> gems;
  private Parser parser;

  public GemsController(Parser parser) {
    gems = new ArrayList<>();
    this.parser = parser;
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
    gems = parser.loadFromFile(filePath);
  }

  public void saveToFile(String filePath) {
    parser.saveToFile(filePath, gems);
  }

  public void print() {
    System.out.println("Gems:");
    for (Gem gem: gems) {
      System.out.println("\t" + gem);
    }
  }
}
