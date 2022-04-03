package shared;

public class Environment
{
  public static class get
  {

    public static String flag = "";
    public static String vendor = "";

    public static int sleepTime(String vendor){return Integer.parseInt(System.getenv("sleepTime"+vendor));}
    public static String username(String vendor){return System.getenv("username"+vendor);}
    public static String password(String vendor){return System.getenv("password"+vendor);}

    public static class url
    {
      public static String base(String vendor){return System.getenv("urlBase"+vendor);}
      public static String login(String vendor){return System.getenv("urlLogin"+vendor);}
      public static String title(String vendor){return System.getenv("urlTitle"+vendor);}
    }

    public static String name(String name){return System.getenv("name"+name);}
    public static String table(String tableName,String vendor){return System.getenv("table"+tableName+vendor);}
    public static String column(String columnName,String vendor){return System.getenv("column"+columnName+vendor);}
    public static String list(String flag){return System.getenv("list"+flag);}

  }
}
//
//  //YF
//  public static String getUsernameYF(){return System.getenv("USERNAME_YF");}
//  public static String getPasswordYF(){return System.getenv("PASSWORD_YF");}
//  public static String getBaseUrlYF(){return System.getenv("BASE_URL_YF");}
//  public static String getLoginUrlYF(){return System.getenv("LOGIN_URL_YF");}
//  public static String getSymbolColumnYF(){return System.getenv("SYMBOL_COLUMN_YF");}
//  public static String getSectorColumnYF(){return System.getenv("SECTOR_COLUMN_YF");}
//  public static String getIndustryColumnYF(){return System.getenv("INDUSTRY_COLUMN_YF");}
//  public static String getTitleColumnYF(){return System.getenv("TITLE_COLUMN_YF");}
//  public static String getExchangeColumnYF(){return System.getenv("EXCHANGE_COLUMN_YF");}
//  public static String getClassificationsTableYF(){return System.getenv("CLASSIFICATIONS_TABLE_YF");}
//  //SQ
//  public static int getSleepTimeSQ(){return Integer.parseInt(System.getenv("SLEEP_TIME_SQ"));}
//  public static String getUsernameSQ(){return System.getenv("USERNAME_SQ");}
//  public static String getPasswordSQ(){return System.getenv("PASSWORD_SQ");}
//  public static String getBaseUrlSQ(){return System.getenv("BASE_URL_SQ");}
//  public static String getSymbolColumnSQ(){return System.getenv("SYMBOL_COLUMN_SQ");}
//  public static String getTitleColumnSQ(){return System.getenv("TITLE_COLUMN_SQ");}
//  //MW
//  public static int getSleepTimeMW(){return Integer.parseInt(System.getenv("SLEEP_TIME_MW"));}
//  public static String getBaseUrlMW(){return System.getenv("BASE_URL_MW");}
//  public static String getBrowserProfilePathMW(){return System.getenv("BROWSER_PROFILE_PATH_MW");}
//  public static String getSymbolColumnMW(){return System.getenv("SYMBOL_COLUMN_MW");}
//  public static String getSectorColumnMW(){return System.getenv("SECTOR_COLUMN_MW");}
//  public static String getIndustryColumnMW(){return System.getenv("INDUSTRY_COLUMN_MW");}
//  public static String getTitleColumnMW(){return System.getenv("TITLE_COLUMN_MW");}
//  public static String getClassificationsTableMW(){return System.getenv("CLASSIFICATIONS_TABLE_MW");}
//  //SD
//  public static int getSleepTimeSD(){return Integer.parseInt(System.getenv("SLEEP_TIME_SD"));}
//  public static String getSymbolColumnSD(){return System.getenv("SYMBOL_COLUMN_SD");}
//  public static String getTitleColumnSD(){return System.getenv("TITLE_COLUMN_SD");}
//  //General
//  public static int getRetryCount(){return Integer.parseInt(System.getenv("RETRY_COUNT"));}
//  public static int getSleepTime(){return Integer.parseInt(System.getenv("SLEEP_TIME"));}
//
//  public static String getSymbolColumn(){return System.getenv("SYMBOL_COLUMN");}
//  public static String getSectorColumn(){return System.getenv("SECTOR_COLUMN");}
//  public static String getIndustryColumn(){return System.getenv("INDUSTRY_COLUMN");}
//  public static String getTitleColumn(){return System.getenv("TITLE_COLUMN");}
//  public static String getIsinColumn(){return System.getenv("ISIN_COLUMN");}
//  public static String getExchangeColumn(){return System.getenv("EXCHANGE_COLUMN");}
//  public static String getTypeColumn(){return System.getenv("TYPE_COLUMN");}
//  public static String getIdColumn(){return System.getenv("ID_COLUMN");}
//  public static String getCandidatesoutliersTable(){return System.getenv("CANDIDATESOUTLIERS_TABLE");}
//  public static String getCommoditiesTable(){return System.getenv("COMMODITIES_TABLE");}
//  public static String getCurrenciesTable(){return System.getenv("CURRENCIES_TABLE");}
//  public static String getIndicesTable(){return System.getenv("INDICES_TABLE");}
//  public static String getStocksTable(){return System.getenv("STOCKS_TABLE");}
//  public static String getTitlesTable(){return System.getenv("TITLES_TABLE");}
//  public static String getCurrentAssetsTable(){return System.getenv("CURRENT_ASSETS_TABLE");}
//  public static String getCandidatesTable(){return System.getenv("CANDIDATES_TABLE");}
//  public static String getCurrentAssetsTableProperties(){return System.getenv("CURRENT_ASSETS_TABLE_PROPERTIES");}
//
//  //DB
//  public static String getUrlDB(){return System.getenv("URL_DB");}
//  public static String getNameDB(){return System.getenv("NAME_DB");}
//  public static String getUsernameDB(){return System.getenv("USERNAME_DB");}
//  public static String getPasswordDB(){return System.getenv("PASSWORD_DB");}
//
//  public static String getCommoditiesList(){return System.getenv("COMMODITIES_LIST");}
//  public static String getIndicesList(){return System.getenv("INDICES_LIST");}
//  public static String getCurrenciesList(){return System.getenv("CURRENCIES_LIST");}
//  public static String getBasketList(){return System.getenv("BASKET_LIST");}
//}
