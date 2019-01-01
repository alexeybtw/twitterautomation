package com.my.sandbox.tests.tweets;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.my.sandbox.models.tweet.Tweet;
import com.my.sandbox.pages.tweets.TweetDeletedPage;
import com.my.sandbox.pages.tweets.TweetSentPage;
import com.my.sandbox.pages.user.MyHomePage;
import com.my.sandbox.pages.user.TopNavigationPage;
import com.my.sandbox.tests.BaseTest;
import com.my.sandbox.utils.ResourceHelper;
import com.my.sandbox.utils.login.LoggedUser;
import com.poiji.bind.Poiji;

public class TweetsTest extends BaseTest {

	private TopNavigationPage pageTopNavigation;
	private MyHomePage pageMyHome;
	private List<Tweet> lstTweets;
	private Tweet testedTweet;

	@BeforeTest
	public void testPreparation() {
		this.lstTweets = Poiji.fromExcel(ResourceHelper.getResourceFile("/TestData/Tweets/Tweets.xlsx"), Tweet.class);
		this.pageTopNavigation = PageFactory.initElements(driver, TopNavigationPage.class);
		this.pageMyHome = PageFactory.initElements(driver, MyHomePage.class);
	}

	@Test(priority = 4, dependsOnMethods = { "com.my.sandbox.tests.login.LoginTest.validLoginTest" })
	public void publishValidTweet() {
		this.testedTweet = Tweet.getObjectById(this.lstTweets, 0);
		this.checkValidModel(this.testedTweet, "Tweet was not found");

		TweetSentPage pageTweetSent = this.pageTopNavigation.publishValidTweet(this.testedTweet);
		assertEquals(pageTweetSent.getTweetSentMessage(), "Your Tweet was sent.");

		pageTweetSent.waitInvisibility(pageTweetSent.getLblTweetSent());

		this.pageMyHome = this.pageMyHome.navigateToHomePage(LoggedUser.getLoggedUser().getUsername());
		assertEquals(this.pageMyHome.getLastTweetText(), this.testedTweet.getTweetText());
	}

	@Test(priority = 5, dependsOnMethods = { "publishValidTweet" })
	public void deleteLastPublishedTweet() {
		if (this.testedTweet.isValid()) {
			TweetDeletedPage pageTweetDeleted = this.pageMyHome.deleteLastTweet();
			assertEquals(pageTweetDeleted.getTweetDeletedMessage(), "Your Tweet has been deleted.");

			pageTweetDeleted.waitInvisibility(pageTweetDeleted.getLblTweetDeleted());
		}

		assertEquals(this.pageMyHome.verifyTweetExists(this.testedTweet.getTweetText()), false);
	}
}
