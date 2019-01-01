package com.my.sandbox.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverInstance {

	private volatile static WebDriver webDriver = null;
	private static final int IMPLICIT_WAIT_MS = 10000;

	private WebDriverInstance() {
	}

	public static synchronized WebDriver getWebDriver() {
		if (webDriver == null) {
			String chromeDriverPath = ResourceHelper.getResource("/WebDrivers/chromedriver.exe").getPath();
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);

			webDriver = new ChromeDriver();
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_MS, TimeUnit.MILLISECONDS);
		}
		return webDriver;
	}

	public static synchronized void closeDriver() {
		if (webDriver != null) {
			webDriver.manage().deleteAllCookies();
			webDriver.close();

			webDriver = null;
		}
	}
}
