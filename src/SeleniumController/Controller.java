package SeleniumController;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Controller
{
  WebDriver driver;
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
         Select selection;
    }
        @Test
        public void Test01()
        {
            driver.get("http://atidcollege.co.il/Xamples/ex_controllers.html");
            driver.findElement(By.name("firstname")).sendKeys("Shay");
            driver.findElement(By.name("lastname")).sendKeys("Gazit");
            selection = new Select(driver.findElement(By.id("continents")));
            selection.selectByVisibleText("Africa");
            driver.findElement(By.id("submit")).click();
            if (driver.getCurrentUrl().contains("Shay") && driver.getCurrentUrl().contains("Gazit"))
                System.out.println("Test Passed");
            else
                System.out.println("Test failed");
        }
            @AfterClass
            public void Exit()
            {
                driver.quit();
            }
}
