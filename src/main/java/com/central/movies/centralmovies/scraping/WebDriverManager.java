package com.central.movies.centralmovies.utils.scraping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.central.movies.centralmovies.scraping.pageobjects.GooglePage;
import com.central.movies.centralmovies.scraping.utils.Waiter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebDriverManager {

    private WebDriver driver;
    private Waiter waiter;
    
    private static final String GOOGLE_URL = "www.google.com";
    private GooglePage googlePage;
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //esto es para el docker compose:
        //    environment:
        //SE_OPTS: "-headless"

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        waiter = new Waiter(driver);
        googlePage = new GooglePage(driver, waiter);
    }

    public List<String> getListOfPlatforms(String query) {
        this.driver.get(GOOGLE_URL);
        return null;
    }
}
