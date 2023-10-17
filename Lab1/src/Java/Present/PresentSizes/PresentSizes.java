package Java.Present.PresentSizes;

public enum PresentSizes {
  Small(1, 10),
  Medium(2, 20),
  Large(3, 30);

  private int id;
  private int maxCandies;

  private PresentSizes(int id, int maxCandies) {
    this.id = id;
    this.maxCandies = maxCandies;
  }

  public int getId() {
    return id;
  }

  public int getMaxCandies() {
    return maxCandies;
  }

  public static PresentSizes getById(int id) {
    if (id == 1) {
      return Small;
    } 
    if (id == 2) {
      return Medium;      
    }
    return Large;
  }
}
