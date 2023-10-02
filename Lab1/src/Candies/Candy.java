package Candies;

import java.time.LocalDate;

public abstract class Candy {
  private int weight;
  private int sugarLevel;
  private int calorieContent;
  private LocalDate expirationDate;

  public Candy(int weight, int sugarLevel, int calorieContent, int shelfLifeMonths) {
    this.weight = weight;
    this.sugarLevel = sugarLevel;
    this.calorieContent = calorieContent;    
    this.expirationDate = LocalDate.now().plusDays((int)(shelfLifeMonths * 30 * Math.random()));
  }

  public Candy(int weight, int sugarLevel, int calorieContent, int shelfLifeMonths, LocalDate prodactionDate) {
    this.weight = weight;
    this.sugarLevel = sugarLevel;
    this.calorieContent = calorieContent;    
    this.expirationDate = prodactionDate.plusMonths(shelfLifeMonths);
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

  public LocalDate getExpirationDate() {
    return expirationDate;
  }
}
