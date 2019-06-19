package Reports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BMIWithreport
{

        WebDriver driver;
        String BMI_res, message;
        ExtentReports report;
        ExtentTest report_test;


        @BeforeClass
        public void InitVariables () throws IOException, SAXException, ParserConfigurationException
        {
            System.setProperty("webdriver.chrome.driver", LogicClass.getData("ChromeDriverPath"));
            System.setProperty("webdriver.chrome.silentOutput", LogicClass.getData("ChromeSilent"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments(LogicClass.getData("Options"));
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            report=new ExtentReports(LogicClass.getData("ReportPath")+LogicClass.getData("ReportFile"));
        }

    @AfterMethod
    public  void ReportClose()
    {
        report.endTest(report_test);
    }
        @Test
        public void Test01FillAndCalc () throws IOException, ParserConfigurationException, SAXException
        {
            try
            {
                report_test=report.startTest(LogicClass.getData("Name1"),LogicClass.getData("Description"));
                driver.get(LogicClass.getData("Url"));
                report_test.log(LogStatus.PASS,"BMI page loaded successfully");
                driver.findElement(By.id("weight")).sendKeys(LogicClass.getData("Whight"));
                report_test.log(LogStatus.PASS,"weight data entered successfully");
                driver.findElement(By.id("hight")).sendKeys(LogicClass.getData("Height"));
                report_test.log(LogStatus.PASS,"height data entered successfully");
                driver.findElement(By.id("calculate_data")).click();
                report_test.log(LogStatus.PASS,"Button clicked successfully");
                BMI_res = driver.findElement(By.id("bmi_result")).getAttribute("value");
                message = driver.findElement((By.id("bmi_means"))).getAttribute("value");
                assertEquals("BMI result not as expected", "200", BMI_res);
                assertEquals("Message is not match", "That you have overweight.", message);

            }
            catch (NoSuchElementException e)
            {
                report_test.log(LogStatus.FAIL,"test was failed. Element not found on the page" + e.getMessage() + report_test.addScreenCapture(TakeScreenShot()));
                fail("Test failed, See error in report");
            }
            catch(AssertionError e)
            {
                report_test.log(LogStatus.FAIL,"test Assertion failed " +e.getMessage()+ report_test.addScreenCapture(TakeScreenShot()));
                fail("Test failed, See error in report");
            }

        }

        @AfterClass
        public void Exit ()
        {
            report.flush();
            report.close();
            driver.quit();
        }

        public String TakeScreenShot() throws IOException, ParserConfigurationException, SAXException
        {
            String filename=new SimpleDateFormat(("yyyyMMddHHmm'.jpg'")).format(new Date());
            String path=LogicClass.getData("ReportPath")+filename;
            File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(SrcFile, new File(path));
            return path;
        }

    }
