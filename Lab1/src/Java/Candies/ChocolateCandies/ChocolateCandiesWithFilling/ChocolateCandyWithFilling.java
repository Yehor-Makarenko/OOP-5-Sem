package Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling;

import java.sql.Date;

import Java.Candies.Candy;
import Java.Candies.IngredientTypes.FillingType;

public abstract class ChocolateCandyWithFilling extends Candy {
  private FillingType filling;

  public ChocolateCandyWithFilling(int weight, int sugarLevel, int calorieContent, int shelfLifeMonths, FillingType filling) {
    super(weight, sugarLevel, calorieContent, shelfLifeMonths);
    this.filling = filling;
  }

  public ChocolateCandyWithFilling(int weight, int sugarLevel, int calorieContent, Date expirationDate, FillingType filling) {
    super(weight, sugarLevel, calorieContent, expirationDate);
    this.filling = filling;
  }

  public String getFilling() {
    return filling.getTitle();
  }
}
