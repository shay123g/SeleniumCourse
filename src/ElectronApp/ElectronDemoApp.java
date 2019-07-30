package ElectronApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ElectronDemoApp
{
    public WebDriver driver;
    public DesiredCapabilities capabilities;
    public ChromeOptions options;


    @BeforeClass
    public void Init()
    {
        System.setProperty("webdriver.chrome.driver","C:/Automation/libs/electrondriver.exe");
        options=new ChromeOptions();
        options.setBinary("C:/Automation/Electron/Electron API Demos-win32-ia32/Electron API Demos.exe");
        capabilities=new DesiredCapabilities();
        capabilities.setCapability("chromeOptions",options);
        capabilities.setBrowserName("chrome");
        driver=new ChromeDriver(capabilities);
    }

    @Test
    public void test01_NewWindow()
    {
        try
        {
            driver.findElement(By.id("button-windows")).click();
            driver.findElement(By.id("new-window")).click();
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public void finish()
    {
        driver.quit();
    }
}
