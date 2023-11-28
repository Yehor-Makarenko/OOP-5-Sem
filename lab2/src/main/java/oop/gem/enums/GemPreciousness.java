package oop.gem.enums;

public enum GemPreciousness {
  PRECIOUS("precious"),
  SEMI_PRECIOUS("semi-precious");  

  private String preciousness;

  public static GemPreciousness getPreciousness(String preciousness) {
    switch (preciousness) {
      case "precious":
        return GemPreciousness.PRECIOUS;        
      default:
        return GemPreciousness.SEMI_PRECIOUS;        
    }
  }

  private GemPreciousness(String preciousness) {    
    this.preciousness = preciousness;
  }

  public String toString() {
    return preciousness;
  }  
}