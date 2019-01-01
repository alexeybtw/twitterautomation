package com.my.sandbox.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.my.sandbox.models.BaseModel;
import com.my.sandbox.utils.WebDriverInstance;

public class BaseTest {
	protected volatile static WebDriver driver;

	@BeforeTest
	public void setUp() {
		driver = WebDriverInstance.getWebDriver();
	}

	@AfterTest
	public void cleanUp() {
		WebDriverInstance.closeDriver();
	}

	protected <T extends BaseModel> void checkValidModel(T model, String invalidMessage) {
		if (model == null)
			Assert.fail(invalidMessage);
	}
}
