package Java.Candies;

import java.sql.Date;
import java.time.LocalDate;

import Java.Candies.ChocolateCandies.ChocolateCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateLiquorCandy.ChocolateLiquorCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNougatCandy.ChocolateNougatCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNutCandy.ChocolateNutCandy;
import Java.Candies.JellyCandy.JellyCandy;
import Java.Candies.Lollipop.Lollipop;

public abstract class Candy {
  private int weight;
  private int sugarLevel;
  private int calorieContent;  
  private Date expirationDate;

  public Candy(int weight, int sugarLevel, int calorieContent, int shelfLifeMonths) {
    this.weight = weight;
    this.sugarLevel = sugarLevel;
    this.calorieContent = calorieContent;        
    this.expirationDate = Date.valueOf(LocalDate.now().plusDays((int)(shelfLifeMonths * 30 * Math.random())));
  }  

  public Candy(int weight, int sugarLevel, int calorieContent, Date expirationDate) {
    this.weight = weight;
    this.sugarLevel = sugarLevel;
    this.calorieContent = calorieContent;        
    this.expirationDate = expirationDate;
  }  

  public int getWeight() {
    return weight;
  }

  public int getSugarLevel() {
    return sugarLevel;
  }

  public int getCalorieContent() {
    return calorieContent;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public String getFilling() {
    return null;
  }

  public static Candy createCandyByClassName(String classname, Date expirationDate) {
    switch (classname) {
      case "ChocolateCandy":
        return new ChocolateCandy(expirationDate);
      case "Lollipop":
        return new Lollipop(expirationDate);
      case "JellyCandy":
        return new JellyCandy(expirationDate);
      case "ChocolateNutCandy":
        return new ChocolateNutCandy(expirationDate);
      case "ChocolateNougatCandy":
        return new ChocolateNougatCandy(expirationDate);
      default:
        return new ChocolateLiquorCandy(expirationDate);      
    }
  }

  public static Candy createCandyByClassName(String classname) {
    switch (classname) {
      case "ChocolateCandy":
        return new ChocolateCandy();
      case "Lollipop":
        return new Lollipop();
      case "JellyCandy":
        return new JellyCandy();
      case "ChocolateNutCandy":
        return new ChocolateNutCandy();
      case "ChocolateNougatCandy":
        return new ChocolateNougatCandy();
      default:
        return new ChocolateLiquorCandy();      
    }
  }
}
