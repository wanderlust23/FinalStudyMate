package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

public class MeerimEje {
    /*
    Meerim eje
     */

//    LoginPage lp = new LoginPage();
@AfterMethod (groups = {"Smoke"})
public void cleanUp(){
    Driver.quitBrowser();
}

    @Test (groups = {"Smoke"})
    public void negativeLoginWithEmptyUsernameAndEmptyPassword() {
        LoginPage lp = new LoginPage();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        Flow.wait(2000);
        lp.login("", "");
        lp.loginButton.click();
        String expectedMessageEmail = "Email is required!";
        String expectedMessagePassword = "Password is required!";
        Flow.wait(2000);
        Assert.assertEquals(expectedMessageEmail, lp.emailErrorMessage.getText());
        Assert.assertEquals(expectedMessagePassword, lp.passwordErrorMessage.getText());

    }

    @Test
    public void negativeLoginWithValidUsernameAndEmptyPassword() {
        LoginPage lp = new LoginPage();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        lp.login("studymate@gmail.com", "");
        lp.loginButton.click();
        String expectedMessagePassword = "Password is required!";
        Flow.wait(1000);
        Assert.assertTrue(expectedMessagePassword.contains(lp.passwordErrorMessage.getText()));

    }

    @Test
    public void negativeLoginWithEmptyUsernameAndValidPassword() {
        LoginPage lp = new LoginPage();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        Flow.wait(2000);
        lp.login("", "123123");
        lp.loginButton.click();
        String expectedMessageEmail = "Email is required!";
        Flow.wait(1000);
        Assert.assertTrue(expectedMessageEmail.contains(lp.emailErrorMessage.getText()));


    }

    @Test
    public void negativeLoginWithInvalidEmailAndInvalidPassword() {
        LoginPage lp = new LoginPage();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        Flow.wait(2000);
        lp.login("incorect@gmail.com", "");
        lp.loginButton.click();
        String expectedMessagePassword = "Password is required!";
        Assert.assertEquals(expectedMessagePassword, lp.passwordErrorMessage.getText());
    }

    @Test
    public void negativeLoginWithInvalidEmailAndInvalidPassword2() {
        LoginPage lp = new LoginPage();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        Flow.wait(2000);
        lp.login("meerim@gmail.com", "654321");
        lp.loginButton.click();
        String expectedMessage = "User with email meerim@gmail.com not found";
        System.out.println(lp.notfound.getText());
        Assert.assertEquals(expectedMessage, lp.notfound.getText(), "Message doesn't match!");
    }


}
