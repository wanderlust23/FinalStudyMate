package test;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LogInForAllTest;
import pages.StudymateGroupCreation;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

public class AnarBaike {
//    StudymateGroupCreation smgc = new StudymateGroupCreation();
//    LogInForAllTest smlp = new LogInForAllTest();
    Faker faker = new Faker();


    /*

    ANARBEK BAIKE

     */

    @AfterMethod (groups = {"Smoke"})
    public void cleanUP(){
        Driver.quitBrowser();
    }



    @Test (groups = {"Smoke"})
    public void createGroupTest() {
        StudymateGroupCreation smgc = new StudymateGroupCreation();
        LogInForAllTest smlp = new LogInForAllTest();
        Driver.getDriver().get(Config.getValue("studyMateURL"));
        smlp.login(Config.getValue("studyMateLoginEmail"), Config.getValue("studyMateLoginPassword"));
        Flow.wait(2000);
        System.out.println(smgc.getTotalGroups());
        int beforeResult = smgc.getTotalGroups();
        smgc.boxCreation(faker.name().fullName(), "13.03.2023", faker.dog().name());

        Flow.wait(1000);
        System.out.println(smgc.getTotalGroups());
        int afterResult = smgc.getTotalGroups();
        Assert.assertEquals(beforeResult + 1, afterResult, "Group adding failed ");


    }
}
