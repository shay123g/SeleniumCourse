package LocatorAdvanced01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class LocatorAdvanced01
{

        WebDriver driver;

        @BeforeClass
        public void InitVariables()
        {
            System.out.println("Initializing...");
            System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
            System.setProperty("webdriver.chrome.silentOutput", "true");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            driver = new ChromeDriver(options);
        }

        @Test
        public void FindElements()
        {
            driver.get("http://atidcollege.co.il/Xamples/ex_locators.html");
            ArrayList<WebElement> List = new ArrayList<>();
            List.add(driver.findElement(By.id("locator_id")));                  //id
            List.add((driver.findElement(By.name("locator_name"))));            //name
            List.add((driver.findElement(By.className("locator_class"))));     //className
            List.add((driver.findElement(By.tagName("p"))));                 //tagName
            List.add((driver.findElement(By.linkText("myLocator(5)"))));       //linktext
            List.add((driver.findElement(By.partialLinkText("(6)"))));         //partiallinktext
            List.add(driver.findElement(By.xpath("//input[@type='submit']")));   //Xpath
            List.add(driver.findElement(By.cssSelector("button[class='btn btn-2']")));  //CSSSelector

            for (WebElement elem : List)
                System.out.println(elem.getText());
        }

    @AfterClass
    public void Exit()
    {
        driver.quit();
    }

}
