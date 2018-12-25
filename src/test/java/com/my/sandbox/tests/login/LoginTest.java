package com.my.sandbox.tests.login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.my.sandbox.models.login.User;
import com.my.sandbox.pages.login.LoginPage;
import com.my.sandbox.pages.login.TwitterHomePage;
import com.my.sandbox.pages.user.MyFeedPage;
import com.my.sandbox.tests.BaseTest;
import com.my.sandbox.utils.ResourceHelper;
import com.my.sandbox.utils.login.TwitterLanguage;
import com.poiji.bind.Poiji;

public class LoginTest extends BaseTest {

	private TwitterHomePage pageHome;
	private LoginPage pageLogin;
	private MyFeedPage pageMyFeed;

	private List<User> lstTestedUsers;
	private User testedUser;
	private boolean isLogged = false;
	
	@BeforeClass
	public void classPreparation() {
		this.lstTestedUsers = Poiji.fromExcel(ResourceHelper.getResourceFile("/TestData/Users/Users.xlsx"), User.class);
	}

	@BeforeMethod
	public void testPreparation() {
		if (!this.isLogged) {
			this.pageHome = new TwitterHomePage(this.driver).openTwitter();
			this.pageLogin = this.pageHome.openLoginPage();
		}
	}

	@Test(priority = 1)
	public void invalidLogin() {
		this.testedUser = User.getUserByValidity(this.lstTestedUsers, false);

		if (this.testedUser == null)
			fail("User was not found!");

		this.pageLogin = this.pageLogin.invalidLogin(this.testedUser, TwitterLanguage.ENGLISH);

		assertEquals(this.pageLogin.getFailedLoginMessage(),
				"The username and password you entered did not match our records. Please double-check and try again.");
	}

	@Test(priority = 2)
	public void loginTest() {
		this.testedUser = User.getUserByValidity(this.lstTestedUsers, true);

		if (this.testedUser == null)
			fail("User was not found!");

		this.pageMyFeed = this.pageLogin.loginTwitter(this.testedUser, TwitterLanguage.ENGLISH);

		assertEquals(this.pageMyFeed.getUserName(), this.testedUser.getUsername());
		assertEquals(this.pageMyFeed.getPageTitle(), "Twitter");
		
		this.isLogged = true;
	}

	@Test(priority = 3, dependsOnMethods = { "loginTest" })
	public void logoutTest() {
		this.pageHome = this.pageMyFeed.logoutTwitter();

		assertEquals(this.pageHome.getSignupTitle(), "See what’s happening in the world right now");
		this.isLogged = false;
	}
}
