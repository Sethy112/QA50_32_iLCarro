package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import static utils.UserFactory.*;

import java.util.Random;

public class RegistrationTests extends ApplicationManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void reristrationPositiveTest() {
        int i = new Random().nextInt(1000);
        User user = User.builder()
                .firstName("aaaaaa")
                .lastName("Popov")
                .email("sazx" + i + "@co.il")
                .password("Parol123@!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
//        Assert.assertTrue(registrationPage.isTextInPopupPresent(
//                "You are logged in success"));
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }


    @Test
    public void reristrationPositiveTest_WithFaker() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationNegativeTest_UserAlreadyExists() {
        User user = User.builder()
                .firstName("fgfgfgf")
                .lastName("trrjtj")
                .email("vasya@yohoo.com")
                .password("Parol123@!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("User already exists"));


    }

    @Test
    public void registrationNegativeTest_WithSpaceInfirstName() {
        User user = User.builder()
                .firstName(" ")
                .lastName("trrjtj")
                .email("vasya@yohoo.com")
                .password("Parol123@!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).
                isTextInPopUpMessagePresent("{\"firstName\":\"must not be blank\"}"));
    }

    @Test
    public void registrationNegativeTest_WithllEmptyField() {
        User user = User.builder()
                .firstName("")
                .lastName("")
                .email("")
                .password("")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Name is required"),
                "validation error message Name is required");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Last name is required"),
                "validation error message Last name is required");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Email is required"),
                "validation error message Email is required");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Password is required"),
                "validation error message Password is required");
        softAssert.assertAll();

    }

    @Test
    public void registrationNegativeTest_WithFirstNameEmpty() {
        User user = User.builder()
                .firstName("")
                .lastName("aaaa")
                .email("vasya@yohoo.com")
                .password("Parol12!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Name is required"));

    }
    @Test
    public void registrationNegativeTest_WithLastNameEmpty() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("")
                .email("vasya@yohoo.com")
                .password("Parol12!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Last name is required"));

    }
    @Test
    public void registrationNegativeTest_WithEmailEmpty() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("")
                .password("Parol12!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Email is required"));

    }
    @Test
    public void registrationNegativeTest_WithPasswordISEmpty() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("vasya@yohoo.com")
                .password("")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Password is required"));

    }

    @Test
    public void registrationNegativeTest_WithPasswordEmpty() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("Email is required")
                .password("Parol12!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Wrong email format"));

    }
    @Test
    public void registrationNegativeTest_PasswordWOUppercaseLetter() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("vasya@yohoo.com")
                .password("parol12!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Password must contain 1 uppercase letter, " +
                        "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));

    }
    @Test
    public void registrationNegativeTest_PasswordWOLowercaseLetter() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("vasya@yohoo.com")
                .password("PAROL12!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Password must contain 1 uppercase letter, " +
                        "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));

    }
    @Test
    public void registrationNegativeTest_PasswordWOSpecialSymbol() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("vasya@yohoo.com")
                .password("Paarol12")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Password must contain 1 uppercase letter, " +
                        "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));

    }
    @Test
    public void registrationNegativeTest_PasswordWONumber() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("vasya@yohoo.com")
                .password("Paarool!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Password must contain 1 uppercase letter, " +
                        "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));

    }
    @Test
    public void registrationNegativeTest_PassworLongLessEihgt() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("vasya@yohoo.com")
                .password("Parol1!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Password must contain minimum 8 symbols"));

    }
    @Test
    public void registrationNegativeTest_PassworLongMoreSixsteen() {
        User user = User.builder()
                .firstName("ppppp")
                .lastName("aaaa")
                .email("vasya@yohoo.com")
                .password("Parol1!Parol1!Parol1!" +
                        "Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!" +
                        "Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!" +
                        "Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!" +
                        "Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!" +
                        "Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!" +
                        "Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!" +
                        "Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!Parol1!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
//        Assert.assertTrue(registrationPage
//                .isTextInErrorPresent("413 symbols and it steel work)))"));

    }
}

