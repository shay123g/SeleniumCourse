package DesktopApps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Calculator
{
    String calculatorApplicationPath = "C:/windows/system32/calc.exe";
    String winiumDriverPath = "C:/Automation/libs/Winium.Desktop.Driver.exe";
    DesktopOptions options;
    WiniumDriverService service;
    WebDriver driver;

    @BeforeClass

    public void startSession() throws IOException, InterruptedException

    {

        options = new DesktopOptions();

        options.setApplicationPath(calculatorApplicationPath);

        File drivePath = new File(winiumDriverPath);

        service = new WiniumDriverService.Builder().

                usingDriverExecutable(drivePath).

                usingPort(9999).

                withVerbose(true).

                withSilent(false).

                buildDesktopService();

        service.start();

        driver = new WiniumDriver(service, options);

        Thread.sleep(1000);

    }

    @Test
    public void Test01_test() throws InterruptedException
    {
        driver.findElement(By.id("139")).click();
        driver.findElement(By.id("92")).click();
        driver.findElement(By.id("133")).click();
        driver.findElement(By.id("121")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("Item 1")).click();
        Thread.sleep(2000);
    }

    @AfterClass
 public void Done()
    {
        driver.quit();

    }


}
