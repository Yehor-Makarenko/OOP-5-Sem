package oop.parsers;

import java.util.ArrayList;

import oop.gem.Gem;

public interface Parser {  
  public ArrayList<Gem> loadFromFile(String filePath);
  public void saveToFile(String filePath, ArrayList<Gem> gems);
}
