package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {

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

//    @Test
//    public void registrationPositiveTest() {
//        int i = new Random().nextInt(1000);
//        User user = User.builder()
//                .firstName("Vasiaa")
//                .lastName("Popov")
//                .email("qa32" + i + "@mail.il")
//                .password("Password12!")
//                .build();
//        HomePage homePage = new HomePage(getDriver());
//        homePage.clickBtnSignUp();
//        SignUpPage signUp = new SignUpPage(getDriver());
//        signUp.clickLabelFirstName();
//        signUp.clickCheckBox();
//        signUp.typeRegistrationForm(user);
//
//        signUp.clickBtnYalla();
//        Assert.assertTrue(signUp.isYouAreLoggedInSuccessDisplaed());


    }


