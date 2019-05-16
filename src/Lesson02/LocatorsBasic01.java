package Lesson02;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorsBasic01
{
    WebDriver driver;
    @BeforeClass
    public void InitVariables()
    {
        System.out.println("Initializing...");

        System.setProperty("webdriver.chrome.driver","C://AutomationDrivers//chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver","C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
    }
    @Test
    public void IdentifyElememt()
    {
        driver.get("http://www.seleniumhq.org");
        System.out.println("Element By ID: "+driver.findElement(By.id("menu_about")).getText());
        System.out.println("Element By Partial Link text: "+driver.findElement(By.partialLinkText("About")).getText());

        //driver.quit();

    }

}
