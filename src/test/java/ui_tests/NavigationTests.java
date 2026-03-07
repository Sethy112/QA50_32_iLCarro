package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.enums.FooterMenuItem;

public class NavigationTests extends ApplicationManager {


    @Test
    public void iconFacebookNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickIconFooter(FooterMenuItem.ICON_FACEBOOK, "Facebook"));
    }
    @Test
    public void iconTelegramNavigationPositiveTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickIconFooter(FooterMenuItem.ICON_TELEGRAM, "Telegram Messenger"));
    }
    @Test
    public void iconVkNavigationPositiveTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickIconFooter(FooterMenuItem.ICON_VK, "VK | Welcome!"));
    }
    @Test
    public void iconInstaNavigationPositiveTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickIconFooter(FooterMenuItem.ICON_INSTAGRAM, "Instagram"));
    }
    @Test
    public void iconSlackNavigationPositiveTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickIconFooter(FooterMenuItem.ICON_SLACK, "Slack | AI Work Platform & Productivity Tools"));
    }



}
