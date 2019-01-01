package com.my.sandbox.tests.login;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.my.sandbox.models.login.User;
import com.my.sandbox.pages.login.LoginPage;
import com.my.sandbox.pages.login.TwitterHomePage;
import com.my.sandbox.pages.user.MyFeedPage;
import com.my.sandbox.tests.BaseTest;
import com.my.sandbox.utils.ResourceHelper;
import com.my.sandbox.utils.login.LoggedUser;
import com.my.sandbox.utils.login.TwitterLanguage;
import com.poiji.bind.Poiji;

public class LoginTest extends BaseTest {

	private TwitterHomePage pageHome;
	private LoginPage pageLogin;
	private MyFeedPage pageMyFeed;

	private List<User> lstTestedUsers;
	private User testedUser;

	@BeforeClass
	public void classPreparation() {
		this.lstTestedUsers = Poiji.fromExcel(ResourceHelper.getResourceFile("/TestData/Users/Users.xlsx"), User.class);
	}

	@BeforeMethod
	public void methodPreparation() {
		if (LoggedUser.getLoggedUser() == null) {
			this.pageHome = PageFactory.initElements(driver, TwitterHomePage.class).openTwitter();
			this.pageLogin = this.pageHome.openLoginPage();
		}
	}

	@Test(priority = 1)
	public void invalidLogin() {
		this.testedUser = User.getUserByValidity(this.lstTestedUsers, false);
		this.checkValidModel(this.testedUser, "Invalid User was not found!");

		this.pageLogin = this.pageLogin.invalidLogin(this.testedUser, TwitterLanguage.ENGLISH);

		assertEquals(this.pageLogin.getFailedLoginMessage(),
				"The username and password you entered did not match our records. Please double-check and try again.");
	}

	@Test(priority = 2)
	public void validLoginTest() {
		this.testedUser = User.getUserByValidity(this.lstTestedUsers, true);
		this.checkValidModel(this.testedUser, "Valid User was not found!");

		this.pageMyFeed = this.pageLogin.loginTwitter(this.testedUser, TwitterLanguage.ENGLISH);

		assertEquals(this.pageMyFeed.getUserName(), this.testedUser.getUsername());
		assertEquals(this.pageMyFeed.getPageTitle(), "Twitter");
		
		LoggedUser.setLoggedUser(this.testedUser);
	}

	@Test(priority = 3, dependsOnMethods = { "validLoginTest" })
	public void logoutTest() {
		this.pageHome = this.pageMyFeed.logoutTwitter();

		assertEquals(this.pageHome.getSignupTitle(), "See what’s happening in the world right now");
		LoggedUser.disposeLoggedUser();
	}
}
