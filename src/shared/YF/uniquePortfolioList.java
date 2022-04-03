package shared.YF;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.*;
import java.sql.*;

public class uniquePortfolioList
{
  public static List<String> get(ResultSet rs,String column,WebDriver driver)
  {
    ArrayList<String> elements = new ArrayList<String>();
    try
    {
      while(rs.next())
      {
        String element = rs.getString(column);
        if(!elements.contains(element))
        {
          elements.add(element);
        }
      }
    }
    catch(Exception e)
    {
      System.out.println("uniquePortfolioList(): failed "+rs);
    }
    return elements;
  }
}
