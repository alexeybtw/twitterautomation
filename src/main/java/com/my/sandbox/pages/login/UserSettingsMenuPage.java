package com.my.sandbox.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.my.sandbox.pages.BasePage;

public class UserSettingsMenuPage extends BasePage {

	@FindBy(xpath = "//button[text()='Log out']")
	@CacheLookup
	WebElement btnLogout;

	public UserSettingsMenuPage(WebDriver driver) {
		super(driver);
	}

	public TwitterHomePage logoutTwitter() {
		this.btnLogout.click();
		return PageFactory.initElements(this.driver, TwitterHomePage.class);
	}
}
