package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MainPageClass {
    public MainPageClass(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "//nav/a[2]/li")
    public WebElement coursesSideBarButton;
}
