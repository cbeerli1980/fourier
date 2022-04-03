import org.openqa.selenium.WebDriver;
import java.util.*;
import java.sql.*;
import java.sql.Connection;

import shared.Session;
import shared.YF.YFlogin;
import shared.YF.YF;
import shared.YF.checkPortfolioExists;
import shared.YF.checkSymbolExists;
import shared.YF.goToPortfolio;
import shared.DB.DB;
import shared.DB.select;
import shared.Environment;

public class createPortfolioBySectorYF
{
  public static String headlessMode;

  public static void main(String[] args) throws Exception
  {
    if(args[0].length() > 0){headlessMode = args[0];}

    int sleepTimeYF = Environment.getSleepTimeYF();
    String sectorColumn = Environment.getSectorColumn();
    String sectorColumnYF = Environment.getSectorColumnYF();
    String isinColumn = Environment.getIsinColumn();
    String classificationsTableYF = Environment.getClassificationsTableYF();
    String titlesTable = Environment.getTitlesTable();
    String stocksTable = Environment.getStocksTable();
    String symbolColumn = Environment.getSymbolColumn();
    String symbolColumnYF = Environment.getSymbolColumnYF();
    String titleColumn = Environment.getTitleColumn();
    String usernameYF = Environment.getUsernameYF();
    String passwordYF = Environment.getPasswordYF();

    String baseUrlYF = Environment.getBaseUrlYF();

    String urlDB = Environment.getUrlDB();
    String nameDB = Environment.getNameDB();
    String usernameDB = Environment.getUsernameDB();
    String passwordDB = Environment.getPasswordDB();


    WebDriver driver = Session.setUp(headlessMode);
    Session.getBaseURL(baseUrlYF);
    YFlogin.doYFlogin(usernameYF,passwordYF,driver);
    YF.goToMyPortfolio(driver);
    Session.timeout(sleepTimeYF);

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
      if(!checkPortfolioExists.get(sector,driver))
      {
        YF.createPortfolio(sector,driver);
	Session.timeout(sleepTimeYF);
        YF.goToMyPortfolio(driver);
      }
    }

    for(String sector : sectors)
    {
      YF.goToMyPortfolio(driver);
      Session.timeout(sleepTimeYF);
      YF.goToPortfolio(sector,driver);
      Session.timeout(sleepTimeYF);
      ResultSet isins = select.c1.whereCequalsV.get(db,isinColumn,titlesTable,sectorColumnYF,sector);

      while(isins.next())
      {
        String isin = isins.getString(isinColumn);
        ResultSet symbols = select.c1.whereCequalsV.get(db,symbolColumnYF,stocksTable,isinColumn,isin);
        while(symbols.next())
        {
          String symbol = symbols.getString(symbolColumnYF);
          if(!checkSymbolExists.get(symbol,driver))
          {
            YF.addSymbol(isin,driver);
          }
        }
      }
      YF.goToMyPortfolio(driver);
    }
    Session.tearDownOnSuccess(driver);
  }
}
