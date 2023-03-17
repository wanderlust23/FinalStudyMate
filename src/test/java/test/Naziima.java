package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LogInForAllTest;
import pages.SideBarPages;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

import java.util.ArrayList;
import java.util.List;

public class Naziima {
    //Nazima
    @AfterMethod (groups = {"Smoke"})
    public void cleanUp(){
        Driver.quitBrowser();
    }


    @Test (groups = {"Smoke"})
    public void SideBarTest(){
        LogInForAllTest smlp = new LogInForAllTest();
        SideBarPages mp = new SideBarPages();


        Driver.getDriver().get(Config.getValue("studyMateURL"));
        smlp.login(Config.getValue("studyMateLoginEmail"),Config.getValue("studyMateLoginPassword"));




        int expectedSideBar = 7;
        List<String> expectedSideBar1 = new ArrayList<>();
        expectedSideBar1.add("Courses");
        expectedSideBar1.add("Announcements");
        expectedSideBar1.add("Trash");
        expectedSideBar1.add("Students");
        expectedSideBar1.add("Groups");
        expectedSideBar1.add("Schedule");
        expectedSideBar1.add("Teachers");


        for (WebElement sideBar : mp.listOfSideBar) {
            String sideBarText = sideBar.getText();
            Assert.assertTrue(expectedSideBar1.contains(sideBarText), "List of sideBar is Failed");


        }
        Assert.assertEquals(expectedSideBar, mp.listOfSideBar.size());


    }



    @Test
    public void clickability(){
        LogInForAllTest smlp = new LogInForAllTest();
        SideBarPages mp = new SideBarPages();

        Driver.getDriver().get(Config.getValue("studyMateURL"));
        smlp.login(Config.getValue("studyMateLoginEmail"), Config.getValue("studyMateLoginPassword"));
        Flow.wait(2000);

        List<String> expectedTrueURL = new ArrayList<>();
        expectedTrueURL.add("https://codewise.studymate.us/admin/groups");
        expectedTrueURL.add("https://codewise.studymate.us/admin/courses?size=6&page=1");
        expectedTrueURL.add("https://codewise.studymate.us/admin/teachers?size=6&page=1");
        expectedTrueURL.add("https://codewise.studymate.us/admin/students?size=6&page=1");
        expectedTrueURL.add("https://codewise.studymate.us/admin/announcements?size=4&page=1");
        expectedTrueURL.add("https://codewise.studymate.us/admin/deleted-items?size=6&page=1");
        expectedTrueURL.add("https://codewise.studymate.us/admin/schedule");

        for (WebElement sideBar : mp.listOfSideBar) {
            sideBar.click();
            Flow.wait(2000);
            String URL = Driver.getDriver().getCurrentUrl();
            Assert.assertTrue(expectedTrueURL.contains(URL), "Clickability FAILED");
            //System.out.println(URL);

        }

    }
    }

