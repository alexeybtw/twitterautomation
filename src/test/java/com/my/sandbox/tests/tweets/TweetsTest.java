package com.my.sandbox.tests.tweets;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.my.sandbox.models.tweet.Tweet;
import com.my.sandbox.pages.tweets.TweetDeletedPage;
import com.my.sandbox.pages.tweets.TweetSentPage;
import com.my.sandbox.pages.user.MyHomePage;
import com.my.sandbox.pages.user.TopNavigationPage;
import com.my.sandbox.tests.BaseTest;
import com.my.sandbox.utils.ResourceHelper;
import com.my.sandbox.utils.login.LoggedUser;
import com.my.sandbox.utils.testdata.TestDataHelper;
import com.poiji.bind.Poiji;

public class TweetsTest extends BaseTest {

	private MyHomePage pageMyHome;
	private List<Tweet> lstTweets;

	@BeforeClass
	public void classPreparation() {
		this.lstTweets = Poiji.fromExcel(ResourceHelper.getResourceFile("/TestData/Tweets/Tweets.xlsx"), Tweet.class);
		
		this.pageMyHome = new MyHomePage(driver);
		this.pageMyHome = this.pageMyHome.navigateToHomePage(LoggedUser.getLoggedUser().getUsername());
	}

	@Test(priority = 4, dependsOnMethods = {"com.my.sandbox.tests.login.LoginTest.validLoginTest" }, dataProvider = "validTweets")
	public void publishValidTweet(Tweet validTweet) {
		TopNavigationPage pageTopNavigation = new TopNavigationPage(driver);
		
		TweetSentPage pageTweetSent = pageTopNavigation.publishValidTweet(validTweet);
		assertEquals(pageTweetSent.getTweetSentMessage(), "Your Tweet was sent.");

		pageTweetSent.waitInvisibility(pageTweetSent.getLblTweetSent());

		this.pageMyHome = this.pageMyHome.refreshPage();
		assertEquals(this.pageMyHome.getLastTweetText(), validTweet.getTweetText());
	}

	@Test(priority = 5, dependsOnMethods = { "publishValidTweet" }, dataProvider = "validTweets")
	public void deleteTweet(Tweet validTweet) {
		int indxTweet = this.pageMyHome.getTweetIndex(validTweet.getTweetText());
		
		TweetDeletedPage pageTweetDeleted = this.pageMyHome.deleteTweet(indxTweet);
		assertEquals(pageTweetDeleted.getTweetDeletedMessage(), "Your Tweet has been deleted.");

		pageTweetDeleted.waitInvisibility(pageTweetDeleted.getLblTweetDeleted());

		this.pageMyHome = this.pageMyHome.refreshPage();
		assertEquals(this.pageMyHome.getTweetIndex(validTweet.getTweetText()), -1);
	}

	@DataProvider(name = "validTweets")
	public Object[][] getValidTweets() {
		return TestDataHelper.covertObjListToArray(this.lstTweets);
	}
}
