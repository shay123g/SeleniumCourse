package InterviewQuestion01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class support
{
    public void verifyElements(WebDriver driver)
    {
        List<WebElement> list;

        //get the first un-ordered list (this is the required list)
        list = driver.findElement(By.className("ms-list")).findElements(By.tagName("span"));

        //count=counter for construct string text: Elem 3, Elem 4...Elem N   count=3,4,5....N
        int count = 3;
        //iterate until end of the list
        while (count <= list.size())
         {
                String text = "elem " + count;
                for (WebElement elem : list)
                {
                    System.out.println(elem.getText());
                    assertEquals(elem.getText(),text,text+ " not found in the left list");
                    count++;
                    break;
                }
                }
         }
    }





