package com.my.sandbox.pages.login;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.my.sandbox.models.login.User;
import com.my.sandbox.pages.BasePage;
import com.my.sandbox.pages.user.MyFeedPage;
import com.my.sandbox.utils.login.TwitterLanguage;

public class LoginPage extends BasePage {
	@FindBy(xpath = "//div[@class = 'clearfix field']/input[@type = 'text']")
	@CacheLookup
	private WebElement txtLogin;

	@FindBy(xpath = "//div[@class = 'clearfix field']/input[@type = 'password']")
	@CacheLookup
	private WebElement txtPassword;

	@FindBy(xpath = "//button[@class = 'submit EdgeButton EdgeButton--primary EdgeButtom--medium']")
	@CacheLookup
	private WebElement btnLogin;

	@FindBy(xpath = "//span[@class = 'message-text']")
	@CacheLookup
	private WebElement lblLoginFailed;

	@FindBy(className = "js-current-language")
	@CacheLookup
	private WebElement drpboxCurrentLang;

	@FindBy(xpath = "//a[@data-lang-code='en']")
	@CacheLookup
	private WebElement lnkEngLang;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterUserName(String userName) {
		this.writeText(this.txtLogin, userName);
	}

	public void enterPassword(String password) {
		this.writeText(this.txtPassword, password);
	}

	public void clickLogin() {
		this.click(this.btnLogin);
	}

	public MyFeedPage loginTwitter(User user, TwitterLanguage twitterLang) {
		this.changeLanguage(twitterLang);
		
		this.enterUserName(user.getUsername());
		this.enterPassword(user.getPassword());
		this.clickLogin();

		return new MyFeedPage(driver);
	}

	public LoginPage invalidLogin(User user, TwitterLanguage twitterLang) {
		this.changeLanguage(twitterLang);
		
		this.enterUserName(user.getUsername());
		this.enterPassword(user.getPassword());
		this.clickLogin();

		return this;
	}

	public String getFailedLoginMessage() {
		return this.readText(this.lblLoginFailed);
	}

	public String getCurrentLang() {
		return this.readText(this.drpboxCurrentLang);
	}
	
	public void changeLanguage(TwitterLanguage twitterLang) {
		if (!this.getCurrentLang().equals(twitterLang.getLanguage())) {
			this.click(this.drpboxCurrentLang);
			
			switch (twitterLang) {
			case ENGLISH:
				this.click(this.lnkEngLang);
				break;
			default:
				throw new NotImplementedException(String.format("Language [%s] is not supported for selection", twitterLang.getLanguage()));
			}
		}
	}
}
