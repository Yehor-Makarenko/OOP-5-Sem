package Java.Candies.IngredientTypes;

public enum FillingTypes {
  NUT(1, "Nut"),
  NOUGAT(2, "Nougat"),
  LIQUOR(3, "Liquor");

  private int id;
  private String title;

  private FillingTypes(int id, String title) {
    this.id = id;
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
}
