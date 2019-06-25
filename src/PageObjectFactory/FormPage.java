package PageObjectFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FormPage
{
    @FindBy(how= How.ID, using="occupation")
    public WebElement Occupation;

    @FindBy(how= How.ID,using="age")
    public WebElement Age;

    @FindBy(how= How.ID, using="location")
    public WebElement Location;

    @FindBy(how=How.XPATH, using="//*[@id=\"contact_form\"]/fieldset/ol/li[4]/a/input")
    public WebElement SubmitButton;

    public void FillForm (String job,String age, String location)
    {
        Occupation.sendKeys(job);
        Age.sendKeys(age);
        Location.sendKeys(location);
        SubmitButton.click();
    }

}
