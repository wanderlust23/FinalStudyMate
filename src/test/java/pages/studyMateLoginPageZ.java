package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class studyMateLoginPageZ {


    public studyMateLoginPageZ(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "(//input)[1]")
    public static WebElement emailInput;

    @FindBy(xpath = "(//input)[2]")
    public static WebElement passwordInput;

    @FindBy(xpath = "//div[@id='mui-component-select-language']")
    public WebElement languageDropdown;

    @FindBy(xpath = "//div//ul/li")
    public List<WebElement> dropDownOptions;

    @FindBy(xpath = "(//div//form//button)[3]")
    public WebElement loginBtn;


    @FindBy(xpath = "//div//a[@href='/admin/groups'])[1]")
    public WebElement groupsLink;

    @FindBy(xpath = "(//div//button)[2]")
    public WebElement threeDotsBtn;

    @FindBy (xpath = "//div//ul/li[@role='menuitem']")//[1]
    public List <WebElement> dropDownOption;

    @FindBy(xpath="(//ul)[2]/li[1]")
    public WebElement editBtn;


    @FindBy (xpath = "//div//ul/li[@role='menuitem'][2]")//[2]
    public WebElement deleteBtn;

    @FindBy(xpath = "//div/input[@id=':r4:']")
    public WebElement groupName;

    @FindBy (xpath = "//div/button[@type='submit'] ")
    public WebElement saveBtn;

    public void login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void clearTextField( WebElement webElement){
        while (!webElement.getAttribute("value").equals("")){
            webElement.sendKeys(Keys.BACK_SPACE);
        }

    }


}
