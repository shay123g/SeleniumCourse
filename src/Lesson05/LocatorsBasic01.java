package Lesson05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class LocatorsBasic01
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
    public void IdentifyElememt()
    {
        int SeleniumCount=0,seleniumCount=0;
        driver.get("http://www.seleniumhq.org");
        List<WebElement> allATags=new ArrayList<>(driver.findElements(By.tagName("a")));
        System.out.println("Element By ID: "+driver.findElement(By.id("menu_about")).getText());
        System.out.println("Element By Link Text: "+driver.findElement(By.linkText("About")).getText());
        System.out.println("Element By Partial Link text: "+driver.findElement(By.partialLinkText("Abo")).getText());
        System.out.println("Total number of links on page: "+allATags.size());
        for (WebElement elem : allATags)
        {
            if (elem.getText().contains("Selenium"))
                SeleniumCount++;
            else
                if (elem.getText().contains("selenium"))
                    seleniumCount++;
        }
        System.out.println("Total number of links with Selenium: "+SeleniumCount);
        System.out.println("Total number of links with selenium: "+seleniumCount);
    }

    @AfterClass
    public void Exit()
    {
        driver.quit();
    }
}
