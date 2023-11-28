package Java.Present;

import java.util.ArrayList;
import java.util.Optional;

import Java.Candies.Candy;
import Java.Database.DBController;
import Java.Present.PresentSizes.PresentSizes;

public class Present {
  private ArrayList<Candy> candies; 
  private int weight; 
  private int id;
  private PresentSizes size;

  public Present(PresentSizes size) {
    candies = new ArrayList<Candy>();
    weight = 0;
    id = DBController.getMaxPresentID() + 1;
    this.size = size;
    DBController.createPresent(size);    
  }

  private Present(PresentSizes size, ArrayList<Candy> candies, int weight, int id) {
    this.size = size;
    this.candies = candies;
    this.weight = weight;
    this.id = id;
  }

  public static int getMaxPresentID() {
    return DBController.getMaxPresentID();
  }

  public static Present getPresentByID(int id) {    
    ArrayList<Candy> candies = DBController.getCandiesByPresentID(id);
    PresentSizes size = DBController.getPresentSizeById(id);
    int weight = 0;
    for (int i = 0; i < candies.size(); i++) {
      weight += candies.get(i).getWeight();
    }

    return new Present(size, candies, weight, id);
  }

  public void put(Candy candy) {
    if (!hasSpace()) {
      System.out.println("No more space");
      return;
    }
    candies.add(candy);
    weight += candy.getWeight();
    DBController.addCandyToPresent(id, candy);
  }

  public boolean hasSpace() {
    return size.getMaxCandies() > candies.size();
  }

  public int getWeight() {
    return weight;
  }

  public PresentSizes getSize() {
    return size;
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

  public Candy getCandyBySugarLevel(int min, int max) {
    Optional<Candy> found = candies.stream().filter(candy -> candy.getSugarLevel() >= min && candy.getSugarLevel() <= max).findFirst();
    return found.isPresent() ? found.get() : null;
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
