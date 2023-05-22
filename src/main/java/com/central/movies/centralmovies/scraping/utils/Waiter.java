package com.central.movies.centralmovies.scraping.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {
	
	private WebDriver driver;
	private WebElement webElement;
	private WebDriverWait elementWaiter;
	
	
	public Waiter(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitToBeClickable(By element) {
		webElement = (new WebDriverWait(driver,5)).until(ExpectedConditions.elementToBeClickable(element));
	} 
	
	public void waitToBeClickable(WebElement element) {
		webElement = (new WebDriverWait(driver,5)).until(ExpectedConditions.elementToBeClickable(element));
	} 
	
	public void waitToBeDisabled(By element){
		new WebDriverWait(driver, 22).until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
	
	public void waitToBeDisabled(List<WebElement> elements){
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public WebElement waitToProcessRequest(By errorWindow, By errorMessage){
		elementWaiter = new WebDriverWait(driver,5);
		elementWaiter.until(ExpectedConditions.invisibilityOfElementWithText(errorMessage, "Establishing Database Connection..."));
		return driver.findElement(errorWindow).findElement(errorMessage);
	}
	
	public void waitForElement(By element) {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.findElement(element);
	}
	
	public void waitForElement(WebElement element) {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		element.isDisplayed();
	}
	
	public boolean isClickable(WebElement element, WebDriver driver){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
	
	public void waitToBeDisplayed(By byElement){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
    }
	
	public void implicitWait() {
		new WebDriverWait(driver, 2);
	}
}