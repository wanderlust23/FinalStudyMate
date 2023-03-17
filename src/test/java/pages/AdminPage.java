package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AdminPage {

    public AdminPage (){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//p[.='Administrator']")
    public WebElement administratorButton;

//    @FindBy(className = "jam jam-world")  //(//div[@id='root']/div/div/header/div/div/div)[3]
//    public WebElement languageButton;

    @FindBy(xpath = "(//ul)[8]/li")
    public WebElement logOutButton;

    @FindBy(xpath = "//button[.='Log out']")
    public WebElement logOutMessagePopUp;

    @FindBy(xpath = "(//a/li)[2]")                //this one is for Admin Page
    public WebElement announcementButton;

    @FindBy(xpath = "//a/li")
    public  List<WebElement> sidebarList;

    public void logOut(){
        administratorButton.click();
        logOutButton.click();
        logOutMessagePopUp.click();
    }

    public WebElement getSidebarOption(String nameOfSidebarOption){
        if(nameOfSidebarOption.equalsIgnoreCase("Groups")){
            return sidebarList.get(0);
        } else if (nameOfSidebarOption.equalsIgnoreCase("Courses")) {
            return sidebarList.get(1);
        }else if (nameOfSidebarOption.equalsIgnoreCase("Teachers")){
            return sidebarList.get(2);
        }else if (nameOfSidebarOption.equalsIgnoreCase("Students")){
            return sidebarList.get(3);
        } else if (nameOfSidebarOption.equalsIgnoreCase("Announcements")) {
            return sidebarList.get(4);
        } else if (nameOfSidebarOption.equalsIgnoreCase("Trash")) {
            return sidebarList.get(5);
        } else if (nameOfSidebarOption.equalsIgnoreCase("Schedule")) {
            return sidebarList.get(6);
        }else {
            System.out.println("Please make sure you entered right sidebar menu");
            return null;
        }
    }

}
