import org.openqa.selenium.WebDriver;
import java.util.*;
import java.sql.*;
import java.sql.Connection;

import shared.Session;
import shared.Environment;
import shared.SQ.SQlogin;
import shared.SQ.SQframes;
import shared.SQ.SQ;
import shared.DB.DB;
import shared.DB.select;
import shared.DB.insert;

public class parseSD
{
// members
  public static String headlessMode;
  public static WebDriver driver;
  public static Connection db;
  static ArrayList<String> SPL;

  public static void main(String[] args) throws Exception
  {
    if(args[0].length() > 0){headlessMode = args[0];}

    int sleepTime = Environment.get.sleepTime("All");
    int sleepTimeSD = Environment.get.sleepTime("SD");

    String candidatesoutliersTable = Environment.get.table("CandidatesOutliers","");
    String symbolSQcolumn = Environment.get.column("Symbol","SQ");
    String symbolColumn = Environment.get.column("Symbol","");
    String isinColumn = Environment.get.column("Isin","");
    String typeColumn = Environment.get.column("Type","");
    String titleColumnSQ = Environment.get.column("Title","SQ");
    String baseUrlSQ = Environment.get.url.base("SQ");
    String userNameSQ = Environment.get.username("SQ");
    String passwordSQ = Environment.get.password("SQ");

    String urlDB = Environment.get.url.base("DB");
    String nameDB = Environment.get.name("DB");
    String usernameDB = Environment.get.username("DB");
    String passwordDB = Environment.get.password("DB");
    String candidatesTable = Environment.get.table("Candidates","");

//calling things
    driver = Session.setUp(headlessMode);
    Session.getBaseURL(baseUrlSQ);
    Session.openTab(driver);
    Session.switchToTabByNumber(0,driver);
    SQframes.setSQhnav(driver);
    SQlogin.doSQlogin(userNameSQ,passwordSQ,driver);
    SQ.goToSDall(driver);
    Session.timeout(sleepTime);
    Session.switchToTabByNumber(1,driver);
    Session.getBaseURL(baseUrlSQ);
    SQ.goToSDall(driver);
    Session.switchToTabByNumber(0,driver);

    db = DB.getConnection(urlDB,nameDB,usernameDB,passwordDB);

    for(int i=1;i<4000;i++)
    {
      System.out.println("page: " + i);
      SQframes.setSQsqmain(driver);
      SPL = SQ.getUniquePLfromSDproductTable(sleepTimeSD,driver);

      for(String title : SPL)
      {
	String type = SQ.getType(title);
	String titleTmp = title;
	String ISIN = "void";
	String symbol = "void";
        title = SQ.removeSpecialCharacters(title);
        select.c1.tou().tou1();
        ResultSet rsTitles = select.c1.where.c.equals.v(db,titleColumnSQ,candidatesTable,titleColumnSQ,title);
        ResultSet rsoutlierTitles = select.c1.where.c.equals.v(db,titleColumnSQ,candidatesoutliersTable,titleColumnSQ,title);
        if(!rsTitles.next() && !rsoutlierTitles.next())
        {
          if(type=="stock")
          {
            Session.timeout(sleepTime);
            Session.switchToTabByNumber(1,driver);
            Session.timeout(sleepTime);
            titleTmp = SQ.formatTitle(title);
            ISIN = SQ.getISIN(titleTmp,driver);
	    if(ISIN.contains("void"))
	    {
              insert.c1.v1.c2.v2.c3.v3.get(db,candidatesoutliersTable,titleColumnSQ,title,isinColumn,ISIN,typeColumn,type);
	    }
	    else
	    {
              insert.c1.v1.c2.v2.c3.v3.get(db,candidatesTable,titleColumnSQ,title,isinColumn,ISIN,typeColumn,type);
	    }
          }
	  else
	  {
            insert.c1.v1.c2.v2.c3.v3.get(db,candidatesTable,titleColumnSQ,title,isinColumn,ISIN,typeColumn,type);
	  }
	}
      }

      Session.switchToTabByNumber(0,driver);
      SQframes.setSQsqmain(driver);
      SQ.clickNextSDproductTable(driver);
      Session.timeout(sleepTimeSD);
    }

    Session.tearDownOnSuccess(driver);
  }
}
