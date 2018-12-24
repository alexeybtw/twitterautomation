package com.my.sandbox.tests.login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.List;

import org.testng.annotations.Test;

import com.my.sandbox.models.login.User;
import com.my.sandbox.pages.login.LoginPage;
import com.my.sandbox.pages.login.TwitterHomePage;
import com.my.sandbox.pages.user.MyFeedPage;
import com.my.sandbox.tests.BaseTest;
import com.my.sandbox.utils.ResourceHelper;
import com.poiji.bind.Poiji;

public class LoginTest extends BaseTest {

	private TwitterHomePage pageHome;
	private MyFeedPage pageMyFeed;

	@Test(priority = 1)
	public void loginTest() {
		this.pageHome = new TwitterHomePage(this.driver).openTwitter();

		LoginPage pageLogin = this.pageHome.openLoginPage();

		List<User> lstTestedUsers = Poiji.fromExcel(ResourceHelper.getResourceFile("/TestData/Users/Users.xlsx"), User.class);
		User testedUser = User.getUserByValidity(lstTestedUsers, true);

		if (testedUser == null)
			fail("Valid User was not found!");

		this.pageMyFeed = pageLogin.loginTwitter(testedUser.getUsername(), testedUser.getPassword());

		assertEquals(this.pageMyFeed.getUserName(), testedUser.getUsername());
		assertEquals(this.pageMyFeed.getPageTitle(), "Twitter");
	}

	@Test(priority = 2, dependsOnMethods = { "loginTest" })
	public void logoutTest() {
		this.pageHome = this.pageMyFeed.clickLogout();

		assertEquals(this.pageHome.getSignupTitle(), "See what’s happening in the world right now");
	}
}
