package Candies.JellyCandy;

import Candies.Candy;

public class JellyCandy extends Candy {
  private static int weight = 18;
  private static int sugarLevel = 15;
  private static int calorieContent = 50;
  private static int shelfLifeMonths = 5;    

  public JellyCandy() {
    super(weight, sugarLevel, calorieContent, shelfLifeMonths);
  }
}
