package Interview_TodoList;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class todos
{

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports report;
    ExtentTest report_test;
    Actions action;



    @BeforeClass
    public void GoToURL() throws IOException, SAXException, ParserConfigurationException
    {
        System.setProperty("webdriver.chrome.driver", Logic.ReadConfig("ChromeDriverPath"));
        System.setProperty("webdriver.chrome.silentOutput", Logic.ReadConfig("ChromeSilent"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Logic.ReadConfig("Options"));
        driver = new ChromeDriver(options);
        driver.get(Logic.ReadConfig("Url"));
        driver.manage().window().maximize();
        report=new ExtentReports(Logic.ReadConfig("ReportPath")+Logic.ReadConfig("ReportFile"));
        action=new Actions(driver);
        wait=new WebDriverWait(driver,2);
    }

    @Test
    public void Test01_NewTask() throws IOException, SAXException, ParserConfigurationException
    {
        try
        {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            WebElement NewTaskText=driver.findElement(By.className("new-todo"));
            report_test=report.startTest(Logic.ReadConfig("Test1"),Logic.ReadConfig("Description1"));
            NewTaskText.sendKeys(Logic.ReadConfig("TaskName"));
            report_test.log(LogStatus.PASS,"Task details populated successfully ");
            action.click(NewTaskText).sendKeys(Keys.ENTER).build().perform();
            report_test.log(LogStatus.PASS,"Enter Key pressed successfully ");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("li")));
            assertTrue(driver.findElement(By.tagName("li")).isDisplayed());
        }
        catch (Exception e)
        {
            report_test.log(LogStatus.FAIL,"test was failed. error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Test was failed - see error on report");
        }
        catch (AssertionError e)
        {
            report_test.log(LogStatus.FAIL,"test was failed. error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Assertion failed - see error in report");
        }
    }
    @AfterMethod
    public  void ReportClose()
    {
        report.endTest(report_test);
    }

    @AfterClass
    public void Exit () throws InterruptedException
    {
        Thread.sleep(10000);
        report.flush();
        report.close();
        driver.quit();
    }
}
