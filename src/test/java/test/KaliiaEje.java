package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.StudyMateLoginPage;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

import java.util.ArrayList;
import java.util.List;

public class KaliiaEje {


    /*
    KALIIA
     */

    @AfterMethod (groups = {"Smoke"})
    public void cleanUp(){
        Driver.quitBrowser();
    }



    @Test (groups = {"Smoke"})
    public void positiveLoginTest() {
        StudyMateLoginPage smlp = new StudyMateLoginPage();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        smlp.emailInputBox.sendKeys(Config.getValue("studyMateLoginEmail"));
        smlp.passwordInputBox.sendKeys(Config.getValue("studyMateLoginPassword"));
        smlp.loginButton.click();
        Flow.wait(500);
        String expectedURL = "https://codewise.studymate.us/admin";
        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualURL.contains(expectedURL));
    }

    @Test
    public void languageOptions() {
        StudyMateLoginPage smlp = new StudyMateLoginPage();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        smlp.languageDropdownBtn.click();
        Flow.wait(300);
        List<String> expectedLanguageOptions = new ArrayList<>();
        expectedLanguageOptions.add("Русский");
        expectedLanguageOptions.add("English");


        Assert.assertEquals(2, smlp.languageOptions.size());

        for (WebElement languageOption : smlp.languageOptions) {
            Assert.assertTrue(expectedLanguageOptions.contains(languageOption.getText()), "language options failed: " + languageOption.getText());
        }

    }
}
