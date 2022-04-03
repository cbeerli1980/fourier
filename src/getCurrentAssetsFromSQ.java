import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;

import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import shared.Session;
import shared.SQ.SQlogin;
import shared.SQ.SQframes;
import shared.SQ.SQfunctions;
import shared.YF.YFlogin;
import shared.YF.YFfunctions;
import shared.DB.DBfunctions;
import shared.DB.select;
import shared.DB.insert;
import shared.Environment;

public class getCurrentAssetsFromSQ
{
// members
  static String[] currentAssetsList; 
  public static String title,headlessMode;
  public static WebDriver driver;
  public static Connection connectionDB;
  public static List<WebElement> assetsLineItems;

  public static void main(String[] args) throws Exception
  {
    if(args[0].length() > 0){headlessMode = args[0];}

    int sleepTime = Environment.getSleepTime();
    int sleepTimeSQ = Environment.getSleepTimeSQ();
    int retryCount = Environment.getRetryCount();

    String symbolsSQtable = Environment.getSymbolsSQtable();
    String symbolColumnSQ = Environment.getSymbolColumnSQ();
    String symbolColumn = Environment.getSymbolColumn();
    String titleColumn = Environment.getTitleColumn();
    String titleColumnSQ = Environment.getTitleColumnSQ();
    String currentAssetsTableProperties = Environment.getCurrentAssetsTableProperties();
    String currentAssetsTable = Environment.getCurrentAssetsTable();
    String usernameSQ = Environment.getUsernameSQ();
    String passwordSQ = Environment.getPasswordSQ();
    String baseUrlSQ = Environment.getBaseUrlSQ();

//calling things
    driver = Session.setUp(headlessMode);
    Session.getBaseURL(baseUrlSQ);
    SQframes.setSQhnav(retryCount,driver);
    SQlogin.doSQlogin(usernameSQ,passwordSQ,retryCount,driver);
    SQframes.setSQrequest(retryCount,driver);
    SQfunctions.goToSQhome(retryCount,driver);
    SQframes.setSQsqmain(retryCount,driver);
    currentAssetsList=SQfunctions.getCurrentAssetsList(driver);
    DBfunctions.access(DBfunctions.dropDBtable(currentAssetsTable));
    DBfunctions.access(DBfunctions.createDBtable(currentAssetsTable,currentAssetsTableProperties));
    for (String currentAssetSymbol : SQfunctions.getAssetSymbols(currentAssetsList))
    {
      ResultSet titleSet = DBfunctions.query(select.column.whereColumnEqualsValue.get(titleColumn,symbolsSQtable,symbolColumn,currentAssetSymbol));
      
      while(titleSet.next())
      {
        title=titleSet.getString(titleColumn);
        DBfunctions.access(insert.column1.value1.column2.value2.get(currentAssetsTable,titleColumn,title,symbolColumnSQ,currentAssetSymbol));
	break;
      }
      System.out.println("getCurrentAssetsFromSQ: unable to match "+currentAssetSymbol+" at "+symbolsSQtable); 
    }
    Session.tearDownOnSuccess(driver);
  }
}
