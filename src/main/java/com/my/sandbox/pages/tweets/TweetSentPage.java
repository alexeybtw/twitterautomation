package com.my.sandbox.pages.tweets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.my.sandbox.pages.BasePage;

public class TweetSentPage extends BasePage {

	@FindBy(xpath = "//div[@class='message']/div[@class='message-inside']/span[@class='message-text']")
	@CacheLookup
	private WebElement lblTweetSent;

	public TweetSentPage(WebDriver driver) {
		super(driver);
	}

	public String getTweetSentMessage() {
		return this.lblTweetSent.getText();
	}

	public WebElement getLblTweetSent() {
		return lblTweetSent;
	}
}
