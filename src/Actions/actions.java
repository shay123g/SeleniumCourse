package Actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class actions
{
    WebDriver driver;
    Actions Action;

    @BeforeClass
    public void GoToUrl()
    {
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://atidcollege.co.il/Xamples/ex_actions.html");
         Action= new Actions(driver);
    }

    @Test
    public void Test01_DragnDrop()
    {
        try
        {
            WebElement drag = driver.findElement(By.id("draggable"));
            WebElement drop = driver.findElement(By.id("droppable"));
            Action.dragAndDrop(drag, drop).build().perform();
            Thread.sleep(2000);
            assertTrue(driver.findElement(By.xpath("//div[@id='droppable']/p")).isDisplayed());
        }
    catch (Exception e)
    {
        if (e instanceof ElementNotFoundException) {
            System.out.println("Element not found\n" + e.getMessage());
            fail();
        } else {
            e.getMessage();
            fail();
        }
    }
        catch (AssertionError e)
        {
            System.out.println("the string 'Dropped!' not display as expected\n"+e.getMessage());
            fail();
        }
    }
    @Test
    public void Test02_SelectItems() {
        try {
            List<WebElement> items = driver.findElements(By.xpath("//ol[@id='select_items']/li"));
            Action.clickAndHold(items.get(1)).clickAndHold(items.get(2)).build().perform();
            Thread.sleep(2000);
        }
        catch (Exception e) {
            System.out.println("Element not found\n" + e.getMessage());
            fail();
        }
    }
        @Test
        public void Test03_DiscoverElement() throws InterruptedException {
          try
          {
              Action.doubleClick(driver.findElement(By.id("dbl_click"))).build().perform();
              Thread.sleep(2000);
              assertTrue(driver.findElement(By.id("demo")).isEnabled());
          }
          catch(NoSuchElementException e)
          {
                System.out.println("Paragraph to double click not found\n "+e.getMessage());
                fail();
            }

          catch (AssertionError e)
          {
              System.out.println("Element didnt exist in the DOM and expected to be\n"+e.getMessage());
              fail();
          }
        }

        @Test
        public void Test04_Hover() throws InterruptedException {
            try
            {
                Action.moveToElement(driver.findElement(By.id("mouse_hover"))).build().perform();
                System.out.println(driver.findElement(By.xpath("//span[contains(@style,'rgb')]")).getAttribute("style"));
                Thread.sleep(2000);
                assertNotNull(driver.findElement(By.xpath("//span[contains(@style,'rgb')]")));
            }
            catch(NoSuchElementException e)
            {
                System.out.println("hovered element not found\n"+e.getMessage());
                fail();
            }
            catch (AssertionError e)
            {
                System.out.println("The element didnt change it color therefore webdriver didnt find it\n"+e.getMessage());
                fail();
            }
        }
        @Test
        public void Test05_Scroll()
        {
            try {
                WebElement scrollElement = driver.findElement(By.id("scrolled_element"));
                JavascriptExecutor excecutor = (JavascriptExecutor) driver;
                excecutor.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
                assertTrue(scrollElement.isDisplayed());
            }
             catch(NoSuchElementException e)
            {
                System.out.println("scroll element not found\n"+e.getMessage());
                fail();
            }
            catch (AssertionError e)
            {
                System.out.println("The scroll element didnt display on screen\n"+e.getMessage());
                fail();
            }
        }


    @AfterClass
    public void Finish() throws InterruptedException {
       Thread.sleep(2000);
        driver.quit();
    }
}
