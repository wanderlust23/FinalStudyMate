package test;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.StudyMateGroupPage;
import pages.StudyMateLoginPage;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

public class Dilbara {

    Faker fakeData = new Faker();

    @AfterMethod (groups = {"Smoke"})
    public void cleanUp(){
        Driver.quitBrowser();
    }


    @BeforeMethod (groups = {"Smoke"})
    public void login(){
        StudyMateGroupPage studyMateGroupPage = new StudyMateGroupPage();
        StudyMateLoginPage studyMateLoginPage = new StudyMateLoginPage();
        Flow.wait(2000);
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        studyMateLoginPage.login(Config.getValue("studyMateLoginEmail"), Config.getValue("studyMateLoginPassword"));
    }

    @AfterMethod (groups = {"Smoke"})
    public void quiteDriver(){
        StudyMateGroupPage studyMateGroupPage = new StudyMateGroupPage();
        StudyMateLoginPage studyMateLoginPage = new StudyMateLoginPage();
        Flow.wait(2000);
        Driver.quitBrowser();
    }


    @Test
    public void createGroup(){
        StudyMateGroupPage studyMateGroupPage = new StudyMateGroupPage();
        StudyMateLoginPage studyMateLoginPage = new StudyMateLoginPage();
        Flow.wait(3000);
        studyMateGroupPage.createGroup.click();
        studyMateGroupPage.groupName.sendKeys(fakeData.company().name());
        studyMateGroupPage.date.sendKeys(Config.getValue("testDate"));
        studyMateGroupPage.description.sendKeys(fakeData.lordOfTheRings().location());
        studyMateGroupPage.createButton.click();
    }

    @Test (groups = {"Smoke"})
    public void deleteGroup(){
        StudyMateGroupPage studyMateGroupPage = new StudyMateGroupPage();
        StudyMateLoginPage studyMateLoginPage = new StudyMateLoginPage();
        Flow.wait(4000);
        int numberOfItemsBeforeDeleting = studyMateGroupPage.getTotalGroups();
        System.out.println(numberOfItemsBeforeDeleting);
        studyMateGroupPage.editDeleteButton.click();
        studyMateGroupPage.deleteGroup.click();
        Flow.wait(500);
        studyMateGroupPage.confirmDelete.click();
        Flow.wait(1000);
        System.out.println(studyMateGroupPage.getTotalGroups());
        int afterDeletingNumberOfItems = studyMateGroupPage.getTotalGroups();
        Assert.assertEquals(afterDeletingNumberOfItems+1, numberOfItemsBeforeDeleting,"Item didn't delete");

    }


}
