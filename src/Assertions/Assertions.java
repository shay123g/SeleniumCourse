package Assertions;

import org.openqa.selenium.By;
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
    String BMI_res,message;

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
        BMI_res=driver.findElement(By.id("bmi_result")).getAttribute("value");
        message=driver.findElement((By.id("bmi_means"))).getAttribute("value");
    }
    @Test
    public void Test02Assert()
    {
        assertEquals("BMI result not as expected","556",BMI_res);
        assertEquals("Message is not match","That you have overweight.",message);
    }

    @Test
    public void Test03ButtonCheck()
    {
        //assertEquals();
    }

    @AfterClass
    public void Exit()
    {
       driver.quit();
    }
}
