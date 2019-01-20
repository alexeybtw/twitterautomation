package com.my.sandbox.pages.user;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.my.sandbox.pages.tweets.DeleteTweetConfirmationPage;
import com.my.sandbox.pages.tweets.TweetDeletedPage;
import com.my.sandbox.utils.Consts;

public class MyHomePage extends TopNavigationPage {

	@FindBy(xpath = "//span[@class = 'username u-dir']/b[@class = 'u-linkComplex-target']")
	@CacheLookup
	private WebElement lnkUserName;

	@FindAll({
		@FindBy(xpath = "//div[@data-tweet-id]/div[@class = 'content']")
	})
	private List<WebElement> lstMyTweetsElements;

	@FindBy(xpath = "//li[@class = 'js-actionDelete']/button")
	private WebElement btnDeleteTweet;

	public MyHomePage(WebDriver driver) {
		super(driver);
	}

	public String getUserName() {
		return this.readText(this.lnkUserName);
	}

	public MyHomePage navigateToHomePage(String username) {
		this.navigateToPage(String.format("%s%s", Consts.HOME_PAGE_URL, username));
		return this;
	}

	private WebElement getElemTweetText(WebElement parentElem) {
		return parentElem.findElement(By.xpath("//div[@class = 'js-tweet-text-container']/p"));
	}

	private WebElement getTweetMenuButton(WebElement parentElem) {
		return parentElem.findElement(By.xpath("//div[@data-tweet-id]/div[@class = 'content']/div[1]/div/div"));
	}

	public String getTweetText(int indxTweet) {
		WebElement elemTweet = this.lstMyTweetsElements.get(indxTweet);
		return this.readText(this.getElemTweetText(elemTweet));
	}

	public String getLastTweetText() {
		try {
			WebElement elemTweet = this.lstMyTweetsElements.get(this.lstMyTweetsElements.size() - 1);
			return this.readText(this.getElemTweetText(elemTweet));
		} catch (IndexOutOfBoundsException ex) {
			return "";
		}
	}

	private DeleteTweetConfirmationPage clickDelete() {
		this.click(this.btnDeleteTweet);
		return new DeleteTweetConfirmationPage(driver);
	}

	public TweetDeletedPage deleteTweet(int indxTweet) {
		WebElement btnTweetMenu = this.getTweetMenuButton(this.lstMyTweetsElements.get(indxTweet));
		this.click(btnTweetMenu);

		DeleteTweetConfirmationPage pageDeleteTweet = this.clickDelete();
		return pageDeleteTweet.confirmDeleteTweet();
	}

	public TweetDeletedPage deleteLastTweet() {
		WebElement btnTweetMenu = this.getTweetMenuButton(this.lstMyTweetsElements.get(this.lstMyTweetsElements.size() - 1));
		this.click(btnTweetMenu);

		DeleteTweetConfirmationPage pageDeleteTweet = this.clickDelete();
		return pageDeleteTweet.confirmDeleteTweet();
	}
	
	public int getTweetIndex(String actTweetText) {		
		for (int i = 0; i < this.lstMyTweetsElements.size(); i++) {
			String expTweetText = this.getTweetText(i);
			
			if (expTweetText.equals(actTweetText))
				return i;
		}
		return -1;
	}
}
