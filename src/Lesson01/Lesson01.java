package Lesson01;

import org.testng.annotations.*;

public class Lesson01
{
    @BeforeMethod
    public void BeforeMethod()
    {
        System.out.println("Shay@BeforeMethod");
    }
    @AfterMethod
    public void AfterMethod()
    {
        System.out.println("Shay@AfterMethod");
    }

    @BeforeClass
    public void BeforeClass()
    {
        System.out.println("Shay@BeforeClass");
    }
    @Test
    public void Test02_Test2()
    {
        System.out.println("Shay@Test2");
    }
    @AfterClass
    public void AfterClass()
    {
        System.out.println("Shay@AfterClass");
    }
    @Test
    public void Test01_Test1()
    {
        System.out.println("Shay@Test1");
    }


}
