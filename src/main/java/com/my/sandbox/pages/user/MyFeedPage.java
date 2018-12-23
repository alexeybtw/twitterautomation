package com.my.sandbox.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.my.sandbox.pages.BasePage;

public class MyFeedPage extends BasePage {
	
	@FindBy(xpath = "//span[@class = 'username u-dir']/b[@class = 'u-linkComplex-target']")
	@CacheLookup
	WebElement lnkUserName;

	public MyFeedPage(WebDriver driver) {
		super(driver);
	}
	
	public String getUserName() {
		return this.readText(this.lnkUserName);
	}
}
