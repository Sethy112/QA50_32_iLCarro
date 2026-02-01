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
    SoftAssert softAssert = new SoftAssert();


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
    public void loginNegativeTest_WrongPassword_WOSpecsymbol() {
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
    public void loginNegativeTest_WrongEmail_Empty() {
        User user = User.builder()
                .email("")
                .password("Password12!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"),
                "validate field email");
//        System.out.println("wrong text!!!");
//        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
//                "validate field password");
//        System.out.println("text is correct");
        softAssert.assertAll();
    }


    @Test
    public void loginPositiveTest_AllIsBlank() {
        User user = User.builder()
                .email("")
                .password("")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"),
                "validate field email");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
                "validate field password");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_WrongPassword_Empty() {
        User user = User.builder()
                .email("123@ail.il")
                .password("")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
//        softAssert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"),
//                "validate field email");
//        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
//                "validate field password");
//        softAssert.assertAll();
        Assert.assertTrue(loginPage.isTextInErrorPresent("Password is required"));
    }
}


