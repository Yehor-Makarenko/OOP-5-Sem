package Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNutCandy;

import Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateCandyWithFilling;
import Candies.IngredientTypes.FillingType;

public class ChocolateNutCandy extends ChocolateCandyWithFilling {
  private static int weight = 25;
  private static int sugarLevel = 12;
  private static int calorieContent = 90;
  private static int shelfLifeMonths = 10;   
  private static FillingType filling = FillingType.NUT;

  public ChocolateNutCandy() {
    super(weight, sugarLevel, calorieContent, shelfLifeMonths, filling);
  }
}
