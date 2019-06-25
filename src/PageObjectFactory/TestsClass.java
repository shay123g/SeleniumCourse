package PageObjectFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


public class TestsClass
{

    WebDriver driver;
    LoginPage Login;
    FormPage Form;
    @BeforeClass
    public void InitVariables()
    {
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver();
        driver.get("http://atidcollege.co.il/Xamples/webdriveradvance.html");
        Login=PageFactory.initElements(driver, LoginPage.class);
        Form=PageFactory.initElements(driver,FormPage.class);
    }
    @Test
    public void Test01_DoAll()
    {
        Login.DoLogin("selenium","webdriver");
        Form.FillForm("Automation Engineer","35","Ramat-Gan");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td/a/button")).isDisplayed());
    }

    @AfterClass
    public void Exit()
    {
        driver.quit();
    }
}
