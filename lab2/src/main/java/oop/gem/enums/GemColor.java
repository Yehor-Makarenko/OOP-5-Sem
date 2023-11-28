package oop.gem.enums;

public enum GemColor {
  GREEN("green"),
  RED("red"),
  YELLOW("yellow"),
  BLUE("blue"),
  PURPLE("purple");
  
  private String color;

  public static GemColor getColor(String color) {
    switch (color) {
      case "green":
        return GemColor.GREEN;        
      case "red":
        return GemColor.RED;        
      case "yellow":
        return GemColor.YELLOW;        
      case "blue":
        return GemColor.BLUE;        
      default:
        return GemColor.PURPLE;        
    }
  }

  private GemColor(String color) {    
    this.color = color;
  }

  public String toString() {
    return color;
  }  
}
