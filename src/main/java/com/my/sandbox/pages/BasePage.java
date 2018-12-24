package com.my.sandbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	private final int WEBDRIVER_WAIT_SEC = 10;

	public BasePage(WebDriver driver) {
		super();
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, WEBDRIVER_WAIT_SEC);
	}

	private void waitVisibility(WebElement webElement) {
		this.wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void click(WebElement webElement) {
		this.waitVisibility(webElement);
		webElement.click();
	}

	public void writeText(WebElement webElement, String text) {
		this.waitVisibility(webElement);
		webElement.sendKeys(text);
	}

	public String readText(WebElement webElement) {
		this.waitVisibility(webElement);
		return webElement.getText();
	}

	public String getPageTitle() {
		return this.driver.getTitle();
	}
}
