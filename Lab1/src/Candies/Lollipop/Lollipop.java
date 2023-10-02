package Candies.Lollipop;

import Candies.Candy;

public class Lollipop extends Candy {
  private static int weight = 8;
  private static int sugarLevel = 12;
  private static int calorieContent = 40;
  private static int shelfLifeMonths = 15;    

  public Lollipop() {
    super(weight, sugarLevel, calorieContent, shelfLifeMonths);
  }
}
