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

  public static Candy createCandyByClassId(int classId, Date expirationDate) {
    switch (classId) {
      case 1:
      return new JellyCandy(expirationDate);
      case 2:
      return new Lollipop(expirationDate);
      case 3:
      return new ChocolateCandy(expirationDate);
      case 4:
        return new ChocolateNutCandy(expirationDate);
      case 5:
        return new ChocolateNougatCandy(expirationDate);
      case 6:
        return new ChocolateLiquorCandy(expirationDate);              
    }
    return null;
  }
}
