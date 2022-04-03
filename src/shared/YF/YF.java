package shared.YF;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.*;

import shared.Session;

public class YF
{
  static int sleepTimeYF = Integer.parseInt(System.getenv("SLEEP_TIME_YF"));

  @Test
  public static void goToPortfolio(String portfolioName,WebDriver driver)
  {
    int k=1;
    WebDriverWait wait = new WebDriverWait(driver, 10);;
    try
    {
      List<WebElement> portfolioTable = driver.findElements(By.xpath(".//*[@data-test='pf-list-table']/tbody/tr"));
      int portfolioTableRowCount = portfolioTable.size();
      for(int i=0;i<portfolioTableRowCount;i=i+2)
      {
        WebElement portfolioTableRow = portfolioTable.get(i);
        List<WebElement> portfolioTableRowElements = portfolioTableRow.findElements(By.xpath(".//a"));
        int portfolioTableRowElementsCount = portfolioTableRowElements.size();
        for(int j=0;j<portfolioTableRowElementsCount;j++)
        {
          String portfolioTableRowElement = portfolioTableRowElements.get(0).getText();
          if(portfolioTableRowElement.contains(portfolioName))
          {
//            Session.timeout(sleepTimeYF);
            portfolioTableRowElements.get(0).click();
          }
        }
      }
      System.out.println("goToPortfolio(): "+portfolioName);
    }
    catch(Exception e)
    {
      System.out.println("goToPortfolio(): table refreshed");
    }
  }

  @Test
  public static void goToMyPortfolio(WebDriver driver)
  {
    String id,xpath,linkText;
    while(true)
    {
      linkText="My Portfolio";
      try
      {
        driver.findElement(By.linkText(linkText)).click();
        System.out.println("goToMyPortfolio(): arrived");
      }
      catch(Exception e)
      {
        System.out.println("goToMyPortfolio(): failed");
      }
      break;
    }
  }

  @Test
  public static void deleteYFportfolio(String portfolioName,WebDriver driver)
  {
    System.out.println("YF: delete portfolio "+portfolioName);
    String id,xpath,linkText;
    xpath="//a[normalize-space(text())='"+portfolioName+"']";
    if(driver.findElement(By.xpath(xpath)).getText() != "")
    {
      while(true)
      {
        try
        {
          try
          {
            driver.findElement(By.xpath(xpath)).click();
          }
          catch(Exception e)
          {
            System.out.println("deleteYFportfolio failed: xpath="+xpath);
          }
          xpath="//*[@data-test='dropdown']/div/span";
          try
          {
            driver.findElement(By.xpath(xpath)).click();
          }
          catch(Exception e)
          {
            System.out.println("deleteYFportfolio failed: xpath="+xpath);
          }
          xpath="//*[@data-test='dropdown']/*[@id='dropdown-menu']/ul/li/button/span[text()='Delete Portfolio']";
          try
          {
            driver.findElement(By.xpath(xpath)).click();
          }
          catch(Exception e)
          {
            System.out.println("deleteYFportfolio failed: xpath="+xpath);
          }
          xpath="//span[text()='Confirm']";
          try
          {
            driver.findElement(By.xpath(xpath)).click();
          }
          catch(Exception e)
          {
            System.out.println("deleteYFportfolio failed: xpath="+xpath);
          }
          System.out.println("YF: deleted portfolio "+portfolioName);
          break;
        }
        catch(Exception e)
        {
          System.out.println("deleteYFportfolio() failed");
        }
        break;
      }
    }
  }

  @Test
  public static String getSymbol(String isin,String baseUrlYF,WebDriver driver)
  {
    String symbol = "";
    try
    {
      WebElement quoteHeaderInfo = driver.findElement(By.id("quote-header-info"));
      symbol = quoteHeaderInfo.findElement(By.xpath(".//div[2]/div[1]/div[1]/h1")).getText().replace(")","").split("\\(")[1];
    }
    catch(Exception e)
    {
      System.out.println("getSymbol(): failed for "+isin);
      symbol = "void";
      Session.getBaseURL(baseUrlYF);
    }
    return symbol;
  }

  @Test
  public static String getSymbolBySearch(String isin,WebDriver driver)
  {
    String symbol;
    try
    {
      driver.findElement(By.id("yfin-usr-qry")).clear();
      driver.findElement(By.id("yfin-usr-qry")).sendKeys(isin);
      Session.timeout(sleepTimeYF);
      WebElement resultQuotes = driver.findElement(By.id("result-quotes-0"));
      symbol = resultQuotes.findElement(By.xpath(".//div[1]/div[1]")).getText();
      return symbol;
    }
    catch(Exception e)
    {
      System.out.println("getSymbolBySearch(): failed "+isin);
      return "void";
    }
  }

