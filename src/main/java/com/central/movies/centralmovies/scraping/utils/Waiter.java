package com.central.movies.centralmovies.scraping.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {
	
	private WebDriver driver;
	private WebElement webElement;
	private WebDriverWait elementWaiter;
	private static int TIMEOUT = 2;
	
	
	public Waiter(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForElement(By element) {
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.findElement(element);
	}
	
	public void waitForElement(WebElement element) {
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		element.isDisplayed();
	}
}