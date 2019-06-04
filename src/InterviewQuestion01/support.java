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
        int count = 0;
        //flag=0 - element found. flag=1 element not found
        int flag=0;
        //iterate until end of the list
        while (count < list.size())
         {
                int elemnum=3;
                String text = "elem " + elemnum;
                for (int j=0;j<list.size();j++)
                {
                    String Actual=list.get(j).getText();
                    if (!(Actual.equals(text)))
                       flag=1;
                    else
                    {
                        flag=0;
                        elemnum++;
                        count++;
                        break;
                    }
                }
                assertEquals(flag,0,text+" didnt exist in list");

         }
       }
    }





