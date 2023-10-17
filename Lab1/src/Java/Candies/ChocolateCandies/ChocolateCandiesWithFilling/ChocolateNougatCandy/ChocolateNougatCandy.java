package Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNougatCandy;

import java.sql.Date;

import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateCandyWithFilling;
import Java.Candies.IngredientTypes.FillingTypes;

public class ChocolateNougatCandy extends ChocolateCandyWithFilling {
  private static int id = 5;
  private static int weight = 20;
  private static int sugarLevel = 15;
  private static int calorieContent = 70;
  private static int shelfLifeMonths = 7;   
  private static FillingTypes filling = FillingTypes.NOUGAT;

  public ChocolateNougatCandy(Date expirationDate) {
    super(weight, sugarLevel, calorieContent, expirationDate, filling);
  }

  public static int getShelfLifeMonths() {
    return shelfLifeMonths;
  }

  public static int getId() {
    return id;
  }
}
