package utilities;

import org.openqa.selenium.By;

public class Pagination {

    public static int numberOfItems(){
        String resultStr = Driver.getDriver().findElement(By.xpath("(//nav[@aria-label='pagination navigation']/../div/p)[3]")).getText();
        int result = Integer.parseInt(resultStr.substring(16));
        return result;
    }

}
