package ErrorHandling;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.*;
import static org.testng.Assert.*;

public class Errorhandle
{
    WebDriver driver;
    List<WebElement> Elem;

    @BeforeClass
    public void Init()
    {
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        Elem=new ArrayList<>();
        driver = new ChromeDriver(options);
        driver.get("http://atidcollege.co.il/Xamples/ex_synchronization.html");
    }

    @Test
    public void Test01_CheckBoxVerification()
    {

            driver.findElement(By.id("btn")).click();
        try
        {
            sleep(10000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Sleep method fail");
            e.printStackTrace();
        }
            Elem=driver.findElements(By.id("checkbox"));
            assertTrue(Elem.isEmpty());


    }
    @AfterClass
    public void Exit()
    {
        driver.quit();
    }



}
