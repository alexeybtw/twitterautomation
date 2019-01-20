package com.my.sandbox.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.my.sandbox.models.tweet.Tweet;
import com.my.sandbox.pages.BasePage;
import com.my.sandbox.pages.login.TwitterHomePage;
import com.my.sandbox.pages.tweets.ComposeNewTweetPage;
import com.my.sandbox.pages.tweets.TweetSentPage;

public class TopNavigationPage extends BasePage {

	@FindBy(xpath = "//a[@id='user-dropdown-toggle']")
	@CacheLookup
	private WebElement btnSettings;

	@FindBy(xpath = "//p[@class = 'name']/span[@class = 'username u-dir u-textTruncate']")
	@CacheLookup
	private WebElement lnkMyUser;

	@FindBy(xpath = "//button[text()='Log out']")
	@CacheLookup
	private WebElement btnLogout;

	@FindBy(id = "global-new-tweet-button")
	@CacheLookup
	private WebElement btnNewTweet;

	public TopNavigationPage(WebDriver driver) {
		super(driver);
	}

	public ComposeNewTweetPage clickNewTweet() {
		this.click(this.btnNewTweet);
		return new ComposeNewTweetPage(driver);
	}
	
	public TweetSentPage publishValidTweet(Tweet tweet) {
		ComposeNewTweetPage pageComposeNewTweet = this.clickNewTweet();
		return pageComposeNewTweet.sendValidTweet(tweet);
	}

	public TwitterHomePage logoutTwitter() {
		this.click(this.btnSettings);

		this.click(this.btnLogout);
		return new TwitterHomePage(driver);
	}
}
