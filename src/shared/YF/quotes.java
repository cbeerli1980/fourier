package shared.YF;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.*;

public class quotes
{
  public static List<String> price(WebDriver driver)
  {
    String symbolTableRowElement = "";
    List<WebElement> symbolTable = driver.findElements(By.xpath(".//*[@id='pf-detail-table']/div/table/tbody/tr"));
    ArrayList<String> quotes = new ArrayList<String>();
    int symbolTableRowCount = symbolTable.size();

    for(int i=0;i<symbolTableRowCount;i++)
    {
      WebElement symbolTableRow = symbolTable.get(i);
      List<WebElement> symbolTableRowElements = symbolTableRow.findElements(By.xpath(".//a"));
      int symbolTableRowElementsCount = symbolTableRowElements.size();
      for(int j=0;j<symbolTableRowElementsCount;j++)
      {
        symbolTableRowElement = symbolTableRowElements.get(0).getText();
	System.out.println(symbolTableRowElement);
	quotes.add(symbolTableRowElement);
      }
    }
    return quotes;
  }
}
