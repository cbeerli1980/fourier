// Generated by Selenium IDE
package shared.DB;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import shared.Session;
import shared.Environment;

public class DB
{

  static String urlDB = Environment.get.url.base("DB");
  static String nameDB = Environment.get.name("DB");
  static String usernameDB = Environment.get.username("DB");
  static String passwordDB = Environment.get.password("DB");

  public static Connection getConnection(String urlDB,String nameDB,String usernameDB,String passwordDB)
  {
    Connection connectionDB;
    try
    {
//      System.out.println("getDBconnection(): attempt urlDB="+urlDB+" nameDB="+nameDB+" usernameDB="+usernameDB+" passwordDB="+passwordDB);
      connectionDB = DriverManager.getConnection(urlDB+"/"+nameDB,usernameDB,passwordDB);
      System.out.println("getDBconnection(): successful");
      return connectionDB;
    }
    catch(Exception e)
    {
      System.out.println("getDBconnection(): failed");
      return null;
    }
  }

  public static ResultSet executeQuery(Connection connectionDB,String statement)
  {
    try
    {
      System.out.println(statement);
      Statement query = connectionDB.createStatement();
      return query.executeQuery(statement);
    }
    catch(Exception e)
    {
      System.out.println("executeQuery(): failed");
      return null;
    }
  }

  public static Boolean execute(Connection connectionDB,String statement)
  {
    try
    {
      System.out.println(statement);
      Statement query = connectionDB.createStatement();
      query.execute(statement);
      return true;
    }
    catch(Exception e)
    {
      System.out.println("execute(): failed");
      return false;
    }
  }
}
