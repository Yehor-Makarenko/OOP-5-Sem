package Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNutCandy;

import java.sql.Date;

import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateCandyWithFilling;
import Java.Candies.IngredientTypes.FillingTypes;

public class ChocolateNutCandy extends ChocolateCandyWithFilling {
  private static int id = 4;
  private static int weight = 25;
  private static int sugarLevel = 12;
  private static int calorieContent = 90;
  private static int shelfLifeMonths = 10;   
  private static FillingTypes filling = FillingTypes.NUT;

  public ChocolateNutCandy(Date expirationDate) {
    super(weight, sugarLevel, calorieContent, expirationDate, filling);
  }

  public static int getShelfLifeMonths() {
    return shelfLifeMonths;
  }

  public static int getId() {
    return id;
  }
}
