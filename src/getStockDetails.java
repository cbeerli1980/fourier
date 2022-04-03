import org.openqa.selenium.WebDriver;
import java.util.*;
import java.sql.*;
import java.sql.Connection;

import shared.Session;
import shared.YF.YF;
import shared.MW.MW;
import shared.DB.DB;
import shared.DB.select;
import shared.DB.update;
import shared.DB.insert;
import shared.Environment;

public class getStockDetails
{
  public static void main(String[] args) throws Exception
  {
    String headlessMode = "";
    if(args[0].length() > 0){headlessMode = args[0];}

    String stocksTable = Environment.getStocksTable();
    String candidatesTable = Environment.getCandidatesTable();
    String titlesTable = Environment.getTitlesTable();
    String isinColumn = Environment.getIsinColumn();
    String exchangeColumn = Environment.getExchangeColumn();
    String titleColumn = Environment.getTitleColumn();
    String titleColumnSQ = Environment.getTitleColumnSQ();

    int sleepTimeYF = Environment.getSleepTimeYF();
    String baseUrlYF = Environment.getBaseUrlYF();
    String symbolColumnYF = Environment.getSymbolColumnYF();
    String industryColumnYF = Environment.getIndustryColumnYF();
    String sectorColumnYF = Environment.getSectorColumnYF();

    int sleepTimeMW = Environment.getSleepTimeMW();
    String baseUrlMW = Environment.getBaseUrlMW();
    String symbolColumnMW = Environment.getSymbolColumnMW();
    String industryColumnMW = Environment.getIndustryColumnMW();
    String sectorColumnMW = Environment.getSectorColumnMW();

    String urlDB = Environment.getUrlDB();
    String nameDB = Environment.getNameDB();
    String usernameDB = Environment.getUsernameDB();
    String passwordDB = Environment.getPasswordDB();

    WebDriver driver = Session.setUpProfile("mw",headlessMode);
    Connection db = DB.getConnection(urlDB,nameDB,usernameDB,passwordDB);

    ResultSet ISINsAndSymbols = select.all.get(db,stocksTable);
    while(ISINsAndSymbols.next())
    {
      String isin = ISINsAndSymbols.getString(isinColumn);
      String symbolYF = ISINsAndSymbols.getString(symbolColumnYF);
      String symbolMW = MW.getSymbolRaw(ISINsAndSymbols.getString(symbolColumnMW));
      String countryCode = MW.getCountryCode(ISINsAndSymbols.getString(symbolColumnMW));

      ResultSet rsTitle = select.c1.whereCequalsV.get(db,titleColumnSQ,candidatesTable,isinColumn,isin);
      while(rsTitle.next())
      {
        String title = rsTitle.getString(titleColumnSQ);
 
        ResultSet rsTitleExists = select.all.whereCequalsV.get(db,titlesTable,titleColumn,title);
        if(!rsTitleExists.next())
        {
          insert.c1.v1.get(db,titlesTable,titleColumn,title);
          String baseUrlYFprofile = baseUrlYF+"/quote/"+symbolYF+"/profile?p="+symbolYF;
          Session.getBaseURL(baseUrlYFprofile);
 
          String exchange = YF.getExchange(isin,driver);
          String sectorYF = YF.getSector(isin,driver);
          String industryYF = YF.getIndustry(isin,driver);

          String baseUrlMWprofile = MW.getProfileUrl(countryCode,symbolMW,baseUrlMW);
	  Session.getBaseURL(baseUrlMWprofile);
	  Session.timeout(sleepTimeMW);

	  String sectorMW = MW.getSector(isin,driver);
	  String industryMW = MW.getIndustry(isin,driver);
 
          update.set.c1.v1.c2.v2.c3.v3.whereByString.c.v.get(db,titlesTable,exchangeColumn,exchange,sectorColumnYF,sectorYF,industryColumnYF,industryYF,titleColumn,title);
          update.set.c1.v1.c2.v2.whereByString.c.v.get(db,titlesTable,sectorColumnMW,sectorMW,industryColumnMW,industryMW,titleColumn,title);
        }
      }
    }
    Session.tearDownOnSuccess(driver);
  }
}
