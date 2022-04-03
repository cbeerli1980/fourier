import java.util.*;
import java.sql.*;

import shared.DB.DB;
import shared.DB.select;
import shared.DB.update;
import shared.DB.insert;
import shared.DB.truncate;
import shared.Environment;
import shared.Session;

public class createClassifications
{
// members
  public static String sectorColumnYF,sectorColumnMW,industryColumnYF,industryColumnMW,sectorColumn,industryColumn;
  public static String titlesTable,classificationsTableYF,classificationsTableMW;
  public static String result;
  public static ArrayList<String> industryYFlist,industryMWlist;
  public static Connection db;

  public static String sectorYF,sectorMW,industryYF,industryMW;
 
  public static void main(String[] args) throws Exception
  {
    sectorColumn = Environment.getSectorColumn();
    sectorColumnYF = Environment.getSectorColumnYF();
    sectorColumnMW = Environment.getSectorColumnMW();
    industryColumn = Environment.getIndustryColumn();
    industryColumnYF = Environment.getIndustryColumnYF();
    industryColumnMW = Environment.getIndustryColumnMW();
    classificationsTableYF = Environment.getClassificationsTableYF();
    classificationsTableMW = Environment.getClassificationsTableMW();
    titlesTable = Environment.getTitlesTable();

    String urlDB = Environment.getUrlDB();
    String nameDB = Environment.getNameDB();
    String usernameDB = Environment.getUsernameDB();
    String passwordDB = Environment.getPasswordDB();

    db = DB.getConnection(urlDB,nameDB,usernameDB,passwordDB);

    ResultSet industryYFresultSet = select.c1.whereCequalsNotV.get(db,industryColumnYF,titlesTable,industryColumnYF,"NULL");
    ResultSet industryMWresultSet = select.c1.whereCequalsNotV.get(db,industryColumnMW,titlesTable,industryColumnMW,"NULL");

    industryYFlist = new ArrayList<String>();
    industryMWlist = new ArrayList<String>();

    truncate.table.get(db,classificationsTableYF);
    truncate.table.get(db,classificationsTableMW);

    while(industryMWresultSet.next())
    {
      industryMW = industryMWresultSet.getString(industryColumnMW);
      ResultSet resultResultSet = select.c1.whereCequalsV.get(db,industryColumnMW,titlesTable,industryColumnMW,industryMW);

      while(resultResultSet.next())
      {
        result = resultResultSet.getString(industryColumnMW);
      }

      if(!industryMWlist.contains(industryMW))
      {
        industryMWlist.add(industryMW);
      }
    }
 
    for(String industryMW : industryMWlist)
    {
      ResultSet industryAndSectorMWresultSet = select.c1.c2.whereCequalsV.get(db,sectorColumnMW,industryColumnMW,titlesTable,industryColumnMW,industryMW);

      while(industryAndSectorMWresultSet.next())
      {
        industryMW = industryAndSectorMWresultSet.getString(industryColumnMW);
        sectorMW = industryAndSectorMWresultSet.getString(sectorColumnMW);

        if(!industryMW.contains("void"))
        {
          insert.c1.v1.c2.v2.get(db,classificationsTableMW,sectorColumn,sectorMW,industryColumn,industryMW);
          break;
        }
      }
    }

    while(industryYFresultSet.next())
    {
      industryYF = industryYFresultSet.getString(industryColumnYF);
      ResultSet resultResultSet = select.c1.whereCequalsV.get(db,industryColumnYF,titlesTable,industryColumnYF,industryYF);

      while(resultResultSet.next())
      {
        result = resultResultSet.getString(industryColumnYF);
      }

      if(!industryYFlist.contains(industryYF))
      {
	industryYFlist.add(industryYF);
      }
    }

    for(String industryYF : industryYFlist)
    {
      ResultSet industryAndSectorYFresultSet = select.c1.c2.whereCequalsV.get(db,sectorColumnYF,industryColumnYF,titlesTable,industryColumnYF,industryYF);

      while(industryAndSectorYFresultSet.next())
      {
        industryYF = industryAndSectorYFresultSet.getString(industryColumnYF);
        sectorYF = industryAndSectorYFresultSet.getString(sectorColumnYF);
	if(!industryYF.contains("void") && !sectorYF.contains("void"))
        {
          insert.c1.v1.c2.v2.get(db,classificationsTableYF,sectorColumn,sectorYF,industryColumn,industryYF);
          break;
        }
      }
    }
  }
}
