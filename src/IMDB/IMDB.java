package IMDB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IMDB
{
    String URL,ExpectedTitle,Title;
    WebDriver driver;

    @BeforeClass
    public void InitVariables()
    {
        System.out.println("Initializing...");
        URL="http://imdb.com";
        ExpectedTitle="IMDB";
        System.setProperty("webdriver.chrome.driver","C://Automation//libs//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
    }
    @Test
    public void Test01Navigate()
    {
        driver.navigate();
    }
    @Test
    public void Test02Refresh()
    {
        driver.navigate().refresh();
    }
    @Test
    public void Test03TitleCheck()
    {
        Title=driver.getTitle();
        if (Title.equals(ExpectedTitle))
            System.out.println("Title Match");
        else
            System.out.println("Title No Match");
    }
    @Test
    public void Test04URLCheck()
    {
        String ActualURL=driver.getCurrentUrl();
        if (ActualURL.equals(URL))
            System.out.println("URL Match");
        else
            System.out.println("URL No Match");
    }
    @AfterClass
    public void Test05Finish()
    {
        System.out.println("Closing...");
        driver.quit();
    }
}
