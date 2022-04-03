import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Alert;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.*;
import java.sql.*;
import java.sql.Connection;
import org.openqa.selenium.By;

import shared.Session;
import shared.Environment;
import shared.KS.login;
import shared.KS.navigate;
import shared.KS.books;
import shared.KS.topics;
import shared.KS.studySessions;
import shared.KS.operations;

public class scrapKS
{
// members
  public static String headlessMode;
  public static WebDriver driver;

  public static void main(String[] args) throws Exception
  {
    if(args[0].length() > 0){headlessMode = args[0];}

    int sleepTime = 1000;

    String baseUrl = "https://www.kaplanlearn.com/login";
    String username = "cbeerli1980";
    String password = "CityBarce!0na";
    String course = "CFA Level II February";

//calling things
    driver = Session.setUp(headlessMode);
    Session.getBaseURL(baseUrl);
    Session.timeout(sleepTime);

    while(!login.doLogin(username,password,driver))
    {
      Session.getBaseURL(baseUrl);
      Session.timeout(sleepTime);
    }

    Session.timeout(sleepTime);
    navigate.selectCourse(course,driver);
    
    Session.timeout(sleepTime);

    for(int i=1;i<=5;i++)
    {
      navigate.selectStudyTools(driver);
      navigate.selectSchweserNotes(driver);
      navigate.selectSchweserNotesBook(i,driver);

      WebElement root = driver.findElement(By.xpath("//*[@id='root']"));

      System.out.println("topic");
      WebElement topic = root.findElement(By.xpath("//div[2]/div/div[1]/div[2]/div[1]"));
      if(!operations.checkFoldStatus(topic,driver)){operations.unfold(topic,driver);}

      System.out.println("studySession");
      WebElement studySession = topic.findElement(By.xpath("//div[2]/button[1]"));
      if(!operations.checkFoldStatus(studySession,driver)){operations.unfold(studySession,driver);}

      System.out.println("reading");
      root.findElement(By.xpath("/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[1]/button[1]")).click();

      System.out.println("page 1");
      root.findElement(By.xpath("/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[1]/a[1]")).click();
//      WebElement nextButton = driver.findElement(By.xpath("//*[@data-test-id='next-course']"));
//      try
//      {
//        if(nextButton.isEnabled())
//        {
//          nextButton.click();
//          System.out.println("next page");
//          driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/a[1]/div/span/div")).sendKeys(Keys.CONTROL + "s");
//	  Session.timeout(1000);
//        }
//      }
//      catch(Exception e)
//      {
//	System.out.println("last page reached");
//      }
//
//      Session.timeout(sleepTime);
//      for(int j=0;j<topics.count(driver);j++)
//      {
//        WebElement topic = topics.get(driver).get(j);
//        if(!operations.checkFoldStatus(topic,driver)){operations.unfold(topic,driver);}
//        
//        for(int k=0;k<studySessions.count(topic,driver);k++)
//        {
//          WebElement studySession = studySessions.get(topic,driver).get(k);
//          if(!operations.checkFoldStatus(studySession,driver)){operations.unfold(studySession,driver);}
//        }
//        Session.timeout(sleepTime);
//      }
//      Session.timeout(sleepTime);
    }
  }
}
