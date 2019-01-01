package com.my.sandbox.pages.tweets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.my.sandbox.pages.BasePage;

public class TweetDeletedPage extends BasePage {

	@FindBy(xpath = "//div[@class='message']/div[@class='message-inside']/span[@class='message-text']")
	@CacheLookup
	private WebElement lblTweetDeleted;

	public TweetDeletedPage(WebDriver driver) {
		super(driver);
	}

	public String getTweetDeletedMessage() {
		return this.readText(this.lblTweetDeleted);
	}

	public WebElement getLblTweetDeleted() {
		return lblTweetDeleted;
	}
}
