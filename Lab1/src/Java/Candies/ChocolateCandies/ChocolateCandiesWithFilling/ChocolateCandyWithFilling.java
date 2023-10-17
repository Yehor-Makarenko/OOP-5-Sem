package Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling;

import java.sql.Date;

import Java.Candies.Candy;
import Java.Candies.IngredientTypes.FillingTypes;

public abstract class ChocolateCandyWithFilling extends Candy {
  private FillingTypes filling;

  public ChocolateCandyWithFilling(int weight, int sugarLevel, int calorieContent, Date expirationDate, FillingTypes filling) {
    super(weight, sugarLevel, calorieContent, expirationDate);
    this.filling = filling;
  }

  public String getFilling() {
    return filling.getTitle();
  }
}
