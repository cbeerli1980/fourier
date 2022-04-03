import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.*;
import java.sql.*;
import java.sql.Connection;

import shared.Session;
import shared.YF.YFlogin;
import shared.YF.YF;
import shared.YF.quotes;
import shared.DB.DB;
import shared.DB.select;
import shared.Environment;

public class fetchQuotesYF
{
  public static String headlessMode;

  public static void main(String[] args) throws Exception
  {
    if(args[0].length() > 0){headlessMode = args[0];}

    int sleepTimeYF			= Environment.get.sleepTime("YF");
    int sleepTime			= Environment.get.sleepTime();

    String stocksTable			= Environment.get.table("stocks");
    String titlesTable			= Environment.get.table("titles");
    String classificationsTableYF	= Environment.get.table("classifications","YF");

    String sectorColumn			= Environment.get.column.sector();
    String sectorColumnYF		= Environment.get.column.sector("YF");

    String symbolColumnYF		= Environment.get.column.symbol("YF");
    String isinColumn			= Environment.get.column.isin();
    String titleColumn			= Environment.get.column.title();

    String usernameYF			= Environment.get.username("YF");
    String usernameDB			= Environment.get.username("DB");

    String passwordYF			= Environment.get.password("YF");

    String baseUrlYF			= Environment.get.url.base("YF");

    String urlDB			= Environment.get.db.url.local();
    String passwordDB			= Environment.get.db.name("symbols").password();

    String nameDB = Environment.getNameDB();
    String usernameDB = Environment.getUsernameDB();
    String passwordDB = Environment.getPasswordDB();

    Connection db = DB.getConnection(urlDB,nameDB,usernameDB,passwordDB);

    ResultSet sectorsResultSet = select.c1.get(db,sectorColumn,classificationsTableYF);
    ArrayList<String> sectors = new ArrayList<String>();

    while(sectorsResultSet.next())
    {
      String sector = sectorsResultSet.getString(sectorColumn);
      if(!sectors.contains(sector))
      {
        sectors.add(sector);
      }
    }

    for(String sector : sectors)
    {
      System.out.println("sector: "+sector);
      //WebDriver driver = Session.setUpProfile("yf",headlessMode);
      WebDriver driver = Session.setUp(headlessMode);

      int i=0;
      ResultSet isinsBySector = select.c1.whereCequalsV.get(db,isinColumn,titlesTable,sectorColumnYF,sector);
      while(isinsBySector.next())
      {
        String isin = isinsBySector.getString(isinColumn);
        ResultSet symbolResultSet = select.c1.whereCequalsV.get(db,symbolColumnYF,stocksTable,isinColumn,isin);
        if(symbolResultSet.next())
        {
	  String symbol = symbolResultSet.getString(symbolColumnYF);
	  if(i>0)
          {
            Session.openTab(driver);
            Session.switchToTabByNumber(i,driver);
          }
	  String quoteUrlYF = baseUrlYF+"/quote/"+symbol;
          Session.getBaseURL(quoteUrlYF);
	  System.out.println(quoteUrlYF);
          i++;
        }
      }
      Session.tearDownOnSuccess(driver);
    }

//      Session.getBaseURL(baseUrlYF);
//      if(i>0)
//      {
//        Session.openTab(driver);
//        Session.switchToTabByNumber(i,driver);
//        Session.getBaseURL(baseUrlYF);
//      }
//
//      if(i==0)
//      {
//        YF.goToMyPortfolio(driver);
//      }
//      YF.goToPortfolio(sector,driver);
//      Session.timeout(sleepTimeYF);
//      i++;
//    }
//
//    Session.timeout(30000);
//
//    for(String sector : sectors)
//    {
//      int tabNumber = Session.getTabNumberByName(sector,driver);
//      Session.switchToTabByNumber(tabNumber,driver);
//      for(String price : quotes.price(driver))
//      {
//	System.out.println(price);
//      }
//    }
  }
}
