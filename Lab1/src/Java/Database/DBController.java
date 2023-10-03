package Java.Database;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import Java.Candies.Candy;

public class DBController {   
  public static int getMaxPresentID() {      
    InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/resources/dbconfig.properties"); 
    Properties props = new Properties();  

    try {
      props.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } 

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return Integer.parseInt(props.getProperty("maxPresentID"));
  }

  public static ArrayList<Candy> getCandiesByPresentID(int id) {
    InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/resources/dbconfig.properties"); 
    ArrayList<Candy> candies = new ArrayList<Candy>(); 
    Properties props = new Properties();
    Connection connection;

    try {
      props.load(inputStream);      
    } catch (IOException e) {
      e.printStackTrace();
    }    

    try {      
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(
        "SELECT * FROM candy_presents.candies " +
        "WHERE present_id=" + id
      );
      
      while (rs.next()) {
        candies.add(Candy.createCandyByClassName(rs.getString("type")));
      }
      
      rs.close();
      connection.close();
    } catch (SQLException | ClassNotFoundException e) {      
      e.printStackTrace();
    }

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return candies;
  }

  public static int getPresentWeightByID(int id) {    
    InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/resources/dbconfig.properties"); 
    int weight = 0;
    Properties props = new Properties();
    Connection connection;

    try {
      props.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }    

    try {      
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(
        "SELECT * FROM candy_presents.presents " +
        "WHERE id=" + id
      );
      
      rs.next();
      weight = rs.getInt("weight");      
      
      rs.close();
      connection.close();
    } catch (SQLException | ClassNotFoundException e) {      
      e.printStackTrace();
    }

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return weight;
  }

  public static void createPresent() {
    InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/resources/dbconfig.properties"); 
    Properties props = new Properties();
    Connection connection;

    try {
      props.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }    

    try {      
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

      Statement stmt = connection.createStatement();
      stmt.executeUpdate(        
        "INSERT INTO candy_presents.presents (id, weight, candies_number)" +
        "VALUES (" + (Integer.parseInt(props.getProperty("maxPresentID")) + 1) + ", 0, 0)"        
      );
      connection.close();
    } catch (SQLException | ClassNotFoundException e) {      
      e.printStackTrace();
    }

    props.setProperty("maxPresentID", String.valueOf(Integer.parseInt(props.getProperty("maxPresentID")) + 1));
    try {
      props.store(new FileWriter("Lab1/src/resources/dbconfig.properties"), "");
      inputStream.close();
    } catch (IOException e) {            
      e.printStackTrace();
    }    
  }

  public static void addCandyToPresent(Candy candy, int presentId, int weight, int candiesNumber) {
    InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/resources/dbconfig.properties"); 
    Properties props = new Properties();
    Connection connection;

    try {
      props.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }    

    try {      
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

      Statement stmt = connection.createStatement();      
      stmt.executeUpdate(        
        "INSERT INTO candy_presents.candies (present_id, type, weight, sugar_level, calorie_content, expiration_date, filling)" +
        " VALUES (" + presentId + ", '" + candy.getClass().getSimpleName() + "', " + candy.getWeight() + 
        ", " + candy.getSugarLevel() + ", " + candy.getCalorieContent() + ", '" + candy.getExpirationDate() + "', '" + candy.getFilling() + "')"        
      );

      stmt.executeUpdate("UPDATE candy_presents.presents " + 
      "SET weight=" + weight + ", candies_number=" + candiesNumber + " WHERE (id=" + presentId + ")");
      connection.close();
    } catch (SQLException | ClassNotFoundException e) {      
      e.printStackTrace();
    }

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
