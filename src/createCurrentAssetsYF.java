import org.openqa.selenium.WebDriver;
import java.util.*;
import java.sql.*;

import shared.Session;
import shared.YF.YFlogin;
import shared.YF.YFfunctions;
import shared.DB.DBfunctions;
import shared.DB.select;
import shared.Environment;

public class createCurrentAssetsYF
{
// members
  public static int sleepTimeYF = Environment.getSleepTimeYF();
  public static int sleepTime ,retryCount;
  public static String usernameYF,passwordYF,baseUrlYF;
  public static String symbolColumn,symbolColumnYF,symbolColumnSQ,symbolColumnSD;
  public static String titleColumn,titleColumnYF,titleColumnSQ,titleColumnSD;
  public static String urlDB,nameDB,usernameDB,passwordDB;
  public static String symbolsSQtable,symbolsYFtable,titlesTable,currentAssetsTable;
  public static String currentAssetsTableProperties;

  public static String title,symbolSQ,symbolYF;
  public static WebDriver driver;
 
  public static String headlessMode;

  public static void main(String[] args) throws Exception
  {
    if(args[0].length() > 0){headlessMode = args[0];}

    symbolsYFtable = Environment.getSymbolsYFtable();
    symbolColumn = Environment.getSymbolColumn();
    currentAssetsTable = Environment.getCurrentAssetsTable();
    titleColumn = Environment.getTitleColumn();
    currentAssetsTable = Environment.getCurrentAssetsTable();
    usernameYF = Environment.getUsernameYF();
    passwordYF = Environment.getPasswordYF();
    baseUrlYF = Environment.getBaseUrlYF();

//calling things
    driver = Session.setUp(headlessMode);
    Session.getBaseURL(baseUrlYF);
    YFlogin.doYFlogin(usernameYF,passwordYF,retryCount,driver);
    YFfunctions.goToYFmyPortfolio(retryCount,driver);
    Session.timeout(sleepTimeYF);
    YFfunctions.deleteYFportfolio(currentAssetsTable,retryCount,driver);
    Session.timeout(sleepTimeYF);
    YFfunctions.createYFportfolio(currentAssetsTable,retryCount,driver);
    Session.timeout(sleepTimeYF);
    ResultSet currentAssetsTableResultSet=DBfunctions.query(select.all.get(currentAssetsTable));

    while(currentAssetsTableResultSet.next())
    {
      String title=currentAssetsTableResultSet.getString(titleColumn);
      YFfunctions.addYFsymbol(title,retryCount,driver);
    }

//tear down everything
    Session.tearDownOnSuccess(driver);
  }
}
