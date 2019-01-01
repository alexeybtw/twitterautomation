package com.my.sandbox.pages.tweets;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.my.sandbox.models.tweet.Tweet;
import com.my.sandbox.pages.BasePage;

public class ComposeNewTweetPage extends BasePage {

	@FindBy(id = "Tweetstorm-dialog-header")
	@CacheLookup
	private WebElement lblNewTweetHeader;

	@FindBy(xpath = "//div[@aria-labelledby='Tweetstorm-tweet-box-0-label Tweetstorm-tweet-box-0-text-label']")
	@CacheLookup
	private WebElement txtNewTweet;

	public ComposeNewTweetPage(WebDriver driver) {
		super(driver);
	}

	public ComposeNewTweetPage enterTweetText(String tweetText) {
		this.writeText(this.txtNewTweet, tweetText);
		return this;
	}

	public TweetSentPage sendValidTweet(Tweet tweet) {
		this.enterTweetText(tweet.getTweetText());
		this.wait.until(ExpectedConditions.textToBePresentInElement(this.txtNewTweet, tweet.getTweetText()));

		final String SEND_TWEET = Keys.chord(Keys.CONTROL, Keys.ENTER);
		this.enterTweetText(SEND_TWEET);

		return PageFactory.initElements(this.driver, TweetSentPage.class);
	}
}
