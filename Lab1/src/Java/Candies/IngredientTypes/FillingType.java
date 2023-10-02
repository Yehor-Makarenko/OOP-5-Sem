package Java.Candies.IngredientTypes;

public enum FillingType {
  NUT("Nut"),
  NOUGAT("Nougat"),
  LIQUOR("Liquor");

  private String title;

  private FillingType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
