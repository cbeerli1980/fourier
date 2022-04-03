package shared.SQ;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.util.*;

import shared.Session;

public class SQframes
{
  @Test
  public static void setSQhnav(WebDriver driver)
  {
    try
    {
      driver.switchTo().defaultContent();
      driver.switchTo().frame("hnav");
    }
    catch(Exception e)
    {
      System.out.println("setSQhnav(): failed");
      Session.tearDownOnError(driver);
    }
  }

  @Test
  public static void setSQrequest(WebDriver driver)
  {
    try
    {
      driver.switchTo().defaultContent();
      driver.switchTo().frame("request");
    }
    catch(Exception e)
    {
      System.out.println("setSQrequest(): failed");
      Session.tearDownOnError(driver);
    }
  }

  @Test
  public static void setSQsqmain(WebDriver driver)
  {
    try
    {
      driver.switchTo().defaultContent();
      driver.switchTo().frame("sqmain");
    }
    catch(Exception e)
    {
      System.out.println("setSQsqmain(): failed");
      Session.tearDownOnError(driver);
    }
  }
}
