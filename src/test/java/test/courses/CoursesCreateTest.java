package test.courses;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.CoursesPageClass;
import pages.LoginPageClass;
import pages.MainPageClass;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

public class CoursesCreateTest {
//    LoginPageClass loginPageClass = new LoginPageClass();
//    CoursesPageClass coursesPageClass = new CoursesPageClass();
//    MainPageClass mainPageClass = new MainPageClass();
    Faker fake = new Faker();
    @AfterMethod
    public void cleanUp(){
        Driver.quitBrowser();
    }
    @Test
    //1 - w
    public void CoursesSideBarButtonNavigateToCoursesSectionTest(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        mainPageClass.coursesSideBarButton.click();
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(Config.getValue("expectedCoursesPage")),
                "Failed! When clicking on the side bar Courses button, it did not navigate to courses section");
    }
    @Test
    //2 - w
    //Name, description and date are correct:
    public void createCourseTestPositive(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(fake.funnyName().name(), fake.harryPotter().quote(), "21092023");
        String actualErrorMessage = coursesPageClass.errorMessage.getText();
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        Flow.wait(2000);
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertNotEquals(sizeBeforeAddingCourse, sizeAfterAddingCourse,
                "Failed to create a new course!");
        Flow.wait(2000);
        Assert.assertEquals(actualErrorMessage, Config.getValue("expectedSuccessfulCreationCourseMessage"),
                "Failed! The pop up message about course created did not display!");
    }
    @Test
    //3 - w
    //Name long 257 characters limit: The website has this limit:but it should be shorter
    public void createCourseLongName(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(Config.getValue("courseLongName257"), fake.harryPotter().quote(), "21092023");
        String actualErrorMessage = coursesPageClass.errorMessage.getText();
        Flow.wait(2000);
        String expectedErrorMessage = "Error! You have exceeded the length! It should be no more than 150 characters";
        if(coursesPageClass.courseNameLongErrorMessage.isDisplayed()){
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage ,
                    "Failed! The long course name error message does not meet the requirements");
        }else{
            Flow.wait(2000);
            coursesPageClass.goToFirstPage();
            int sizeAfterAddingCourse = coursesPageClass.checkSize();
            Assert.assertNotEquals(sizeAfterAddingCourse,sizeBeforeAddingCourse,
                    "Failed! The new course with a long name was created!");
        }
    }

    @Test
    //4
    //Description long 300 characters limit: The website has this limit but it should be changed to the one with more(450)
    public void createCourseLongDescription(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(fake.harryPotter().quote(), Config.getValue("courseLongDescription300"), "21092023");
        String actualErrorMessage = coursesPageClass.errorMessage.getText();
        Flow.wait(2000);
        String expectedErrorMessage = "Error! You have exceeded the length! It should be no more than 400 characters";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, //there should be an error message,
                "Failed! The long description name error message does not meet the requirements");
        coursesPageClass.goToFirstPage();
        Flow.wait(2000);
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeBeforeAddingCourse, sizeAfterAddingCourse,
                "Failed! The new course with a long description was created!");
        //manual: it creates a course with a 300 characters description,but it doesn't show any error message, nor it lets
        //to put more characters. It cuts when 300 without telling the user about it.
    }
    @Test
    //5 - w
    public void createCourseEmptyName(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        mainPageClass.coursesSideBarButton.click();
        Flow.wait(2000);
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse("", fake.harryPotter().quote(), "21092023");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse, sizeBeforeAddingCourse,
                "Failed! The new course with empty name was created!");
    }
    @Test
    //6 - w
    public void createCourseEmptyDescription(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(fake.university().name(), "", "21092023");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse,sizeBeforeAddingCourse,
                "Failed! The new course with empty description was created!");
    }
    @Test
    //7 - w
    public void createCourseSpecialCharactersName(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse("!@#$%^&*()_+~|?><", fake.harryPotter().quote(), "21092023");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse, sizeBeforeAddingCourse,
                "Failed! The new course with a name only with special characters was created!");
    }
    @Test
    //8 - w
    public void createCourseSpecialCharactersDescription(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(fake.name().fullName(), "!@#$%^&*()_+~?>|><", "21092023");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse,sizeBeforeAddingCourse,
                "Failed! The new course with a description only with special characters was created!");
    }
    @Test
    //9 - w
    public void createCourseOneCharacterName(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse("d",fake.harryPotter().quote(), "21092023");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse,sizeBeforeAddingCourse,
                "Failed! The new course with a name only with one character was created!");
    }
    @Test
    //10 - w
    public void createCourseOneCharacterDescription(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(fake.harryPotter().spell(), "q", "21092023");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse,sizeBeforeAddingCourse,
                "Failed! The new course with a description only with one character was created!");
    }
    @Test
    //11 - w
    public void createCourseIncorrectYear(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(fake.harryPotter().spell(), fake.harryPotter().house(), "21101");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse, sizeBeforeAddingCourse,
                "Failed! The new course with incorrect year was created!");
        //It should ask for all for digits to create a course.
    }
    @Test
    //12 - w
    public void createCoursePastDate(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(fake.harryPotter().spell(), fake.harryPotter().character(), "211011998");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse,sizeBeforeAddingCourse,
                "Failed! The new course with incorrect year (past - before 2023) was created!");
        //The date is for finish date of course. It should take only dates in future but not past.
    }
    @Test
    //13 - w
    public void createCourseIncorrectDate(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        Flow.wait(2000);
        coursesPageClass.createCourse(fake.harryPotter().spell(), fake.harryPotter().spell(), "321320");
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeAfterAddingCourse, sizeBeforeAddingCourse,
                "Failed! The new course with incorrect year (day 32, month 13) was created!");
    }
    @Test
    //14 - w
    public void cancelCreatingCourse(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        int sizeBeforeAddingCourse =  coursesPageClass.checkSize();
        coursesPageClass.createCourseButton.click();
        coursesPageClass.courseNameInputBox.sendKeys("Introduction to PSY");
        coursesPageClass.descriptionInputBox.sendKeys("Introduction to PSY");
        Flow.wait(1500);
        coursesPageClass.cancelButton.click();
        Flow.wait(2000);
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeBeforeAddingCourse, sizeAfterAddingCourse,
                "Failed! The new course was created after cancelling!");
    }
    @Test
    //15 - w
    public void createCourseDuplicateName(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        coursesPageClass.createCourse("Philosophy", fake.harryPotter().house(), "21102019");
        Flow.wait(2000);
        int sizeBeforeAddingDuplicateCourse =  coursesPageClass.checkSize();
        coursesPageClass.goToFirstPage();
        Flow.wait(2000);
        coursesPageClass.createCourse("Philosophy", fake.harryPotter().house(), "21102019");
        String actualErrorMessage = coursesPageClass.errorMessage.getText();
        Flow.wait(2000);
        Assert.assertEquals(coursesPageClass.duplicateCourseErrorMessage.getText(), Config.getValue("duplicateCourseNameErrorMessage"),
                "Failed! The duplicate course name error message discrepancy detected");
        coursesPageClass.cancelButton.click();
        coursesPageClass.goToFirstPage();
        int sizeAfterAddingDuplicateCourse = coursesPageClass.checkSize();
        Assert.assertEquals(sizeBeforeAddingDuplicateCourse, sizeAfterAddingDuplicateCourse,
                "Failed! Duplicate course was created!");
        Assert.assertEquals(actualErrorMessage, Config.getValue("expectedDuplicateCourseErrorMessage"),
                "Failed! Duplicate error message is not displayed!");
    }
    @Test
    //16 - w
    public void createCourseDateFormatTest(){
        LoginPageClass loginPageClass = new LoginPageClass();
        CoursesPageClass coursesPageClass = new CoursesPageClass();
        MainPageClass mainPageClass = new MainPageClass();
        Driver.getDriver().get(Config.getValue("url"));
        loginPageClass.login(Config.getValue("adminEmail"), Config.getValue("correctPassword"));
        Flow.wait(2000);
        mainPageClass.coursesSideBarButton.click();
        Flow.wait(1000);
        coursesPageClass.createCourseButton.click();
        Assert.assertEquals(coursesPageClass.dateFormatText.getText(), "dd.mm.yyyy",
        "Failed! The date text format discrepancy found");
    }
}
