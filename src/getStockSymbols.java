import org.openqa.selenium.WebDriver;
import java.util.*;
import java.sql.*;

import shared.Session;
import shared.MW.MW;
import shared.YF.YF;
import shared.DB.DB;
import shared.DB.select;
import shared.DB.update;
import shared.DB.insert;
import shared.Environment;
import org.openqa.selenium.Point;

public class getStockSymbols
{

  public static void main(String[] args) throws Exception
  {
    String headlessMode = "" ;
    if(args[0].length() > 0){headlessMode = args[0];}

    int sleepTime = Environment.get.sleepTime("All");
    int sleepTimeYF = Environment.get.sleepTime("YF");

    String typeColumn = Environment.get.column("Type","");
    String symbolColumn = Environment.get.column("Symbol","");
    String isinColumn = Environment.get.column("Isin","");
    String idColumn = Environment.get.column("Id","");
    String titleColumn = Environment.get.column("Title","");
    String symbolColumnSQ = Environment.get.column("Symbol","SQ");
    String symbolColumnYF = Environment.get.column("Symbol","YF");
    String symbolColumnMW = Environment.get.column("Symbol","MW");

    String stocksTable = Environment.get.table("Stocks","");
    String indicesTable = Environment.get.table("Indices","");
    String commoditiesTable = Environment.get.table("Commodities","");
    String currenciesTable = Environment.get.table("Currencies","");
    String titlesTable = Environment.get.table("Titles","");
    String candidatesTable = Environment.get.table("Candidates","");

    String baseUrlYF = Environment.get.url.base("YF");
    String baseUrlMW = Environment.get.url.base("MW");
    String urlDB = Environment.get.url.base("DB");
    String nameDB = Environment.get.name("DB");
    String usernameDB = Environment.get.username("DB");
    String passwordDB = Environment.get.password("DB");

//calling things
    WebDriver driver = Session.setUp(headlessMode);
    Session.getBaseURL(baseUrlYF);
    Session.openTab(driver);
    Session.switchToTabByNumber(1,driver);
    Session.getBaseURL(baseUrlMW);
    MW.goToSymbolSearch(baseUrlMW,driver);

    Connection db = DB.getConnection(urlDB,nameDB,usernameDB,passwordDB);
    ResultSet IdIsinType = select.c1.c2.c3.get(db,idColumn,isinColumn,typeColumn,candidatesTable);

    while(IdIsinType.next())
    {
      String id = IdIsinType.getString(idColumn);
      String ISIN = IdIsinType.getString(isinColumn);
      String type = IdIsinType.getString(typeColumn);

      if(!ISIN.contains("void"))
      {
        if(type.contains("stock"))
        {
 
          ResultSet candidatesExists = select.all.whereCequalsV.get(db,stocksTable,isinColumn,ISIN); 
 
          if(!candidatesExists.next())
          {
            Session.switchToTabByNumber(0,driver);
            String symbolYF = YF.getSymbolBySearch(ISIN,driver);
            Session.switchToTabByNumber(1,driver);
            String symbolMW = MW.getSymbol(ISIN,driver);
            insert.c1.v1.c2.v2.c3.v3.c4.v4.get(db,stocksTable,idColumn,id,isinColumn,ISIN,symbolColumnYF,symbolYF,symbolColumnMW,symbolMW);
          }
        }
      }
    }
//tear down everything
    Session.tearDownOnSuccess(driver);
  }
}
