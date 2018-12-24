package com.my.sandbox.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.my.sandbox.pages.BasePage;

public class TwitterHomePage extends BasePage {

	private final String HOME_PAGE_URL = "https://twitter.com/";
	private final String LOGIN_PAGE_URL = "https://twitter.com/login";

	@FindBy(xpath = "//div[@class = 'StaticLoggedOutHomePage-buttons']/a[@href='/login']")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(xpath = "//h1[@class='StaticLoggedOutHomePage-signupTitle']")
	@CacheLookup
	WebElement lblSignupTitle;

	public TwitterHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public TwitterHomePage openTwitter() {
		this.driver.navigate().to(HOME_PAGE_URL);
		return this;
	}

	public LoginPage openLoginPage() {
		this.driver.navigate().to(LOGIN_PAGE_URL);
		return PageFactory.initElements(this.driver, LoginPage.class);
	}

	public String getSignupTitle() {
		return this.readText(this.lblSignupTitle);
	}
}
