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
import java.util.List;
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
    public void Test01_NewTask() throws ParserConfigurationException, SAXException, IOException
    {
        try
        {
            report_test=report.startTest(Logic.ReadConfig("Test1"),Logic.ReadConfig("Description1"));
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            WebElement NewTaskText=driver.findElement(By.className("new-todo"));
            NewTaskText.sendKeys(Logic.ReadConfig("TaskName"));
            report_test.log(LogStatus.PASS,"Task details populated successfully ");
            action.click(NewTaskText).sendKeys(Keys.ENTER).build().perform();
            report_test.log(LogStatus.PASS,"Enter Key pressed successfully ");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("li")));
            assertTrue(driver.findElement(By.tagName("li")).isDisplayed());
            Thread.sleep(5000);
            report_test.log(LogStatus.PASS,"New task created successfully "+ report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
        }
        catch (Exception e)
        {
            report_test.log(LogStatus.FAIL,"test was failed. error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Test was failed - see error on report");
        }
        catch (AssertionError e)
        {
            report_test.log(LogStatus.FAIL,"test was failed. New task not displayed on the list.  error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Assertion failed - see error in report");
        }
    }


    @Test
    public void Test02_RenameTask() throws ParserConfigurationException, SAXException, IOException
    {
        try
        {
            int NameLength=Logic.ReadConfig("TaskName").length();
            report_test = report.startTest(Logic.ReadConfig("Test2"), Logic.ReadConfig("Description2"));
            action.doubleClick(driver.findElement(By.xpath("//ul/li/div/label"))).build().perform();
            report_test.log(LogStatus.PASS, "Double click on textarea");
            action.sendKeys(Keys.HOME).build().perform();
            report_test.log(LogStatus.PASS, "Move marker to beginning of name");
            action.keyDown(Keys.LEFT_SHIFT).build().perform();
            report_test.log(LogStatus.PASS, "press and hold Left Shift");
            for (int i=0;i<NameLength;i++)
                action.sendKeys(Keys.ARROW_RIGHT).build().perform();
            action.keyUp(Keys.LEFT_SHIFT).build().perform();
            report_test.log(LogStatus.PASS, "marked all the text");
            action.sendKeys("Renamed task").build().perform();
            action.sendKeys(Keys.ENTER).build().perform();
            Thread.sleep(5000);
            report_test.log(LogStatus.PASS, "Task Renamed "+ report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
        } catch (Exception e)
        {
            report_test.log(LogStatus.FAIL, "test was failed. error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Test was failed - see error on report\n"+e.getMessage());
        }
        catch (AssertionError e)
        {
            report_test.log(LogStatus.FAIL, "test was failed. New task not displayed on the list.  error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Assertion failed - see error in report");
        }
    }

    @Test
    public void Test03_TaskCompleted() throws ParserConfigurationException, SAXException, IOException
    {
        try
        {
            report_test = report.startTest(Logic.ReadConfig("Test3"), Logic.ReadConfig("Description3"));
            WebElement task=driver.findElement(By.xpath("//input[@class='toggle']"));
            report_test.log(LogStatus.PASS, "Existing task found on the page ");
            action.click(task).build().perform();
            Thread.sleep(5000);
            assertTrue(driver.findElement(By.xpath("//li[@class='completed']")).isEnabled());
            report_test.log(LogStatus.PASS, "Task selected successfully ");
            report_test.log(LogStatus.PASS, "Task marked as done "+ report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
        }
        catch(Exception e)
        {
            report_test.log(LogStatus.FAIL,"test was failed. error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Test was failed - see error in report\n"+e.getMessage());
        }
        catch (AssertionError e)
        {
            report_test.log(LogStatus.FAIL,"test was failed. Task cannot be ticked.  error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Assertion failed - see error in report");
        }
    }

    @Test
    public void Test04_DeleteTask() throws ParserConfigurationException, SAXException, IOException
    {
        try
        {

            WebDriverWait wait=new WebDriverWait(driver,3);
            report_test = report.startTest(Logic.ReadConfig("Test4"), Logic.ReadConfig("Description4"));
            List Items=driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
            WebElement task_button = driver.findElement(By.tagName("button"));
            action.click(task_button).build().perform();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//ul/li"),Items.size()));
            report_test.log(LogStatus.PASS, "Delete button was clicked");
            report_test.log(LogStatus.PASS, "Task deleted successfully "+ report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
        } catch (Exception e)
        {
            report_test.log(LogStatus.FAIL, "test was failed. error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Test was failed - see error in report\n" + e.getMessage());
        } catch (AssertionError e)
        {
            report_test.log(LogStatus.FAIL, "test was failed. Task cannot be ticked.  error message: " + e.getMessage() + report_test.addScreenCapture(Logic.TakeScreenShot(driver)));
            fail("Assertion failed - see error in report");
        }
    }
    @AfterMethod
    public  void ReportClose()
    {
        report.endTest(report_test);
    }

    @AfterClass
    public void Exit ()
    {
        report.flush();
        report.close();
        driver.quit();
    }
}
