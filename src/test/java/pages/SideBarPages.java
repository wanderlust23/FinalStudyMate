package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SideBarPages {

    public SideBarPages(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//body/div/div/div/nav/a")
    public List<WebElement> listOfSideBar;
}
