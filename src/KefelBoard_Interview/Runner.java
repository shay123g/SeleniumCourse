package KefelBoard_Interview;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Runner
{

    WebDriver driver;
    support sup;
    @BeforeClass
    public void InitVariables()
    {
        sup=new support();
        System.out.println("Initializing...");
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void Test01()
    {
        driver.get("http://loudev.com");
        sup.verifyElements(driver);
    }

    @AfterClass
    public void Exit()
    {
        driver.quit();
    }
}
