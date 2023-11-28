package oop;

import java.util.Scanner;

import oop.gem.enums.GemColor;
import oop.gem.enums.GemPreciousness;
import oop.xmlParser.GemsParser;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static String xmlSchemaPath = "lab2/src/main/resources/gems.xsd";
    private static String xmlFilePath = "lab2/src/main/resources/gems.xml";
    private static GemsParser gp;

    public static void main(String[] args) {
        gp = new GemsParser(xmlSchemaPath);
        gp.loadFromFile(xmlFilePath);
        showMenu();
        gp.saveToFile(xmlFilePath);
    }

    private static void showMenu() {
        System.out.println("\n1) Переглянути камiння");
        System.out.println("2) Вибрати камiння");
        System.out.println("3) Додати камiння"); 
        System.out.println("4) Видалити камiння"); 
        System.out.println("5) Сортувати камiння"); 
        System.out.println("6) Завершити роботу"); 
        int num = input.nextInt();
        int index;
        
        switch (num) {
            case 1:
                gp.print();
                break;
            case 2:
                System.out.println("\nВведiть номер:");
                index = input.nextInt();
                System.out.println(gp.getGem(index - 1));
                break;
            case 3:
                System.out.println("\nВведiть назву:");
                String name = input.nextLine();
                System.out.println("Введiть дорогоцiннiсть (1 - дорогоцiнний, 2 - напiвдорогоцiнний):");
                GemPreciousness preciousness = input.nextInt() == 1 ? GemPreciousness.PRECIOUS : GemPreciousness.SEMI_PRECIOUS;
                System.out.println("Введiть мiсце видобування:");
                String origin = input.nextLine();
                System.out.println("Введiть колiр (1 - зелений, 2 - червоний, 3 - жовтий, 4 - синiй, 5 - фiолетовий):");
                int colorId = input.nextInt();
                GemColor color = colorId == 1 ? GemColor.GREEN : colorId == 2 ? GemColor.RED : colorId == 3 ? GemColor.YELLOW :
                    colorId == 4 ? GemColor.BLUE : GemColor.PURPLE;
                System.out.println("Введiть прозорiсть (1-100):");
                double transparency = input.nextDouble();
                System.out.println("Введiть кiлькiсть граней (3-15):");
                int facetCount = input.nextInt();
                System.out.println("Введiть вагу в каратах:");
                double value = input.nextDouble();
                gp.addGem(name, preciousness, origin, color, transparency, facetCount, value);
                break;
            case 4:
                System.out.println("\nВведiть номер:");
                index = input.nextInt();
                gp.deleteGem(index - 1);
                break;
            case 5:
                System.out.println("\nСортувати за (1 - назвою, 2 - дорогоцiннiстю, 3 - мiсцем видобування, 4 - кольором, 5 - прозорiстю," + 
                " 6 - кiлькiстю граней, 7 - вагою):");
                index = input.nextInt();
                if (index == 1) gp.sortByName();
                else if (index == 2) gp.sortByPreciousness();
                else if (index == 3) gp.sortByOrigin();
                else if (index == 4) gp.sortByColor();
                else if (index == 5) gp.sortByTransparency();
                else if (index == 6) gp.sortByFacetCount();
                else gp.sortByValue();
                break;
            default:
                return;
        }
        showMenu();
    }
}