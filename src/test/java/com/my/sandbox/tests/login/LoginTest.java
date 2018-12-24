package com.my.sandbox.tests.login;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.my.sandbox.pages.login.LoginPage;
import com.my.sandbox.pages.login.TwitterHomePage;
import com.my.sandbox.pages.user.MyFeedPage;
import com.my.sandbox.tests.BaseTest;

public class LoginTest extends BaseTest {

	private TwitterHomePage pageHome;
	private MyFeedPage pageMyFeed;

	@Test(priority = 1)
	public void loginTest() {
		this.pageHome = new TwitterHomePage(this.driver).openTwitter();

		LoginPage pageLogin = this.pageHome.openLoginPage();
		this.pageMyFeed = pageLogin.loginTwitter("Jonnny_Doe", "bkxEGLmduZeG4UaiSLQn");

		assertEquals(this.pageMyFeed.getUserName(), "Jonnny_Doe");
		assertEquals(this.pageMyFeed.getPageTitle(), "Twitter");
	}

	@Test(priority = 2, dependsOnMethods = { "loginTest" })
	public void logoutTest() {
		this.pageHome = this.pageMyFeed.clickLogout();

		assertEquals(this.pageHome.getSignupTitle(), "See what’s happening in the world right now");
	}
}
