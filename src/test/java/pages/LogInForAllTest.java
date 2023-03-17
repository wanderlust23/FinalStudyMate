package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class LogInForAllTest {
    public LogInForAllTest(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name="email")
    public WebElement emailBox;

    @FindBy(name = "password")
    public WebElement passwordBox;

    @FindBy(xpath = "(//button)[3]")
    public WebElement logInBtn;

//    @FindBy(id="mui-component-select-language")
//    public WebElement languageButton;
//
//    @FindBy(xpath = "//ul/li")
//    public List<WebElement> languageOptions;


    public void login(String email, String password){
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        logInBtn.click();
    }
}
