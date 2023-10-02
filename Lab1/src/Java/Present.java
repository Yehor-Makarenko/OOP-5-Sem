package Java;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Java.Candies.Candy;

public class Present {
  private ArrayList<Candy> candies = new ArrayList<Candy>(); 
  private int weight = 0; 

  public static void init() {      
    InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/resources/dbconfig.properties");    
    Properties props = new Properties();
    try {
      props.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(props.getProperty("url"));
    System.out.println(props.getProperty("username"));
    System.out.println(props.getProperty("password"));
    try {      
      Connection connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
      System.out.println("Connection was succesful");
      connection.close();
    } catch (SQLException e) {      
      e.printStackTrace();
    }
  }

  public void put(Candy candy) {
    candies.add(candy);
    weight += candy.getWeight();
  }

  public int getWeight() {
    return weight;
  }

  public void sortByWeight() {
    candies.sort((c1, c2) -> c1.getWeight() - c2.getWeight());    
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
