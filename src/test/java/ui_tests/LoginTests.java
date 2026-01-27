package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;

public class LoginTests extends ApplicationManager {
SoftAssert softAssert= new SoftAssert();


    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .email("123@mail.il")
                .password("Password12!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());

    }


    @Test
    public void loginPositiveTest_WithPopupPage() {
        User user = User.builder()
                .email("123@mail.il")
                .password("Password12!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Logged is success"));

    }
    @Test
    public void loginPositiveTest_WrongPassword_WOSpecsymbol() {
        User user = User.builder()
                .email("123@mail.il")
                .password("Password12")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Login or Password incorrect"));

    }
    @Test
    public void loginPositiveTest_WrongEmail_Empty() {
        User user = User.builder()
                .email("123ail.il")
                .password("")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"),
                "validate field email");
        System.out.println("wrong text!!!");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
                "validate field password");
        System.out.println("text is correct");
        softAssert.assertAll();
    }
}


