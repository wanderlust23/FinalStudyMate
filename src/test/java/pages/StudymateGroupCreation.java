package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.Flow;

public class StudymateGroupCreation {
    public StudymateGroupCreation() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//header/button")
    public WebElement groupCreateBtn;

    @FindBy(name = "name")
    public WebElement groupNameInput;

    @FindBy(name = "dateOfFinish")
    public WebElement dateInputBox;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement descriptionInputBox;

    @FindBy(xpath = "//button[@form='group-form']")
    public WebElement createButton;

    @FindBy(xpath = "//div/button[.='Cancel']")
    public WebElement cancelButton;

    @FindBy(xpath = "//div[@role='presentation']")
    public WebElement pictureUploadButton;

    @FindBy(xpath = "//p[.='pagination.show']/../p[2]")
    public WebElement totalResult;

    public void boxCreation(String groupName, String date, String description) {
        groupCreateBtn.click();
        groupNameInput.sendKeys(groupName);
        dateInputBox.sendKeys(date);
        Flow.wait(1000);
        descriptionInputBox.sendKeys(description);
        Flow.wait(1000);
        Flow.wait(1500);
        createButton.click();

    }

    public int getTotalGroups() {
        String totalResultStr = totalResult.getText();
        String totalNumberStr = totalResultStr.substring(totalResultStr.indexOf(" ") + 1);
        int totalNumberInt = Integer.parseInt(totalNumberStr.trim());
        return totalNumberInt;

    }

}
