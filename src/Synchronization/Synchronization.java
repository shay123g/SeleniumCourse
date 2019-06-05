package Synchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class Synchronization
{
    WebDriver driver;

    @BeforeClass
    public void Init()
    {
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.navigate().to("http://atidcollege.co.il/Xamples/ex_synchronization.html");
    }
    @Test
    public void Test01_ImplicityWailt()
    {
        driver.findElement(By.id("rendered")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.xpath("//div[@id='finish2']/h4")).isDisplayed(),"tag <h4> not exist after clicking the button");
    }
    @Test
    public void Test02_Sleep()
    {
        driver.findElement(By.id("hidden")).click();
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            System.out.println("cannot perform Sleep command");
        }
        assertTrue(driver.findElement(By.id("loading1")).isDisplayed(), "'Loading...' is not present on page");
    }
        @Test
        public void Test03_ExplicitWailt()
        {
            WebDriverWait wait=new WebDriverWait(driver,3);
            driver.navigate().to("http://atidcollege.co.il/Xamples/ex_synchronization.html");
            driver.findElement(By.id("btn")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
            assertTrue(driver.findElement(By.id("message")).isDisplayed(),"'Its Gone' not exist after clicking the button");
        }

        @AfterClass
    public void Exit()
        {
            driver.quit();
        }
}


