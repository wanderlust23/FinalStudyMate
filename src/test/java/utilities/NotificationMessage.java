package utilities;

import org.openqa.selenium.By;

public class NotificationMessage {

    public static String getNotificationMessage(){
        return Driver.getDriver().findElement(By.xpath("(//div[@id='root']/div)[2]//p")).getText();
    }
}