  @Test
  public static String getExchange(String isin,WebDriver driver)
  {
    String exchange ="";
    try
    {
      WebElement quoteHeaderInfo = driver.findElement(By.id("quote-header-info"));
      exchange = quoteHeaderInfo.findElement(By.xpath(".//div[2]/div[1]/div[2]/span")).getText().split("-")[0];
    }
    catch(Exception e)
    {
      System.out.println("getExchange(): failed to find exchange for "+isin);
      exchange = "void";
    }
    return exchange;
  }

  @Test
  public static String getSector(String isin,WebDriver driver)
  {
    String sector ;
    try
    {
      WebElement profileYFtable = driver.findElement(By.id("Col1-0-Profile-Proxy"));
      sector = profileYFtable.findElement(By.xpath("//section/div[1]/div/div/p[2]/span[2]")).getText();
    }
    catch(Exception e)
    {
      System.out.println("getSectorYF: no profile found for: "+isin);
      sector = "void";
    }
    return sector;
  }

  @Test
  public static String getIndustry(String isin,WebDriver driver)
  {
    String sector ;
    try
    {
      WebElement profileYFtable = driver.findElement(By.id("Col1-0-Profile-Proxy"));
      sector = profileYFtable.findElement(By.xpath("//section/div[1]/div/div/p[2]/span[4]")).getText();
    }
    catch(Exception e)
    {
      System.out.println("getSectorYF: failed to find profile: "+isin);
      sector = "void";
    }
    return sector;
  }

  @Test
  public static void createPortfolio(String portfolioName,WebDriver driver)
  {
    System.out.println("YF: create portfolio");
    String id,xpath,linkText;
    while(true)
    {
      try
      {
        xpath="//span[text()='Create Portfolio']";
        try
        {
          Session.timeout(sleepTimeYF);
          driver.findElement(By.xpath(xpath)).click();
          Session.timeout(sleepTimeYF);
        }
        catch(Exception e)
        {
          System.out.println("createPortfolio() failed: xpath="+xpath);
        }
        xpath="//*[@placeholder='Enter Portfolio Name']";
        try
        {
          Session.timeout(sleepTimeYF);
          driver.findElement(By.xpath(xpath)).sendKeys(portfolioName);
          Session.timeout(sleepTimeYF);
        }
        catch(Exception e)
        {
          System.out.println("createPortfolio() failed: xpath="+xpath);
        }
        xpath="//span[text()='Submit']";
        try
        {
          driver.findElement(By.xpath(xpath)).click();
        }
        catch(Exception e)
        {
          System.out.println("createPortfolio() failed: xpath="+xpath);
        }
        System.out.println("createPortfolio(): created "+portfolioName);
        break;
      }
      catch(Exception e)
      {
        System.out.println("createPortfolio() failed");
      }
      break;
    }
  }

  @Test
  public static void addSymbol(String symbol,WebDriver driver)
  {
    System.out.println("YF: add symbol "+symbol);
    String id,xpath,linkText;
    while(true)
    {
      try
      {
        xpath="//span[text()='Add Symbol']";
        try
        {
          driver.findElement(By.xpath(xpath)).click();
        }
        catch(Exception e)
        {
          System.out.println("addYFsymbol() failed: xpath="+xpath);
        }
        xpath="//*[@data-finsrch='quote']/input";
        try
        {
          driver.findElement(By.xpath(xpath)).click();
        }
        catch(Exception e)
        {
          System.out.println("addYFsymbol() failed: xpath="+xpath);
        }
        xpath="//*[@data-finsrch='quote']/input";
        try
        {
          driver.findElement(By.xpath(xpath)).sendKeys(symbol);
          Session.timeout(sleepTimeYF);
        }
        catch(Exception e)
        {
          System.out.println("addYFsymbol() failed: xpath="+xpath);
        }
        xpath="//*[@data-finsrch='quote']/input";
        try
        {
          Session.timeout(sleepTimeYF);
          driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
          Session.timeout(sleepTimeYF);
        }
        catch(Exception e)
        {
          System.out.println("addYFsymbol() failed: xpath="+xpath);
        }
        System.out.println("YF: added symbol "+symbol);
        break;
      }
      catch(Exception e)
      {
        System.out.println("addYFsymbol() failed");
      }
      break;
    }
  }
}
