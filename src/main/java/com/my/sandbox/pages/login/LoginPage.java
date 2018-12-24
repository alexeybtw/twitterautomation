package com.my.sandbox.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.my.sandbox.pages.BasePage;
import com.my.sandbox.pages.user.MyFeedPage;

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
		this.click(btnLogin);
	}

	public MyFeedPage loginTwitter(String userName, String password) {
		this.enterUserName(userName);
		this.enterPassword(password);
		this.clickLogin();

		return PageFactory.initElements(this.driver, MyFeedPage.class);
	}
}
