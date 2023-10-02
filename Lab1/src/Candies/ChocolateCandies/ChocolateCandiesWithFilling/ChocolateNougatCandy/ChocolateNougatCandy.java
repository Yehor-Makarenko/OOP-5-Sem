package Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNougatCandy;

import Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateCandyWithFilling;
import Candies.IngredientTypes.FillingType;

public class ChocolateNougatCandy extends ChocolateCandyWithFilling {
  private static int weight = 20;
  private static int sugarLevel = 15;
  private static int calorieContent = 70;
  private static int shelfLifeMonths = 7;   
  private static FillingType filling = FillingType.NOUGAT;

  public ChocolateNougatCandy() {
    super(weight, sugarLevel, calorieContent, shelfLifeMonths, filling);
  }
}
