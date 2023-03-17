package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StudyMateGroupPage {
    public StudyMateGroupPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//header/button")
    public WebElement createGroup;

    @FindBy(xpath = "(//form[@id='group-form']//input)[2]")
    public WebElement groupName;

    @FindBy(xpath = "(//form[@id='group-form']//input)[3]")
    public WebElement date;

    @FindBy(xpath = "//form//textarea")
    public WebElement description;

    @FindBy(xpath = "(//form//button)[3]")
    public WebElement createButton;

    @FindBy(xpath = "//div[2]//div[1]/div[1]/div[3]/div/button")////div[1]//div[3]//button
    public WebElement editDeleteButton;

    @FindBy(xpath = "(//ul)[2]/li[1]")
    public WebElement editGroup;

    @FindBy(xpath = "(//ul)[2]/li[2]")
    public WebElement deleteGroup;

    @FindBy(xpath = "//div[8]/div[3]//div/button[2]")
    public WebElement confirmDelete;

    @FindBy(xpath = "//p[.='pagination.show']/../p[2]")
    public WebElement totalResult;

    public int getTotalGroups(){
        String totalResultStr = totalResult.getText();
        String totalNumberStr = totalResultStr.substring(totalResultStr.indexOf(" ")+1);
        return Integer.parseInt(totalNumberStr.trim());
    }





}