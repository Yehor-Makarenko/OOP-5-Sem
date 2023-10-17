package Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateLiquorCandy;

import java.sql.Date;

import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateCandyWithFilling;
import Java.Candies.IngredientTypes.FillingTypes;

public class ChocolateLiquorCandy extends ChocolateCandyWithFilling {
  private static int id = 6;
  private static int weight = 25;
  private static int sugarLevel = 10;
  private static int calorieContent = 80;
  private static int shelfLifeMonths = 6;   
  private static FillingTypes filling = FillingTypes.LIQUOR;

  public ChocolateLiquorCandy(Date expirationDate) {
    super(weight, sugarLevel, calorieContent, expirationDate, filling);
  }

  public static int getShelfLifeMonths() {
    return shelfLifeMonths;
  }

  public static int getId() {
    return id;
  }
}
