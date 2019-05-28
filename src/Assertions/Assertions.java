package Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import static org.junit.Assert.assertEquals;

public class Assertions
{
    WebDriver driver;
    String Whight,Height;

    @BeforeClass
    public void InitVariables()
    {
        System.out.println("Initializing...");
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
    }

    @Test
    public void Test01FillAndCalc()
    {
        driver.get("http://atidcollege.co.il/Xamples/bmi/");
        driver.findElement(By.id("weight")).sendKeys("50");
        driver.findElement(By.id("hight")).sendKeys("30");
        driver.findElement(By.id("calculate_data")).click();
        Whight=driver.findElement(By.id("weight")).getText();
    }
    @Test
    public void Test02Assert()
    {
        assertEquals("Height is not 30","30",Height);
        assertEquals("Whight is not 50","50",Whight);
        System.out.println("H:"+Height+ "W: "+Whight);
    }
    @AfterClass
    public void Exit()
    {
        driver.quit();
    }
}
