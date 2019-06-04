package SwitchNavigation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SwitchNav
{
    ChromeDriver driver;
    String status;
    Alert pop;
    Set<String> handlers;
    String mainWindowHandle;

    @BeforeClass
    public void Init()
    {
        System.setProperty("webdriver.chrome.driver","C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.navigate().to("http://atidcollege.co.il/Xamples/ex_switch_navigation.html");
        mainWindowHandle=driver.getWindowHandle();

    }
    @Test
    public void Test01_ShowAlert()
    {
        driver.findElement(By.id("btnAlert")).click();
        pop=driver.switchTo().alert();
        System.out.println("Show Alert message: "+pop.getText());
        pop.accept();
        status=driver.findElement(By.tagName("span")).getText();
        assertEquals(status,"Alert is gone.", "no match");
    }
    @Test
    public void Test02_ShowPrompt()
    {
        driver.findElement(By.id("btnPrompt")).click();
        pop=driver.switchTo().alert();
        System.out.println("Show Prompt message: "+pop.getText());
        pop.sendKeys("Hello");
        pop.accept();
        status=driver.findElement(By.tagName("span")).getText();
        assertEquals(status,"Hello", "no match");
    }
    @Test
    public void Test03_ConfirmBoxOK()
    {
        driver.findElement(By.id("btnConfirm")).click();
        pop=driver.switchTo().alert();
        System.out.println("Show Confirm message: "+pop.getText());
        pop.accept();
        status=driver.findElement(By.tagName("span")).getText();
        assertEquals(status,"Confirmed.", "no match");
    }

    @Test
    public void Test04_ConfirmBoxCancel()
    {
        driver.findElement(By.id("btnConfirm")).click();
        pop=driver.switchTo().alert();
        System.out.println("Show Confirm message: "+pop.getText());
        pop.dismiss();
        status=driver.findElement(By.tagName("span")).getText();
        assertEquals(status,"Rejected!", "no match");
    }

    @Test
    public void Test05_IframeMessage()
    {

        WebElement iFrame=driver.findElement(By.xpath("//iframe[@src='ex_switch_newFrame.html']"));
        driver.switchTo().frame(iFrame);
        String IfrmMsg=driver.findElement(By.id("iframe_container")).getText();
        System.out.println("Iframe message: "+IfrmMsg);
        driver.switchTo().parentFrame();
    }

    @Test
    public void Test06_NewTab()
    {
        driver.findElement(By.id("btnNewTab")).click();
        handlers=driver.getWindowHandles();
        for (String handle:handlers)
            if (mainWindowHandle!=handle)
                driver.switchTo().window(handle);
        String TabMessage=driver.findElement(By.id("new_tab_container")).getText();
        System.out.println("New Tab Message: "+TabMessage);
        handlers.clear();
        assertEquals(TabMessage,"This is a new tab", "no match");
    }
    @Test
    public void Test07_NewWindow()
    {
        driver.switchTo().window(mainWindowHandle);
        driver.findElement(By.linkText("Open New Window")).click();
        handlers=driver.getWindowHandles();
        for (String handle:handlers)
            if (driver.getWindowHandle()!=handle)
                driver.switchTo().window(handle);
        String WindowMessage=driver.findElement(By.id("new_window_container")).getText();
        System.out.println("New Window Message: "+WindowMessage);
        assertEquals(WindowMessage,"This is a new window", "no match");
    }
    @AfterClass
    public void Exit()
    {
        driver.quit();
    }
}
