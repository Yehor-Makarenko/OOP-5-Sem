package Candies.ChocolateCandies.ChocolateCandiesWithFilling;

import Candies.Candy;
import Candies.IngredientTypes.FillingType;

public abstract class ChocolateCandyWithFilling extends Candy {
  private FillingType filling;

  public ChocolateCandyWithFilling(int weight, int sugarLevel, int calorieContent, int shelfLifeMonths, FillingType filling) {
    super(weight, sugarLevel, calorieContent, shelfLifeMonths);
    this.filling = filling;
  }

  public String getFilling() {
    return filling.getTitle();
  }
}
