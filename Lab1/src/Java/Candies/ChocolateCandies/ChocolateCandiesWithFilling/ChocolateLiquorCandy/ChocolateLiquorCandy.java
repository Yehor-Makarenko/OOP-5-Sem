package Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateLiquorCandy;

import java.sql.Date;

import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateCandyWithFilling;
import Java.Candies.IngredientTypes.FillingType;

public class ChocolateLiquorCandy extends ChocolateCandyWithFilling {
  private static int weight = 25;
  private static int sugarLevel = 10;
  private static int calorieContent = 80;
  private static int shelfLifeMonths = 6;   
  private static FillingType filling = FillingType.LIQUOR;

  public ChocolateLiquorCandy() {
    super(weight, sugarLevel, calorieContent, shelfLifeMonths, filling);
  }

  public ChocolateLiquorCandy(Date expirationDate) {
    super(weight, sugarLevel, calorieContent, expirationDate, filling);
  }
}
