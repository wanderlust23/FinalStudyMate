package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EditPage {


    public  EditPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }



    @FindBy(xpath = "//div/div/button")
    public WebElement threeDotButtons;

    @FindBy(xpath = "(//div/div/button)[1]")
    public WebElement firstDotButton;

    @FindBy(xpath = "//div[2]/div[3]/ul/li[1]")
    public WebElement editBtn;

    @FindBy(xpath = "//form//div/input[@name='name']")
    public  WebElement groupName;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement description;

    @FindBy(xpath = "//button[@form='group-form']")
    public WebElement createButton;


}
