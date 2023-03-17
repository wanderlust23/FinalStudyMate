package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LogInForAllTest;
import pages.StudyMateLogoutPage;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;




public class AyilchyBaike {

    @AfterMethod (groups = {"Smoke"})
    public void cleanUp(){
        Driver.quitBrowser();
    }

    @Test (groups = {"Smoke"})
    public void logoutPageTest() {
        StudyMateLogoutPage logoutPage = new StudyMateLogoutPage();
        LogInForAllTest smlp = new LogInForAllTest();
        Driver.getDriver().get("https://codewise.studymate.us/");
        smlp.login("studymate@gmail.com", "123123");
        Flow.wait(3000);
        String expectedURLAfterLogOut = "https://codewise.studymate.us/login";
        logoutPage.logout();
        String actualURLAfterLogOut = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(actualURLAfterLogOut, expectedURLAfterLogOut, "Logout test FAILED");

    }

}
