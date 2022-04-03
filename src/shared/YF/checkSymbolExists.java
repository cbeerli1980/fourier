package shared.YF;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.*;

public class checkSymbolExists
{
  public static Boolean get(String symbol,WebDriver driver)
  {
    int k=1;
    List<WebElement> symbolTable = driver.findElements(By.xpath(".//*[@id='pf-detail-table']/div/table/tbody/tr"));
    int symbolTableRowCount = symbolTable.size();

    for(int i=0;i<symbolTableRowCount;i++)
    {
      WebElement symbolTableRow = symbolTable.get(i);
      List<WebElement> symbolTableRowElements = symbolTableRow.findElements(By.xpath(".//a"));
      int symbolTableRowElementsCount = symbolTableRowElements.size();
      for(int j=0;j<symbolTableRowElementsCount;j++)
      {
        String symbolTableRowElement = symbolTableRowElements.get(0).getText();
//        System.out.println("checksymbolExists(): "+symbol+" "+symbolTableRowElement);
        if(symbolTableRowElement.contains(symbol))
        {
          k=0;
        }
      }
    }

    if(k==0)
    {
      System.out.println("checkSymbolExists(): exists "+symbol);
      return true;
    }
    else
    {
      System.out.println("checkSymbolExists(): missing "+symbol);
      return false;
    }
  }
}
