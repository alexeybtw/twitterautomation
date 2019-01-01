package com.my.sandbox.pages.tweets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.my.sandbox.pages.BasePage;

public class DeleteTweetConfirmationPage extends BasePage {
	
	@FindBy(xpath = "//button[@class = 'EdgeButton EdgeButton--danger delete-action']")
	@CacheLookup
	private WebElement btnDelete;
	
	public DeleteTweetConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	public TweetDeletedPage confirmDeleteTweet() {
		this.click(this.btnDelete);
		return PageFactory.initElements(this.driver, TweetDeletedPage.class);
	}
}
