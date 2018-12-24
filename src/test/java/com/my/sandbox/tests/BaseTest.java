package com.my.sandbox.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	protected WebDriver driver;
	private final int IMPLICIT_WAIT_MS = 10000;

	@BeforeClass
	public void setUp() {
		String chromeDriverPath = BaseTest.class.getResource("/WebDrivers/chromedriver.exe").getPath();
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_MS, TimeUnit.MILLISECONDS);
	}

	@AfterClass
	public void cleanUp() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
