package Java;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

import Java.Candies.Candy;
import Java.Candies.ChocolateCandies.ChocolateCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateLiquorCandy.ChocolateLiquorCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNougatCandy.ChocolateNougatCandy;
import Java.Candies.ChocolateCandies.ChocolateCandiesWithFilling.ChocolateNutCandy.ChocolateNutCandy;
import Java.Candies.JellyCandy.JellyCandy;
import Java.Candies.Lollipop.Lollipop;
import Java.Database.DBController;
import Java.Present.Present;
import Java.Present.PresentSizes.PresentSizes;

public class App {    
    private static Scanner input = new Scanner(System.in);
    private static Present present;    

    public static void main(String[] args) {  
        DBController.openConnection();
        showStartMenu();
        DBController.closeConnection();
    }

    private static void showStartMenu() {
        while (true) {
            System.out.println("\n1) Створити подарунок");
            System.out.println("2) Вибрати iснуючий подарунок");
            System.out.println("3) Завершити роботу");   
            int num = input.nextInt();

            if (num == 3) {                
                return;
            }
            if (num == 1) {
                present = showCreatePresentMenu();
                showPresentMenu();
            } else {
                showExistingPresents();
            }
            
        }             
    }

    private static Present showCreatePresentMenu() {
        System.out.println("1) Створити малий подарунок");
        System.out.println("2) Створити середнiй подарунок");
        System.out.println("3) Створити великий подарунок");
        int num = input.nextInt();

        if (num == 1) {
            return new Present(PresentSizes.Small);
        }
        if (num == 1) {
            return new Present(PresentSizes.Medium);
        }
        return new Present(PresentSizes.Large);
    }

    private static void showPresentMenu() {
        while (true) {
            System.out.println("\n1) Додати цукерку");
            System.out.println("2) Переглянути змiст подарунка");
            System.out.println("3) Отримати розмiр подарунка"); 
            System.out.println("4) Отримати вагу подарунка"); 
            System.out.println("5) Вiдсортувати за вагою"); 
            System.out.println("6) Вiдсортувати за рiвнем цукру"); 
            System.out.println("7) Вiдсортувати за калорiйнiстю"); 
            System.out.println("8) Вiдсортувати за термiном придатностi"); 
            System.out.println("9) Отримати цукерку в дiапазонi рiвня цукру"); 
            System.out.println("10) Повернутися"); 

            int num = input.nextInt();
            if (num == 10) {
                return;
            }
            if (num == 1) {
                chooseCandy();
            } else if (num == 2) {
                present.print();
            } else if(num == 3) {
                System.out.println("Розмiр: " + present.getSize().name());
            } else if (num == 4) {
                System.out.println("Вага: " + present.getWeight());
            } else if (num == 5) {
                present.sortByWeight();
            } else if (num == 6) {
                present.sortBySugarLevel();
            } else if (num == 7) {
                present.sortByCalorieContent();
            } else if (num == 8) {
                present.sortByExpirationDate();
            } else {
                getCandyBySugarLevel();
            }
        }                       
    }

    private static void getCandyBySugarLevel() {
        System.out.println("Нижнiй рiвень цукру: ");
        int min = input.nextInt();
        System.out.println("Верхнiй рiвень цукру: ");
        int max = input.nextInt();
        Candy candy = present.getCandyBySugarLevel(min, max);
        if (candy == null) {
            System.out.println("Такої цукерки не знайдено");
        } else {
            System.out.println("Знайдена: " + candy.getClass().getSimpleName() + " \tWeight: " + candy.getWeight() 
                + " \tSugar level: " + candy.getSugarLevel() + " \tCalorie content: " + candy.getCalorieContent() 
                + " \tExpiration date: " + candy.getExpirationDate());
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
                present.put(new ChocolateCandy(Date.valueOf(LocalDate.now().plusDays((int)(ChocolateCandy.getShelfLifeMonths() * 30 * Math.random())))));
                break;
            case 2:
                present.put(new Lollipop(Date.valueOf(LocalDate.now().plusDays((int)(Lollipop.getShelfLifeMonths() * 30 * Math.random())))));
                break;
            case 3:
                present.put(new JellyCandy(Date.valueOf(LocalDate.now().plusDays((int)(JellyCandy.getShelfLifeMonths() * 30 * Math.random())))));
                break;
            case 4:
                present.put(new ChocolateNutCandy(Date.valueOf(LocalDate.now().plusDays((int)(ChocolateNutCandy.getShelfLifeMonths() * 30 * Math.random())))));
                break;
            case 5:
                present.put(new ChocolateNougatCandy(Date.valueOf(LocalDate.now().plusDays((int)(ChocolateNougatCandy.getShelfLifeMonths() * 30 * Math.random())))));
                break;
            case 6:
                present.put(new ChocolateLiquorCandy(Date.valueOf(LocalDate.now().plusDays((int)(ChocolateLiquorCandy.getShelfLifeMonths() * 30 * Math.random())))));
                break;
        }
    }
}