package com.my.sandbox.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MyFeedPage extends TopNavigationPage {

	@FindBy(xpath = "//span[@class = 'username u-dir']/b[@class = 'u-linkComplex-target']")
	@CacheLookup
	private WebElement lnkUserName;
	
	public MyFeedPage(WebDriver driver) {
		super(driver);
	}

	public String getUserName() {
		return this.readText(this.lnkUserName);
	}
}
