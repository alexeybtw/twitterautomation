package com.my.sandbox.tests.login;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.my.sandbox.pages.login.LoginPage;
import com.my.sandbox.pages.login.TwitterHomePage;
import com.my.sandbox.pages.user.MyFeedPage;
import com.my.sandbox.tests.BaseTest;

public class LoginTest extends BaseTest {
	
	@Test
	public void loginTest() {
		TwitterHomePage pageHome = new TwitterHomePage(this.driver).openTwitter();
		
		LoginPage pageLogin = pageHome.openLoginPage();
		MyFeedPage pageMyFeed = pageLogin.loginTwitter("Jonnny_Doe", "bkxEGLmduZeG4UaiSLQn");
		
		assertEquals(pageMyFeed.getUserName(), "Jonnny_Doe");
	}
}
