package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.Flow;

import java.util.List;

public class CoursesPageClass {
    public CoursesPageClass(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "//header/button")
    public WebElement createCourseButton;
    @FindBy(xpath = "//form[@id='courseForm']/div/div/img")
    public WebElement uploadPhotoBox;
    @FindBy(xpath = "//form[@id='courseForm']/div/p")
    public WebElement uploadPhotoMessage;
    @FindBy(name = "courseName")
    public WebElement courseNameInputBox;
    @FindBy(xpath = "//form/div[2]/div[2]/div/div/textarea")
    public WebElement descriptionInputBox;
    @FindBy(xpath = "//input[@name='dateOfFinish']")
    public WebElement dateOfFinish;

    @FindBy(xpath = "//form/div[2]/div[1]/div[2]/div/div/div/button")
    public WebElement calendarButton;
    @FindBy(xpath = "(//div[@role='dialog']/div[2]/div/div/div/div/div/button)[1]")
    public WebElement yearDropDownButton;
    @FindBy(xpath = "(//div[@role='presentation'])[9]/button")
    public WebElement yearArrowButton;
    @FindBy(xpath = "//button[@title='Next month']")
    public WebElement nextMonthButton;
    @FindBy(xpath = "//button[@title='Previous month']")
    public WebElement previousMonthButton;
    @FindBy(xpath = "//div[@role='grid']/div[1]/span")
    public List <WebElement> daysOfTheWeek;
    @FindBy(xpath = "//div[@role='row']/button")
    public List<WebElement> days;
    @FindBy(xpath = "//div[@role='dialog']/div[2]/div/div/div/div[2]/div/div/div")
    public List<WebElement> years;
    @FindBy(xpath = "//button[.='Cancel']")
    public WebElement cancelButton;
    @FindBy(xpath = "//button[.='Create']")
    public WebElement createButton;
    @FindBy(xpath = "((//div)[20]/div[1]/div")
    public WebElement courses;

    @FindBy(xpath = "//div/div/button")
    public List<WebElement> threeDotButtons;
    @FindBy(xpath = "(//div/div/button)[1]")
    public WebElement oneCourseThreeDotsButton;
    @FindBy(xpath = "(//div[@data-testid='sentinelStart']/../div[3])[1]/ul/li[2]")
    public WebElement oneCourseEditButton;

    @FindBy(xpath = "//nav/ul/li/button")
    public List<WebElement> pages;

    @FindBy(xpath = "//nav/ul/li/button[@aria-label='Go to next page']")
    public WebElement nextPageButton;

    @FindBy(xpath = "((//header/../div)[2]/div[1]/div/div/div/div)[1]")
    public WebElement coursesName;
    @FindBy(xpath = "((//header/../div)[2]/div[1]/div[1])/div[2]/div/span")
    public WebElement date;
    @FindBy(xpath = "((//header/../div)[2]/div[1]/div[1])/div[2]/p")
    public WebElement description;

    @FindBy(xpath = "//div[@id='root']/div[2]//p")
    public WebElement courseNameLongErrorMessage;

    @FindBy(xpath = "//div[@id='root']/div[2]/div/div/div/div/div/div/p")
    public WebElement duplicateCourseErrorMessage;

    @FindBy(xpath = "//form/div[2]/div[1]/div[2]/div/label")
    public WebElement dateFormatText;
    @FindBy(xpath = "//button[@form='courseForm']")
    public WebElement saveButton;
    @FindBy(xpath = "//div[@id='root']/div[2]//p")
    public WebElement editMessage;
    @FindBy(xpath = "(//div[@data-testid='sentinelStart']/../div[3])[1]/ul/li[1]")
    public WebElement assignTeacherButton;
    @FindBy (xpath = "(//div[@data-testid='sentinelStart']/../div[3])/ul/li[2]")
    public List <WebElement> assignTeacherButtons;
    @FindBy(xpath = "//div[@role='button']")
    public WebElement teacherDropDownButton;
    @FindBy(xpath = "//ul[@role='listbox']/li/span/input")
    public  List <WebElement> teachersDropDownList;

    @FindBy(xpath = "//div[@aria-hidden='true'][2]/div[1]")
    public WebElement randomSpot;
    @FindBy(xpath = "//div[@class='MuiAlert-message css-1xsto0d']/p")
    public WebElement errorMessage;
    @FindBy(xpath = "//button[.='Cancel']")
    public WebElement cancelButtonEdit;
    @FindBy(xpath = "((//header/../div)[2]/div[1]/div/div/div/div)")
    public List <WebElement> coursesNames;


//    @FindBy(xpath = "//form[@id='courseForm']/div[3]/button[2]")
//    public WebElement createButton;
//    @FindBy(xpath = "//form[@id='courseForm']/div[3]/button[1]")
//    public WebElement cancelButton;


    public void createCourse(String courseName, String description,String date){
        Flow.wait(1000);
        createCourseButton.click();
        Flow.wait(1000);
        courseNameInputBox.sendKeys(courseName);
        Flow.wait(3000);
        descriptionInputBox.sendKeys(description);
        Flow.wait(1000);
        dateOfFinish.sendKeys(date);
        Flow.wait(1000);
       if(createButton.isEnabled()){
           createButton.click();
       }else{
           cancelButton.click();
       }
    }
    public int checkSize(){
        int sumOfCourses = 0;
        for (int i =0; i< pages.size(); i++){
        for(WebElement course: coursesNames){
            sumOfCourses++;
        Flow.wait(1000);
        }
        if(nextPageButton.isEnabled()){
        nextPageButton.click();
        Flow.wait(1000);
        }else{
        break;
    }
    }
        return sumOfCourses;
    }
    public void goToFirstPage(){
        for(WebElement page: pages){
            if(page.getText().contains("1")){
                page.click();
                break;
            }
        }
    }

    public void deleteCourse(){
    }
    public void assignTeacher(){
    }

    public void editName(String newName){
        oneCourseThreeDotsButton.click();
        Flow.wait(1000);
        oneCourseEditButton.click();
        Flow.wait(1000);
        courseNameInputBox.click();
        courseNameInputBox.sendKeys(Keys.COMMAND + "a");
        courseNameInputBox.sendKeys(Keys.DELETE);
        Flow.wait(2000);
        courseNameInputBox.sendKeys(newName);
        Flow.wait(1000);
        saveButton.click();
    }
    public void editDescription(String newDescription){
        oneCourseThreeDotsButton.click();
        Flow.wait(1000);
        oneCourseEditButton.click();
        Flow.wait(1000);
        descriptionInputBox.click();
        descriptionInputBox.sendKeys(Keys.COMMAND + "a");
        descriptionInputBox.sendKeys(Keys.DELETE);
        Flow.wait(2000);
        descriptionInputBox.sendKeys(newDescription);
        Flow.wait(1000);
        saveButton.click();
    }
    public void editDate(String newDate){
        oneCourseThreeDotsButton.click();
        Flow.wait(1000);
        oneCourseEditButton.click();
        Flow.wait(1000);
        dateOfFinish.click();
        dateOfFinish.sendKeys(Keys.COMMAND + "a");
        dateOfFinish.sendKeys(Keys.DELETE);
        dateOfFinish.sendKeys(newDate);
        Flow.wait(1000);
        saveButton.click();
    }
}
