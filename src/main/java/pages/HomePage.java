package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;
import utils.enums.FooterMenuItem;

import java.time.Duration;
import java.time.LocalDate;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
//        driver.get("https://ilcarro.web.app/search ");
        driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }


    @FindBy(xpath = "//a[@href='/login?url=%2Fsearch']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[@href='/registration?url=%2Fsearch']")
    WebElement btnSignUp;
    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearCalendar;


    public void clickBtnSignUp() {
        btnSignUp.click();
    }


    public void clickBtnLogin() {
        btnLogin.click();
    }

    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        String dates = startDate.getMonthValue() + "/" + startDate.getDayOfMonth() + "/" + startDate.getYear() + " - " +
                endDate.getMonthValue() + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute(\"disabled\")");
        inputDates.sendKeys(dates);
//        btnYalla.click();
        clickWait(btnYalla, 3);
    }

    public void typeSearchFormWOJS(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        String dates = startDate.getMonthValue() + "/" + startDate.getDayOfMonth() + "/" + startDate.getYear() + " - " +
                endDate.getMonthValue() + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();

        inputDates.sendKeys(dates);

    }

    public void clickBtnYalla() {

        clickWait(btnYalla, 3);
    }

    public void typeSearchFormWithCalendar(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
//        System.out.println(startDate.getMonth());
        typeCalendar(startDate);
        typeCalendar(endDate);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute(\"disabled\")");


    }
    private  String  createMonth (String  month){
        StringBuilder res = new StringBuilder();
        return res.append(month.substring(0,1). toUpperCase())
                .append(month.substring(1).toLowerCase()).toString();

    }




    private void typeCalendar(LocalDate date) {
        btnYearCalendar.click();
        //td[@aria-label='2026'];
        String year =Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement(By.
                xpath("//td[@aria-label='"+year+"']"));
        btnYear.click();
//        td[@aria-label='March 2026']
        String month =  createMonth(date.getMonth().toString());
        WebElement btnMonth = driver.findElement(By.
                xpath("//td[@aria-label='" + month + " " + year + "']"));
        btnMonth.click();
        //td[@aria-label='March 18, 2026']
        //String day = Integer.toString(date.getDayOfMonth());
        String day  = String.valueOf(date.getDayOfMonth());
        WebElement btnDay = driver.findElement(By.
                xpath("//td[@aria-label='" + month + " " + day + ", " + year + "']"));
        btnDay.click();

    }
 public  boolean clickIconFooter(FooterMenuItem item, String  title){
        driver.findElement(By.xpath(item.getLocator())).click();
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleContains(title));

 }
}
