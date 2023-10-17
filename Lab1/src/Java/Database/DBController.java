package Java.Database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import Java.Candies.Candy;
import Java.Present.PresentSizes.PresentSizes;

public class DBController {  
  private static String dbconfigPath = "Lab1/src/resources/dbconfig.properties";
  private static Connection connection;
  private static Properties props = new Properties();

  public static void openConnection() {
    try {
      props.load(new FileInputStream(dbconfigPath));
    } catch (IOException e) {
      e.printStackTrace();
    } 

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
    } catch (SQLException | ClassNotFoundException e) {      
      e.printStackTrace();
    }    
  }

  public static void closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {      
      e.printStackTrace();
    }
  }
  
  public static int getMaxPresentID() {          
    try {
      props.load(new FileInputStream(dbconfigPath));
    } catch (IOException e) {
      e.printStackTrace();
    } 

    return Integer.parseInt(props.getProperty("maxPresentID"));
  }

  public static PresentSizes getPresentSizeById(int id) {  
    PresentSizes size = null;
    try {            
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(
        "SELECT size_id FROM candy_presents.presents " +
        "WHERE id=" + id
      );
      
      rs.next();
      size = PresentSizes.getById(rs.getInt("size_id"));
      rs.close();
    } catch (SQLException e) {      
      e.printStackTrace();
    }
    
    return size;
  }

  public static ArrayList<Candy> getCandiesByPresentID(int id) {    
    ArrayList<Candy> candies = new ArrayList<Candy>();     

    try {
      props.load(new FileInputStream(dbconfigPath));      
    } catch (IOException e) {
      e.printStackTrace();
    }    

    try {            
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(
        "SELECT type_id, expiration_date FROM candy_presents.candies " +
        "WHERE present_id=" + id
      );
      
      while (rs.next()) {
        Date expirationDate = Date.valueOf(rs.getString("expiration_date"));
        candies.add(Candy.createCandyByClassId(rs.getInt("type_id"), expirationDate));
      }
      
      rs.close();
    } catch (SQLException e) {      
      e.printStackTrace();
    }
    
    return candies;
  }

  public static void createPresent(PresentSizes size) {            
    try {
      props.load(new FileInputStream(dbconfigPath));
    } catch (IOException e) {
      e.printStackTrace();
    }    

    int maxPresentID = Integer.parseInt(props.getProperty("maxPresentID"));

    try {                  
      Statement stmt = connection.createStatement();
      stmt.executeUpdate(        
        "INSERT INTO candy_presents.presents (id, size_id)" +
        "VALUES (" + (maxPresentID + 1) + ", " + size.getId() + ")"        
      );
    } catch (SQLException e) {      
      e.printStackTrace();
    }

    props.setProperty("maxPresentID", String.valueOf(maxPresentID + 1));
    try {
      props.store(new FileOutputStream(dbconfigPath), "");      
    } catch (IOException e) {            
      e.printStackTrace();
    }    
  }

  public static void addCandyToPresent(int presentId, Candy candy) {            
    try {
      props.load(new FileInputStream(dbconfigPath));
    } catch (IOException e) {
      e.printStackTrace();
    }    

    try {                  
      Statement stmt = connection.createStatement();  

      ResultSet rs = stmt.executeQuery(
        "SELECT id FROM candy_presents.candy_types " +
        "WHERE type_name='" + candy.getClass().getSimpleName() + "'"
      );    
      rs.next();
      int candyTypeId = rs.getInt("id");

      stmt.executeUpdate(        
        "INSERT INTO candy_presents.candies (present_id, type_id, expiration_date)" +
        " VALUES (" + presentId + ", " + candyTypeId + ", '" + candy.getExpirationDate() + "')"        
      );      
    } catch (SQLException e) {      
      e.printStackTrace();
    }
  }
}
