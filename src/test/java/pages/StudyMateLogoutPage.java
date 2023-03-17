package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StudyMateLogoutPage {

    public StudyMateLogoutPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//p[.='Administrator']")
    public WebElement administratorUser;

    @FindBy(xpath = "(//li[@tabindex= '0'])[7]")
    public WebElement logoutButton;

    @FindBy(xpath = "//button[@textcolor = '#EA1313']")
    public WebElement logoutConfirmationPopup;

    public void logout (){
        administratorUser.click();
        logoutButton.click();
        logoutConfirmationPopup.click();
    }

}
