package test;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.EditPage;
import pages.LogInForAllTest;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;
import utilities.NotificationMessage;

public class ZarrinaEje {
//    LogInForAllTest smlp = new LogInForAllTest();
//    EditPage editpage = new EditPage();

    @AfterMethod (groups = {"Smoke"})
    public void cleanUp(){
        Driver.quitBrowser();
    }



    @Test (groups = {"Smoke"})
    public void EditGroupTest(){
        LogInForAllTest smlp = new LogInForAllTest();
        EditPage editpage = new EditPage();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        smlp.login(Config.getValue("studyMateLoginEmail"),Config.getValue("studyMateLoginPassword"));
        Flow.wait(2000);

        editpage.firstDotButton.click();
        Flow.wait(2000);
        editpage.editBtn.click();
        Flow.wait(2000);
//        String before = editpage.groupName.getText();
//        System.out.println(before);
        editpage.groupName.sendKeys(Keys.COMMAND + "a");
        editpage.groupName.sendKeys(Keys.DELETE);
        Faker faker = new Faker();
        String editedGroupName = faker.name().fullName();
        String editedGroupDescription = faker.book().author();
        editpage.groupName.sendKeys(editedGroupName);
        Flow.wait(2000);
       editpage.description.click();
        editpage.description.sendKeys(Keys.COMMAND + "a");
        editpage.description.sendKeys(Keys.DELETE);
        editpage.description.sendKeys(editedGroupDescription);
        editpage.createButton.click();
        Flow.wait(1000);
        String message = NotificationMessage.getNotificationMessage();
//        String after = editpage.groupName.getText();
        //System.out.println(after);
        String expectedMessage = "Group successfully updated";
        Assert.assertEquals(message, expectedMessage, "Edit group test FAILED");










    }
}
