package Java.Candies.ChocolateCandies;

import java.sql.Date;

import Java.Candies.Candy;

public class ChocolateCandy extends Candy {  
  private static int id = 3;
  private static int weight = 15;
  private static int sugarLevel = 10;
  private static int calorieContent = 80;
  private static int shelfLifeMonths = 8;      

  public ChocolateCandy(Date expirationDate) {
    super(weight, sugarLevel, calorieContent, expirationDate);
  }

  public static int getShelfLifeMonths() {
    return shelfLifeMonths;
  }

  public static int getId() {
    return id;
  }
}
