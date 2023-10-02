package Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateLiquorCandy;

import Candies.IngredientTypes.FillingType;

public class ChocolateLiquorCandy {
  private static int weight = 25;
  private static int sugarLevel = 10;
  private static int calorieContent = 80;
  private static int shelfLifeMonths = 6;   
  private static FillingType filling = FillingType.LIQUOR;

  public ChocolateLiquorCandy() {
    super(weight, sugarLevel, calorieContent, shelfLifeMonths, filling);
  }
}