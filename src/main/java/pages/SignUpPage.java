package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(
                driver, 10), this);
    }


    @FindBy(xpath = "//label[@for='name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@id='lastName']")
    WebElement inputLastName;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement checkBox;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;
    @FindBy(xpath = "//h2[text()='You are logged in success']")
    WebElement popUpYouAreLoggedInSuccess;

    public void typeRegistrationForm(User user) {
//        pausa(3);
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());

    }

    public void clickCheckBox() {
       checkBox.click();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public void clickLabelFirstName() {
        inputName.click();
    }

    public boolean isYouAreLoggedInSuccessDisplaed(WebElement element){
        return isYouAreLoggedInSuccessDisplaed(popUpYouAreLoggedInSuccess);
    }
}

