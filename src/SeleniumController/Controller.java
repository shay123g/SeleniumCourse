package SeleniumController;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Controller
{
  WebDriver driver;
  WebElement Picker;
  List<WebElement> cells;
  Select selection;
    @BeforeClass
    public void InitVariables()
    {
        System.out.println("Initializing...");
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        cells=new ArrayList();


    }
        @Test
        public void Test01FillAndSend()
        {
            driver.get("http://atidcollege.co.il/Xamples/ex_controllers.html");
            driver.findElement(By.name("firstname")).sendKeys("Shay");
            driver.findElement(By.name("lastname")).sendKeys("Gazit");
            selection = new Select(driver.findElement(By.id("continents")));
            selection.selectByVisibleText("Africa");
            driver.findElement(By.id("sex-0")).click();
            driver.findElement(By.id("exp-3")).click();
            driver.findElement(By.id("profession-0")).click();
            driver.findElement(By.id("tool-1")).click();
            selection = new Select(driver.findElement(By.xpath("//select[@id='selenium_commands']")));
            selection.selectByIndex(1);
            driver.findElement(By.name("photo")).sendKeys("C://pic.bmp");
            driver.findElement(By.id("datepicker")).click();
            Picker = driver.findElement(By.id("ui-datepicker-div"));
            cells = Picker.findElements(By.tagName("td"));
            for (WebElement cell : cells)
                if (cell.getText().equals("14"))
                    cell.click();
            driver.findElement(By.id("submit")).click();
            System.out.println(driver.getCurrentUrl());
            if (driver.getCurrentUrl().contains("Shay") && driver.getCurrentUrl().contains("Gazit"))
                System.out.println("Test Passed");
            else
                System.out.println("Test failed");
        }
            @AfterClass
            public void Test02Exit()
            {
                driver.quit();
            }
}
