package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;
import pages.TrashPage;
import utilities.*;

import java.util.List;

public class BaitikBaike {
    @AfterMethod (groups = {"Smoke"})
    public void cleanUp(){
        Driver.quitBrowser();
    }


    @Test (groups = {"Smoke"})
    public void recoverItemByCheckingThePagination(){
        LoginPage lp = new LoginPage();
        AdminPage ap = new AdminPage();
        TrashPage tp = new TrashPage();
        Driver.getDriver().get(Config.getValue("SMURL"));
        lp.login(Config.getValue("SMAdminEmail"), Config.getValue("SMAdminPassword"));
        Flow.wait(1000);
        ap.getSidebarOption("Trash").click();
        Flow.wait(2000);
        int actualNumber = Pagination.numberOfItems();
        List<WebElement> recoveryList = tp.listOfRecoveryButtons;
        if(recoveryList.size() > 0){
            recoveryList.get(0).click();
        }else {
            System.out.println("There are no elements to delete");
        }
        Flow.wait(2000);
        int numberOfElementsAfterRecovery = Pagination.numberOfItems();

        Assert.assertEquals( numberOfElementsAfterRecovery + 1, actualNumber,"Recovery of item is FAILED");
    }

    @Test
    public void recoverItemWithCheckingSuccessfullyRecoveryMessage(){
        LoginPage lp = new LoginPage();
        AdminPage ap = new AdminPage();
        TrashPage tp = new TrashPage();
        Driver.getDriver().get(Config.getValue("SMURL"));
        lp.login(Config.getValue("SMAdminEmail"), Config.getValue("SMAdminPassword"));
        Flow.wait(1000);
        ap.getSidebarOption("Trash").click();
        Flow.wait(2000);
        String expectedMessage = "Data successfully recovered";
        List<WebElement> recoveryList = tp.listOfRecoveryButtons;
        if(recoveryList.size() > 0){
            recoveryList.get(0).click();
        }else {
            System.out.println("There are no elements to recover");
        }
        Flow.wait(1000);
        String actualMessage = NotificationMessage.getNotificationMessage();

        Assert.assertEquals(actualMessage, expectedMessage, "Recovery of item is FAILED");
    }

    @Test
    public void recoverItemByCheckingTheTotalPagination(){
        LoginPage lp = new LoginPage();
        AdminPage ap = new AdminPage();
        TrashPage tp = new TrashPage();
        Driver.getDriver().get(Config.getValue("SMURL"));
        lp.login(Config.getValue("SMAdminEmail"), Config.getValue("SMAdminPassword"));
        Flow.wait(1000);
        int totalItemsBeforeRecovery = 0;

        ap.getSidebarOption("Groups").click();
        Flow.wait(2000);
        totalItemsBeforeRecovery += Pagination.numberOfItems();
        ap.getSidebarOption("Courses").click();
        Flow.wait(2000);
        totalItemsBeforeRecovery += Pagination.numberOfItems();
        ap.getSidebarOption("Teachers").click();
        Flow.wait(2000);
        totalItemsBeforeRecovery += Pagination.numberOfItems();
        ap.getSidebarOption("Students").click();
        Flow.wait(2000);
        totalItemsBeforeRecovery += Pagination.numberOfItems();

        ap.getSidebarOption("Trash").click();
        Flow.wait(2000);
        List<WebElement> recoveryList = tp.listOfRecoveryButtons;
        if(recoveryList.size() > 0){
            recoveryList.get(0).click();
        }else {
            System.out.println("There are no elements to recover");
        }
        Flow.wait(2000);

        int totalItemsAfterRecovery = 0;
        ap.getSidebarOption("Groups").click();
        Flow.wait(2000);
        totalItemsAfterRecovery += Pagination.numberOfItems();
        ap.getSidebarOption("Courses").click();
        Flow.wait(2000);
        totalItemsAfterRecovery += Pagination.numberOfItems();
        ap.getSidebarOption("Teachers").click();
        Flow.wait(2000);
        totalItemsAfterRecovery += Pagination.numberOfItems();
        ap.getSidebarOption("Students").click();
        Flow.wait(2000);
        totalItemsAfterRecovery += Pagination.numberOfItems();

        Assert.assertEquals(totalItemsAfterRecovery - 1, totalItemsBeforeRecovery, "Recovery of item is FAILED");
    }

}
