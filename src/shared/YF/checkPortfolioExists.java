package shared.YF;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.*;

public class checkPortfolioExists
{
  public static Boolean get(String portfolioName,WebDriver driver)
  {
    int k=1;
    List<WebElement> portfolioTable = driver.findElements(By.xpath(".//*[@data-test='pf-list-table']/tbody/tr"));
    int portfolioTableRowCount = portfolioTable.size();

    for(int i=0;i<portfolioTableRowCount;i=i+2)
    {
      WebElement portfolioTableRow = portfolioTable.get(i);
      List<WebElement> portfolioTableRowElements = portfolioTableRow.findElements(By.xpath(".//a"));
      int portfolioTableRowElementsCount = portfolioTableRowElements.size();
      for(int j=0;j<portfolioTableRowElementsCount;j++)
      {
        String portfolioTableRowElement = portfolioTableRowElements.get(0).getText();
        if(portfolioTableRowElement.contains(portfolioName))
        {
          k=0;
        }
      }
    }

    if(k==0)
    {
      System.out.println("checkPortfolioExists(): exists "+portfolioName);
      return true;
    }
    else
    {
      System.out.println("checkPortfolioExists(): missing "+portfolioName);
      return false;
    }
  }
}
