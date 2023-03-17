package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class StudyMateLoginPage {

    public StudyMateLoginPage () {
        PageFactory.initElements(Driver.getDriver(), this);
    }



    @FindBy(name = "email")
    public WebElement emailInputBox;

    @FindBy(name = "password")
    public WebElement passwordInputBox;

    @FindBy(xpath = "(//button)[3]")
    public WebElement loginButton;

    @FindBy(id = "mui-component-select-language")
    public WebElement languageDropdownBtn;

    @FindBy(xpath = "//ul/li")
    public List<WebElement> languageOptions;

    @FindBy(xpath = "//li[.='Русский']")
    public WebElement russianLanguage;

    @FindBy(xpath = "//li[.='English']")
    public WebElement englishLanguage;


    public void login(String email, String password) {
        emailInputBox.sendKeys(email);
        passwordInputBox.sendKeys(password);
        loginButton.click();


    }
}
