package Java.Present;

import java.util.ArrayList;

import Java.Candies.Candy;
import Java.Database.DBController;

public class Present {
  private ArrayList<Candy> candies; 
  private int weight; 
  private int id;

  public Present() {
    candies = new ArrayList<Candy>();
    weight = 0;
    id = DBController.getMaxPresentID() + 1;
    DBController.createPresent();
  }

  private Present(ArrayList<Candy> candies, int weight, int id) {
    this.candies = candies;
    this.weight = weight;
    this.id = id;
  }

  public static int getMaxPresentID() {
    return DBController.getMaxPresentID();
  }

  public static Present getPresentByID(int id) {    
    ArrayList<Candy> candies = DBController.getCandiesByPresentID(id);
    int weight = DBController.getPresentWeightByID(id);

    return new Present(candies, weight, id);
  }

  public void put(Candy candy) {
    candies.add(candy);
    weight += candy.getWeight();
    DBController.addCandyToPresent(candy, id, weight, candies.size());
  }

  public int getWeight() {
    return weight;
  }

  public void sortByWeight() {
    candies.sort((c1, c2) -> c1.getWeight() - c2.getWeight());    
  }

  public void sortBySugarLevel() {
    candies.sort((c1, c2) -> c1.getSugarLevel() - c2.getSugarLevel());    
  }

  public void sortByCalorieContent() {
    candies.sort((c1, c2) -> c1.getCalorieContent() - c2.getCalorieContent());    
  }

  public void sortByExpirationDate() {
    candies.sort((c1, c2) -> c1.getExpirationDate().compareTo(c2.getExpirationDate()));    
  }

  public void print() {
    System.out.println("Candies:");
    candies.stream().forEach(candy -> {
      System.out.println("\t" + candy.getClass().getSimpleName() + " \tWeight: " + candy.getWeight() 
        + " \tSugar level: " + candy.getSugarLevel() + " \tCalorie content: " + candy.getCalorieContent() 
        + " \tExpiration date: " + candy.getExpirationDate());
    });
  }
}
