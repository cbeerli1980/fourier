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

public class update
{

  static String statement;

  public static class set
  {
    public static class c1
    {
      public static class v1
      {
        public static class whereByInt
        {
          public static Boolean get(Connection connectionDB,String tableName,String c1,String v1,String c,int v)
          {
            statement = "UPDATE "+tableName+" SET "+c1+"='"+v1+"' WHERE "+c+"='"+v+"'";
    	    return DB.execute(connectionDB,statement);
          }
        }

        public static class whereByString
        {
          public static class c
          {
            public static class v
            {
              public static Boolean get(Connection connectionDB,String tableName,String c1,String v1,String c,String v)
              {
                statement = "UPDATE "+tableName+" SET "+c1+"='"+v1+"' WHERE "+c+"='"+v+"'";
                return DB.execute(connectionDB,statement);
              }
            }
          }
        }

        public static class c2
        {
          public static class v2
          {
            public static class whereByString
            {
              public static class c
              {
                public static class v
                {
                  public static Boolean get(Connection connectionDB,String tableName,String c1,String v1,String c2,String v2,String c,String v)
                  {
                    statement = "UPDATE "+tableName+" SET "+c1+"='"+v1+"',"+c2+"='"+v2+"' WHERE "+c+"='"+v+"'";
                    return DB.execute(connectionDB,statement);
                  }
                }
              }
            }
            public static class c3
            {
              public static class v3
              {
                public static class whereByString
                {
                  public static class c
                  {
                    public static class v
                    {
                      public static Boolean get(Connection connectionDB,String tableName,String c1,String v1,String c2,String v2,String c3,String v3,String c,String v)
                      {
                        statement = "UPDATE "+tableName+" SET "+c1+"='"+v1+"',"+c2+"='"+v2+"',"+c3+"='"+v3+"' WHERE "+c+"='"+v+"'";
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
  }
}
