package shared.MW;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import java.util.*;

import shared.Session;
import shared.Environment;

public class MW
{
  static int sleepTimeMW = Environment.get.sleepTime("MW");

  @Test
  public static String getProfileUrl(String countryCode,String symbol,String baseUrl)
  {
    String profileUrl;
    if(countryCode!="void")
    {
      profileUrl = baseUrl+"/investing/stock/"+symbol+"/company-profile?countrycode="+countryCode+"&mod=mw_quote_tab";
    }
    else
    {
      profileUrl = baseUrl+"/investing/stock/"+symbol+"/company-profile?mod=mw_quote_tab";
    }
    return profileUrl;   
  }

  @Test
  public static String getCountryCode(String symbol)
  {
    if(symbol.contains(":"))
    {
      return symbol.split(":")[0];
    }
    else
    {
      return "void";
    }
  }

  @Test
  public static String getSymbolRaw(String symbol)
  {
    if(symbol.contains(":"))
    {
      return symbol.split(":")[1];
    }
    else
    {
      return symbol;
    }
  }

  @Test
  public static void clickDismiss(WebDriver driver)
  {
    try
    {
      driver.findElement(By.cssSelector(".pushly_popover-buttons-dismiss")).click();
    }
    catch(Exception e)
    {
      System.out.println("clickDismiss: failed");
    }
  }

  @Test
  public static Boolean clickAgree(String baseUrl,WebDriver driver)
  {
    while(!attemptClickAgree(driver))
    {
      System.out.println("clickAgree(): failed, reloading page");
      Session.getBaseURL(baseUrl);
      Session.timeout(sleepTimeMW);
    }
    return true;
  }

  @Test
  public static Boolean attemptClickAgree(WebDriver driver)
  {
    try
    {
      driver.switchTo().frame(4);
      driver.findElement(By.xpath(".//*[@title='YES, I AGREE']")).click();
      return true;
    }
    catch(Exception e)
    {
      System.out.println("clickAgree(): failed");
      return false;
    }
  }

  @Test
  public static Boolean goToSymbolSearch(String baseUrl,WebDriver driver)
  {
    while(!attemptGoToSymbolSearch(driver))
    {
      System.out.println("goToSymbolSearch() failed, reloading page");
      Session.getBaseURL(baseUrl);
      Session.timeout(sleepTimeMW);
    }
    return true;
  }

  @Test
  public static Boolean attemptGoToSymbolSearch(WebDriver driver)
  {
    try
    {
      driver.switchTo().defaultContent();
      driver.findElement(By.cssSelector(".btn--search")).click();
      return true;
    }
    catch(Exception e)
    {
      System.out.println("goToSymbolSearch(): failed");
      return false;
    }
  }

  @Test
  public static String getSymbol(String isin,WebDriver driver)
  {
    String symbol;
    //int sleepTimeMW = Environment.getSleepTimeMW();
    try
    {
      driver.findElement(By.id("search")).clear();
      driver.findElement(By.id("search")).sendKeys(isin);
      Session.timeout(sleepTimeMW);
      Session.timeout(sleepTimeMW);
      symbol = driver.findElement(By.xpath(".//*[@class='j-symbol']")).getText();
    }
    catch(Exception e)
    {
      System.out.println("getSymbol(): failed "+isin);
      System.out.println(e);
      symbol = "void";
    }
    return symbol;
  }

  @Test
  public static String getSector(String isin,WebDriver driver)
  {
    String sector ;
    try
    {
      WebElement list = driver.findElement(By.xpath(".//*[@class='group left']"));
      sector = list.findElement(By.xpath(".//div/ul/li[2]/span")).getText();
      System.out.println("getSector(): "+sector);
    }
    catch(Exception e)
    {
      System.out.println("getSector(): failed "+isin);
      sector = "void";
    }
    return sector;
  }

  @Test
  public static String getIndustry(String isin,WebDriver driver)
  {
    String industry ;
    try
    {
      WebElement list = driver.findElement(By.xpath(".//*[@class='group left']"));
      industry = list.findElement(By.xpath(".//div/ul/li[1]/span")).getText();
    }
    catch(Exception e)
    {
      System.out.println("getIndustry(): failed "+isin);
      industry = "void";
    }
    return industry;
  }
}
