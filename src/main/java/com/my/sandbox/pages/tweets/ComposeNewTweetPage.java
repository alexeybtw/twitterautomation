package com.my.sandbox.pages.tweets;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
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

	private final String SEND_TWEET = Keys.chord(Keys.CONTROL, Keys.ENTER);

	public ComposeNewTweetPage(WebDriver driver) {
		super(driver);
	}

	public TweetSentPage sendValidTweet(Tweet tweet) {
		this.writeText(this.txtNewTweet, tweet.getTweetText());
		this.wait.until(ExpectedConditions.textToBePresentInElement(this.txtNewTweet, tweet.getTweetText()));

		this.writeText(this.txtNewTweet, SEND_TWEET);

		return new TweetSentPage(driver);
	}
}
