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

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BMIWithreport
{

        WebDriver driver;
        String BMI_res, message;
        ExtentReports report;
        ExtentTest report_test;

        @AfterMethod
        public  void ReportClose()
        {
            report.endTest(report_test);
        }
        @BeforeClass
        public void InitVariables ()
        {
            System.out.println("Initializing...");
            System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
            System.setProperty("webdriver.chrome.silentOutput", "true");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            driver = new ChromeDriver(options);
            report=new ExtentReports("C://Automation//workspace//SeleniumCourse//src//Reports//report.html");
        }


        @Test
        public void Test01FillAndCalc () throws IOException
        {
            try
            {
                report_test=report.startTest("BMI Test Report - fill details and calculate","Sample description for test");
                driver.get("http://atidcollege.co.il/Xamples/bmi/");
                report_test.log(LogStatus.PASS,"BMI page loaded successfully");
                driver.findElement(By.id("weixght")).sendKeys("50");
                report_test.log(LogStatus.PASS,"weight data entered successfully");
                driver.findElement(By.id("hight")).sendKeys("30");
                report_test.log(LogStatus.PASS,"height data entered successfully");
                driver.findElement(By.id("calculate_data")).click();
                report_test.log(LogStatus.PASS,"Button clicked successfully");
                BMI_res = driver.findElement(By.id("bmi_result")).getAttribute("value");
                message = driver.findElement((By.id("bmi_means"))).getAttribute("value");

            }
            catch (Exception e)
            {

                File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(SrcFile, new File("C://Automation//workspace//SeleniumCourse//src//Reports//screen1.jpg"));
                report_test.log(LogStatus.FAIL,"test was failed " + e+report_test.addScreenCapture("C://Automation//workspace//SeleniumCourse//src//Reports//screen1.jpg"));
                fail();
            }
        }
        @Test
        public void Test02Assert () throws IOException
        {
            try
            {
                report_test = report.startTest("BMI Test Report - Asserions", "Sample description for test");
                assertEquals("BMI result not as expected", "556", BMI_res);
                report_test.log(LogStatus.PASS, "BMI Result assertion passed successfully ");
                assertEquals("Message is not match", "That you hagve overweight.", message);
                report_test.log(LogStatus.PASS, "message to the user passed successfully");
            }
            catch (Exception e)
            {
                File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(SrcFile, new File("C://Automation//workspace//SeleniumCourse//src//Reports//screen2.jpg"));
                report_test.log(LogStatus.FAIL,"test was failed " + e+report_test.addScreenCapture("C://Automation//workspace//SeleniumCourse//src//Reports//screen2.jpg"));
                fail();
            }
        }



        @AfterClass
        public void Exit ()
        {
            report.flush();
            report.close();
            driver.quit();
        }

    }
