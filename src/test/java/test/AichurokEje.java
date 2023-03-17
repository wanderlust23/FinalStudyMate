package test;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.CoursesPageClass;
import pages.LoginPageClass;
import pages.MainPageClass;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

public class AichurokEje {

    Faker fake = new Faker();
    @AfterMethod (groups = {"Smoke"})
    public void cleanUp(){
        Driver.quitBrowser();
    }
    @Test
    public void cancelEditing(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        mainPageClass.coursesSideBarButton.click();
        Flow.wait(2000);
        coursesPageClass.oneCourseThreeDotsButton.click();
        Flow.wait(2000);
        coursesPageClass.oneCourseEditButton.click();
        Flow.wait(2000);
        String before = coursesPageClass.courseNameInputBox.getText();
        coursesPageClass.courseNameInputBox.sendKeys(Keys.COMMAND + "a");
        coursesPageClass.courseNameInputBox.sendKeys(Keys.DELETE);
        coursesPageClass.courseNameInputBox.sendKeys("QWERTYUIOP");
        coursesPageClass.cancelButtonEdit.click();
        coursesPageClass.oneCourseThreeDotsButton.click();
        Flow.wait(2000);
        coursesPageClass.oneCourseEditButton.click();
        Flow.wait(2000);
        String after = coursesPageClass.courseNameInputBox.getText();
        Assert.assertEquals(before, after, "Failed! It changed the name after cancelling!");
    }

    @Test
    public void editName(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        mainPageClass.coursesSideBarButton.click();
        Flow.wait(2000);
        String nameBeforeChange = coursesPageClass.coursesName.getText();
        Flow.wait(1000);
        coursesPageClass.editName(fake.lordOfTheRings().character());
        String actualEditMessage = coursesPageClass.editMessage.getText();
        Flow.wait(1000);
        Assert.assertEquals(actualEditMessage, Config.getValue("editSuccessfullyMessage"));
        Flow.wait(2000);
        String nameAfterChange = coursesPageClass.coursesName.getText();
        Assert.assertNotEquals(nameAfterChange, nameBeforeChange, "Failed! The name was not updated after edit");
    }

    @Test (groups = {"Smoke"})
    public void editDescription(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        mainPageClass.coursesSideBarButton.click();
        Flow.wait(2000);
        String descriptionBeforeChange = coursesPageClass.description.getText();
        Flow.wait(1000);
        coursesPageClass.editDescription(fake.lordOfTheRings().location());
        Flow.wait(1000);
        String actualEditMessage = coursesPageClass.editMessage.getText();
        Assert.assertEquals(actualEditMessage, Config.getValue("editSuccessfullyMessage"));
        Flow.wait(2000);
        String descriptionAfterChange = coursesPageClass.description.getText();
        Assert.assertNotEquals(descriptionAfterChange, descriptionBeforeChange,
                "Failed! The description was not updated after edit");
    }

    @Test
    public void editDate(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        mainPageClass.coursesSideBarButton.click();
        Flow.wait(2000);
        String dateBeforeChange = coursesPageClass.date.getText();
        Flow.wait(1000);
        coursesPageClass.editDate("10122018");
        Flow.wait(1000);
        String actualEditMessage = coursesPageClass.editMessage.getText();
        Assert.assertEquals(actualEditMessage, Config.getValue("editSuccessfullyMessage"));
        Flow.wait(2000);
        String dateAfterChange = coursesPageClass.date.getText();
        Assert.assertNotEquals(dateAfterChange, dateBeforeChange,
                "Failed! The date was not updated after edit");
    }

}
