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

public class insert 
{

  static String statement;

  public static class c1
  {
    public static class v1
    {
      public static Boolean get(Connection connectionDB,String tableName,String c1,String v1)
      {
        statement = "INSERT INTO "+tableName+"("+c1+") VALUES('"+v1+"')";
	System.out.println(statement);
	return DB.execute(connectionDB,statement);
      }

      public static class c2
      {
        public static class v2
        {
	  public static Boolean get(Connection connectionDB,String tableName,String c1,String v1,String c2,String v2)
	  {
	    statement = "INSERT INTO "+tableName+"("+c1+","+c2+") VALUES('"+v1+"','"+v2+"')";
	    System.out.println(statement);
            return DB.execute(connectionDB,statement);
	  }

	  public static class c3
	  {
	    public static class v3
	    {
	      public static Boolean get(Connection connectionDB,String tableName,String c1,String v1,String c2,String v2,String c3,String v3)
	      {
	        statement = "INSERT INTO "+tableName+"("+c1+","+c2+","+c3+") VALUES('"+v1+"','"+v2+"','"+v3+"')";
	        System.out.println(statement);
	        return DB.execute(connectionDB,statement);
	      }

	      public static class c4
	      {
		public static class v4
		{
	          public static Boolean get(Connection connectionDB,String tableName,String c1,String v1,String c2,String v2,String c3,String v3,String c4,String v4)
	          {
	            statement = "INSERT INTO "+tableName+"("+c1+","+c2+","+c3+","+c4+") VALUES('"+v1+"','"+v2+"','"+v3+"','"+v4+"')";
	            System.out.println(statement);
	            return DB.execute(connectionDB,statement);
		  }
		}
	      }
	    }
	  }
	}
      }
    }
  }
}
