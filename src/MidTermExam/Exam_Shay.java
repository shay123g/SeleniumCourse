package MidTermExam;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class Exam_Shay
{
   private WebDriver driver;
   private String FullName,First,Last;
   private Alert popup;

   @BeforeClass
   /**
    * Init() method will initialize most of the variables and open browser to the desired site.
    * Also an implicitly wait was defined for 2 seconds in case the elements on the page not loaded fully
     */
   public void Init()
    {
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver=new ChromeDriver();
        driver.navigate().to("http://atidcollege.co.il/Xamples/pizza/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        First="Shay";
        Last="Gazit";
    }

    @Test
    /**
     * the Test01_startingPriceValidate() is the first test case. its functionality is to validate,using Assert, the price 7.5$ is
     * the initial price  on the page.in case its not, a proper message will displayed
     */
    public void Test01_startingPriceValidate()
    {
        assertEquals(driver.findElement(By.xpath("//div[@class='ginput_container']/span")).getText(),"$7.50","Starting price is not 7.50$ as expected");
    }
    @Test
    /**
     * the Test02_fillForm_ValidateNewPrice() is the second test case. it has several functions:
     * A. insert first and last named to the correct fields in the page
     * B. select the Delivery option from combo box
     * C. read and store the first and last name from the text fields to "Full Name" String variable for future use
     * D. validate, using Assert, that the new price is 10.50$ , and in case not, display message
     */
    public void Test02_fillForm_ValidateNewPrice()
    {
        Select comboBox = new Select(driver.findElement(By.id("input_5_21")));
        driver.findElement(By.id("input_5_22_3")).sendKeys(First);
        driver.findElement(By.id("input_5_22_6")).sendKeys(Last);
        comboBox.selectByValue("Delivery|3");
        FullName=driver.findElement(By.id("input_5_22_3")).getAttribute("value")+" "+driver.findElement(By.id("input_5_22_6")).getAttribute("value");
        assertEquals(driver.findElement(By.xpath("//div[@class='ginput_container']/span")).getText(), "$10.50", "price is not 10.50$ as expected");
    }

    @Test
    /**
     * the Test03_FillCupon_SendAndValidate() is the third test case. it has several functions:
     * A. intercept the cupon value into String variable "Cupon"
     * B. construct the expected message that should appear in the popup alert (Full name and cupon code) and store it to String variable
     * C. click the button and validate that the alert message the user received is as expected
     */
    public void Test03_FillCupon_SendAndValidate()
    {
        WebElement iFrame = driver.findElement(By.xpath("//iframe[@src='coupon.html']"));
        driver.switchTo().frame(iFrame);
        String Cupon = driver.findElement(By.id("coupon_Number")).getText();
        driver.switchTo().parentFrame();
        String ExpectedAlertMsg = FullName + " " + Cupon;
        driver.findElement(By.id("input_5_20")).sendKeys(Cupon);
        driver.findElement(By.id("gform_submit_button_5")).click();
        popup = driver.switchTo().alert();
        String ActualAlertMsg=popup.getText();
        popup.accept();
        assertEquals(ActualAlertMsg, ExpectedAlertMsg, "The Alert message not display full name and cupon code");
    }
    /**
     * the Finish() is the last method that executed last. it close the alert popup by clicking OK and close the browser window
     */
    @AfterClass
    public void Finish()
    {
        driver.quit();
    }




}




