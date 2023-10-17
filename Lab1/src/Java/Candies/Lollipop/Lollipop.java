package Java.Candies.Lollipop;

import java.sql.Date;

import Java.Candies.Candy;

public class Lollipop extends Candy {
  private static int id = 2;
  private static int weight = 8;
  private static int sugarLevel = 12;
  private static int calorieContent = 40;
  private static int shelfLifeMonths = 15;    

  public Lollipop(Date expirationDate) {
    super(weight, sugarLevel, calorieContent, expirationDate);
  }

  public static int getShelfLifeMonths() {
    return shelfLifeMonths;
  }

  public static int getId() {
    return id;
  }
}
