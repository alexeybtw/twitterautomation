package com.my.sandbox.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.my.sandbox.pages.BasePage;
import com.my.sandbox.pages.login.TwitterHomePage;

public class MyFeedPage extends BasePage {

	@FindBy(xpath = "//span[@class = 'username u-dir']/b[@class = 'u-linkComplex-target']")
	@CacheLookup
	WebElement lnkUserName;

	@FindBy(xpath = "//a[@id='user-dropdown-toggle']")
	@CacheLookup
	WebElement btnSettings;

	@FindBy(xpath = "//button[text()='Log out']")
	@CacheLookup
	WebElement btnLogout;

	public MyFeedPage(WebDriver driver) {
		super(driver);
	}

	public String getUserName() {
		return this.readText(this.lnkUserName);
	}

	public TwitterHomePage logoutTwitter() {
		this.click(this.btnSettings);

		this.btnLogout.click();
		return PageFactory.initElements(this.driver, TwitterHomePage.class);
	}
}
