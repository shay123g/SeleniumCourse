package LocatorBasics02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LocatorBasic02
{
    private WebDriver driver;
    private ArrayList<WebElement> Elements;

    @BeforeClass
    public void InitVariables()
    {
        System.out.println("Initializing...");
        System.setProperty("webdriver.chrome.driver","C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        Elements=new ArrayList<>();
    }

    @Test
    public void WikipediaIdentify()
    {
        driver.get("https://www.wikipedia.org/");
        Elements.add(driver.findElement(By.id("searchInput")));
        Elements.add(driver.findElement(By.xpath("//select[@name='language']")));
        Elements.add(driver.findElement(By.cssSelector("div[class='footer-sidebar-text jsl10n']")));
        Elements.add(driver.findElement(By.linkText("Wikimedia Foundation")));
        Elements.add(driver.findElement(By.xpath("//img[starts-with(@class,'central-featured')]")));
    }
    @Test
    public void Print()
    {
        for (int i=Elements.size()-1;i>=0;i--)
        {
            System.out.println("WebElement in position "+i+":"+Elements.get(i).toString());
        }
    }

    @AfterClass
    public void Exit()
    {
        driver.quit();
    }
}
