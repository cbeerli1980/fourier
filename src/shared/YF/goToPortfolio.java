package shared.YF;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.*;

public class goToPortfolio
{
  public static Boolean get(String portfolioName,WebDriver driver)
  {
    int j=1,k=1;
    String portfolioNameFromYF="";
    List<WebElement> portfolioTable = driver.findElements(By.xpath(".//*[@data-test='pf-list-table']/tbody/tr"));
    int portfolioTableRowCount = portfolioTable.size();

    for(int i=0;i<portfolioTableRowCount;i=i+2)
    {
      if(portfolioNameFromYF==portfolioName)
      {
	try
	{
          portfolioTable.get(i).click();
	  k=0;
        }
	catch(Exception e)
        {
          System.out.println("goToPortfolio(): failed "+portfolioName);
	  k=1;
        }
      }
    }
    if(k==0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
