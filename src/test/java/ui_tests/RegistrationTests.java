package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.Random;

public class RegistrationTests extends ApplicationManager {
    RegistrationPage registrationPage;

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
        Assert.assertTrue(registrationPage.isTextInPopupPresent(
                "You are logged in success"));
    }
}
