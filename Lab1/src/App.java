import Candies.ChocolateCandy.ChocolateCandy;

public class App {
    public static void main(String[] args) {
        ChocolateCandy cc = new ChocolateCandy();
        System.out.println(cc.getWeight());
        System.out.println(cc.getExpirationDate());
    }
}