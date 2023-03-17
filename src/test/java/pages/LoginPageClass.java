package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.Flow;

import java.util.List;

public class LoginPageClass {
    public LoginPageClass()  {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(name = "email")
    public WebElement emailInputBox;
    @FindBy(name = "password")
    public WebElement passwordInputBox;
    @FindBy(xpath = "//button[.='Log in']")
    public WebElement loginButton;
    @FindBy(id = "mui-component-select-language")
    public WebElement languageDropdown;
    @FindBy(xpath = "//ul/li")
    public List <WebElement> languageOptions;
    @FindBy(xpath = "//div/button")
    public WebElement passwordVisibilitySign;
    @FindBy(xpath = "(//button)[2]")
    public WebElement forgotPasswordButton;
    @FindBy(xpath = "//p[.='Sign up with Google']")
    public WebElement googleSignInButton;
    @FindBy(xpath = "//form/div[1]/div/p/span")
    public WebElement emailErrorMessage;
    @FindBy(xpath = "//form/div[2]/div/p/span")
    public WebElement passwordErrorMessage;
    @FindBy(xpath = "(//form)[2]/p")
    public WebElement forgotPasswordMessage;
    @FindBy(xpath = "(//form)[2]/div/div/input")
    public WebElement forgotPasswordEmailAddressInputBox;
    @FindBy(xpath = "(//form)[2]/button")
    public WebElement forgotPasswordSubmitButton;


    public void login(String email, String password){
        emailInputBox.sendKeys(email);
        Flow.wait(3000);
        passwordInputBox.sendKeys(password);
        Flow.wait(3000);
        loginButton.click();
    }
    public void signUpGmail(){
        Flow.wait(3000);
        googleSignInButton.click();
    }
    public void resetPassword(String email){
        forgotPasswordButton.click();
        Flow.wait(3000);
        forgotPasswordEmailAddressInputBox.sendKeys(email);
        forgotPasswordSubmitButton.click();
    }
}
