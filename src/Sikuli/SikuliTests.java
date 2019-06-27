package Sikuli;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import static org.testng.Assert.assertNotNull;


public class SikuliTests
{
    WebDriver driver;
    SikuliInfra Do=new SikuliInfra();
    double timeout=10.0;
    String path="C:/Automation/workspace/SeleniumCourse/src/Sikuli/";

    @BeforeClass
    public void InitVariables()
    {
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver();
        driver.get("https://www.youtube.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void Test01_SearchIdanAmedi() throws FindFailed
    {

        Do.SearchClip(path+"searchfield.PNG",path+"searchButton.PNG",timeout,"Idan Amedi");
        assertNotNull(Do.VerifyElementExist(path+"MixIdanAmediExist.PNG",timeout));
    }
    @Test
    public void Test02_HoverOn() throws FindFailed
    {
    Do.HoveronClip(path+"HoverOnCLIP.PNG",timeout);
    }



    @AfterClass
    public void Exit()
    {
        driver.quit();
    }
}


