package ExternalFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.junit.Assert.assertEquals;

public class BMIWithConfig
{
    WebDriver driver;
    String BMI_res,message;
    WebElement weight,height;
    Integer h,w;
    Integer BMI_Calc=new Integer(0);

    @BeforeClass
    public void InitVariables()
    {
        System.out.println("Initializing...");
        try
        {
            w=Integer.parseInt(LogicClass.getData("whightValue"));
            h=Integer.parseInt(LogicClass.getData("heightValue"));
            BMI_Calc=w/(h/100*h/100);
            System.setProperty("webdriver.chrome.driver",LogicClass.getData("ChromeDriverPath"));
            driver = new ChromeDriver();
            driver.get(LogicClass.getData("url"));
        }
        catch (Exception  e)
        {
            e.printStackTrace();
        }
        weight=driver.findElement(By.id("weight"));
        height=driver.findElement(By.id("hight"));

    }
    @Test
    public void Test01FillAndCalc()
    {
        try
        {
            weight.sendKeys(LogicClass.getData("whightValue"));
            height.sendKeys(LogicClass.getData("heightValue"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        driver.findElement(By.id("calculate_data")).click();
        BMI_res=driver.findElement(By.id("bmi_result")).getAttribute("value");
        message=driver.findElement((By.id("bmi_means"))).getAttribute("value");

    }
    @Test
    public void Test02Assert()
    {
        assertEquals("BMI result not as expected",BMI_Calc,BMI_res);
    }

    @AfterClass
    public void Exit()
    {
        driver.quit();
    }
}

