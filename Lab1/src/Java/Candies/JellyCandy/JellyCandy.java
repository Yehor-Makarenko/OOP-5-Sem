package Java.Candies.JellyCandy;

import java.sql.Date;

import Java.Candies.Candy;

public class JellyCandy extends Candy {
  private static int id = 1;
  private static int weight = 18;
  private static int sugarLevel = 15;
  private static int calorieContent = 50;
  private static int shelfLifeMonths = 5;    

  public JellyCandy(Date expirationDate) {
    super(weight, sugarLevel, calorieContent, expirationDate);
  }

  public static int getShelfLifeMonths() {
    return shelfLifeMonths;
  }

  public static int getId() {
    return id;
  }
}
