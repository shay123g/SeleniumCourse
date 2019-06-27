package Sikuli;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class SikuliInfra
{
    Screen screen=new Screen();
    public void SearchClip(String image1,String image2,double timeout,String text) throws FindFailed
    {
        screen.wait(image1,timeout);
        screen.click("image");
        screen.type(image1,text);
        screen.wait(image2,timeout);
        screen.click("image2");
    }
    public void HoveronClip(String image,double timeout) throws FindFailed
    {
        screen.wait(image,timeout);
        screen.hover("image");
    }

    public Match VerifyElementExist(String image, double timeout) throws FindFailed
    {
        screen.wait(image,timeout);
        return screen.find("image");
    }

}
