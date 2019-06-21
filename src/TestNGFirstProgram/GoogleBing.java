package TestNGFirstProgram;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleBing
{
    private WebDriver driver;

    @BeforeClass
    public void InitVariables()
    {
        System.out.println("Initializing...");
        System.setProperty("webdriver.chrome.driver","C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
    }
    @Test
    public void Test01Navigate2Google()
    {
        driver.get("https://www.google.com");
        driver.navigate();
    }
    @Test
    public void Test02Navigate2Bing()
    {
        driver.get("https://www.bing.com");
        driver.navigate();
    }
    @Test
    public void Test03GoBack()
    {
        driver.navigate().back();
    }
    @Test
    public void Test04PrintTitle()
    {
        System.out.println(driver.getTitle());
    }
    @Test
    public void Test05Exist()
    {
        driver.get("https://www.google.com");
        if (driver.getPageSource().contains("bubble"))
            System.out.println("exists in Google");
        else
            System.out.println("Does not exist in Google");
        driver.get("https://www.bing.com");
        if (driver.getPageSource().contains("bubble"))
            System.out.println("exists in Bing");
        else
            System.out.println("Does not exist in Bing");
    }

    @AfterClass
    public void Test05Finish()
    {
        System.out.println("Closing...");
        driver.quit();
    }
}
