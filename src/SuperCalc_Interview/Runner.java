package SuperCalc_Interview;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Runner
{
    WebDriver driver;
    List<WebElement> ResultList;
    int BoardColSize,BoardRowSize;
    @BeforeClass
    public void Init() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", BussinessLogic.getData("ChromeDriverPath"));
        BoardColSize=Integer.parseInt(BussinessLogic.getData("ColValue"));
        BoardRowSize=Integer.parseInt(BussinessLogic.getData("RowValue"));
        driver = new ChromeDriver();
        driver.get(BussinessLogic.getData("URL"));
    }
    @Test
    public void Test01_Multiply()
    {
        WebDriverWait wait=new WebDriverWait(driver,5);
        Select comboBoxSelection=new Select(driver.findElement(By.xpath("//select")));
        WebElement firstNumber=driver.findElement(By.xpath("//input[@ng-model='first']"));
        WebElement SecondNumber=driver.findElement(By.xpath("//input[@ng-model='second']"));
        comboBoxSelection.selectByIndex(3);
        WebElement button=driver.findElement(By.id("gobutton"));
        int count=0;
        for (int i=1;i<=BoardColSize;i++)
            for (int j = 1; j <= BoardRowSize; j++)
            {
                firstNumber.sendKeys(Integer.toString(i));
                SecondNumber.sendKeys(Integer.toString(j));
                button.click();
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tbody/tr"), count));
                count++;
            }
    ResultList=driver.findElements(By.xpath(("//tbody/tr/td[3]")));
    BussinessLogic.Print(ResultList);
    }
    @AfterClass
    public void Finish()
    {
        driver.quit();
    }

}

