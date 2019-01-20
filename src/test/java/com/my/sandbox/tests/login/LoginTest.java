package com.my.sandbox.tests.login;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.my.sandbox.models.login.User;
import com.my.sandbox.pages.login.LoginPage;
import com.my.sandbox.pages.login.TwitterHomePage;
import com.my.sandbox.pages.user.MyFeedPage;
import com.my.sandbox.tests.BaseTest;
import com.my.sandbox.utils.ResourceHelper;
import com.my.sandbox.utils.login.LoggedUser;
import com.my.sandbox.utils.login.TwitterLanguage;
import com.my.sandbox.utils.testdata.TestDataHelper;
import com.poiji.bind.Poiji;

public class LoginTest extends BaseTest {

	private TwitterHomePage pageHome;
	private LoginPage pageLogin;
	private MyFeedPage pageMyFeed;
	
	private List<User> lstInvalidUsers;
	private List<User> lstValidUsers;
	
	@BeforeClass
	public void classPreparation() {
		List<User> lstTestedUsers = Poiji.fromExcel(ResourceHelper.getResourceFile("/TestData/Users/Users.xlsx"), User.class);
		
		this.lstInvalidUsers = User.getUsersByValidity(lstTestedUsers, false);
		this.lstValidUsers = User.getUsersByValidity(lstTestedUsers, true);
	}

	@BeforeMethod
	public void methodPreparation() {
		if (LoggedUser.getLoggedUser() == null) {
			this.pageHome = new TwitterHomePage(driver);
			this.pageLogin = this.pageHome.openLoginPage();
		}
	}

	@Test(priority = 1, dataProvider = "invalidUsers")
	public void invalidLogin(User invalidUser) {
		this.pageLogin = this.pageLogin.invalidLogin(invalidUser, TwitterLanguage.ENGLISH);

		assertEquals(this.pageLogin.getFailedLoginMessage(),
				"The username and password you entered did not match our records. Please double-check and try again.");

		this.pageLogin = this.pageLogin.refreshPage();
	}

	@Test(priority = 2, dataProvider = "validUsers")
	public void validLoginTest(User validUser) {
		this.pageMyFeed = this.pageLogin.loginTwitter(validUser, TwitterLanguage.ENGLISH);

		assertEquals(this.pageMyFeed.getUserName(), validUser.getUsername());
		assertEquals(this.pageMyFeed.getPageTitle(), "Twitter");

		LoggedUser.setLoggedUser(validUser);
	}

	@Test(priority = 3, dependsOnMethods = { "validLoginTest" })
	public void logoutTest() {
		this.pageHome = this.pageMyFeed.logoutTwitter();

		assertEquals(this.pageHome.getSignupTitle(), "See what’s happening in the world right now");
		LoggedUser.disposeLoggedUser();
	}

	@DataProvider(name = "invalidUsers")
	public Object[][] getInvalidUsers() {
		return TestDataHelper.covertObjListToArray(this.lstInvalidUsers);
	}
	
	@DataProvider(name = "validUsers")
	public Object[][] getValidUsers() {
		return TestDataHelper.covertObjListToArray(this.lstValidUsers);
	}
}
