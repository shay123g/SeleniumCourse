package PageObjectFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage
{
    @FindBy(how= How.NAME, using="username2")
    public WebElement username;

    @FindBy(how=How.NAME,using="password2")
    public WebElement password;

    @FindBy(how=How.ID,using="submit")
    public WebElement submitButton;


    public void DoLogin(String name, String pass)
    {
        username.sendKeys(name);
        password.sendKeys(pass);
        submitButton.click();
    }
}
