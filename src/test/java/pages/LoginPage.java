package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {
    public LoginPage() {
        /*
        Meerim eje
         */

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "email")
    public WebElement usernameBox;

    @FindBy(name = "password")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[2]")
    public WebElement loginButton;

    @FindBy(xpath = "//form/div[1]/div/p/span")
    public WebElement emailErrorMessage;

    @FindBy(xpath = "//form/div[2]/div/p/span")
    public WebElement passwordErrorMessage;

    @FindBy(xpath = "//p[@class='sc-dkrFOg hbyUzQ']")
    public WebElement notfound;

    public void login(String email, String password){
        usernameBox.sendKeys(email);
        passwordBox.sendKeys(password);
        loginButton.click();


    }



}
