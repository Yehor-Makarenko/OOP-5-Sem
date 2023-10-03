package Java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import Java.Candies.ChocolateCandies.ChocolateCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateLiquorCandy.ChocolateLiquorCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNougatCandy.ChocolateNougatCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNutCandy.ChocolateNutCandy;
import Java.Candies.JellyCandy.JellyCandy;
import Java.Candies.Lollipop.Lollipop;
import Java.Present.Present;

public class App {
    private static Scanner input = new Scanner(System.in);
    private static Present present;

    public static void main(String[] args) {                 
        while (true) {
            showStartMenu();
            int num = input.nextInt();

            if (num == 3) {
                return;
            }
            if (num == 1) {
                present = new Present();
                showPresentMenu();
            } else {
                showExistingPresents();
            }
            
        }

    }

    private static void showStartMenu() {
        System.out.println("\n1) Створити подарунок");
        System.out.println("2) Вибрати iснуючий подарунок");
        System.out.println("3) Завершити роботу");        
    }

    private static void showPresentMenu() {
        while (true) {
            System.out.println("\n1) Додати цукерку");
            System.out.println("2) Переглянути змiст подарунка");
            System.out.println("3) Отримати вагу подарунка"); 
            System.out.println("4) Відсортувати за вагою"); 
            System.out.println("5) Відсортувати за рівнем цукру"); 
            System.out.println("6) Відсортувати за калорійністю"); 
            System.out.println("7) Відсортувати за терміном придатності"); 
            System.out.println("8) Повернутися"); 

            int num = input.nextInt();
            if (num == 8) {
                return;
            }
            if (num == 1) {
                chooseCandy();
            } else if (num == 2) {
                present.print();
            } else if (num == 3) {
                System.out.println("Вага: " + present.getWeight());
            } else if (num == 4) {
                present.sortByWeight();
            } else if (num == 5) {
                present.sortBySugarLevel();
            } else if (num == 6) {
                present.sortByCalorieContent();
            } else if (num == 7) {
                present.sortByExpirationDate();
            }         
        }                       
    }

    private static void showExistingPresents() {
        InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/resources/dbconfig.properties"); 
        Properties props = new Properties();

        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }    


        System.out.println("\nНаявнi подарунки 1-" + props.getProperty("maxPresentID"));
        System.out.println("Вибрати:");
        int presentId = input.nextInt();
        present = Present.getPresentByID(presentId);
        showPresentMenu();
    }

    private static void chooseCandy() {
        System.out.println("\n1) Шоколадна цукерка");
        System.out.println("2) Льодяник");
        System.out.println("3) Желейна цукерка");
        System.out.println("4) Шоколадна цукерка з горiхом");
        System.out.println("5) Шоколадна цукерка з нугою");
        System.out.println("6) Шоколадна цукерка з лiкером");
        System.out.println("Вибрати:");
        int presentId = input.nextInt();

        switch (presentId) {
            case 1:
                present.put(new ChocolateCandy());
                break;
            case 2:
                present.put(new Lollipop());
                break;
            case 3:
                present.put(new JellyCandy());
                break;
            case 4:
                present.put(new ChocolateNutCandy());
                break;
            case 5:
                present.put(new ChocolateNougatCandy());
                break;
            case 6:
                present.put(new ChocolateLiquorCandy());
                break;
        }
    }
}