package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class TrashPage {
    public TrashPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

//    @FindBy (xpath = "(//nav[@aria-label='pagination navigation']/../div/p)[3]")
//    public WebElement paginationFrom;

    //tbody/tr/td/div
    //tbody/tr/td/div//*[local-name()='svg'][@width='24']  xpath for delete button
    //tbody/tr/td/div//*[local-name()='svg'][@width='22']  xpath for recover button

    @FindBy (xpath = "//tbody/tr/td/div//*[local-name()='svg'][@width='22']")
    public List<WebElement> listOfRecoveryButtons;

    @FindBy (xpath = "//tbody/tr/td/div//*[local-name()='svg'][@width='24']")
    public List<WebElement> listOfDeleteButtons;


}
