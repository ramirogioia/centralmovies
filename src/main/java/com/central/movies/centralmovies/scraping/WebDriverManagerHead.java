package com.central.movies.centralmovies.scraping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.central.movies.centralmovies.scraping.pageobjects.GooglePage;
import com.central.movies.centralmovies.scraping.utils.Waiter;
import org.openqa.selenium.chrome.ChromeDriverService;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;


public class WebDriverManagerHead {

    private WebDriver driver;
    private Waiter waiter;
    private GooglePage googlePage;
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public WebDriverManagerHead(Boolean isCloudExecuted){
        if(isCloudExecuted){
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
        }else{
            ChromeOptions options = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            options.addArguments("--headless");
            driver = new ChromeDriver(service, options);
        }
        waiter = new Waiter(driver);
        googlePage = new GooglePage(driver, waiter);
    }

    public String getPlatformsInformationFromScrapping(String query, Boolean keepActive){
        String scrappedPlatforms;

        try{
            googlePage.enterSite();
            scrappedPlatforms = googlePage.getPlatformsForMovie(query);
        }catch (NoSuchElementException e){
            driver.close();
            driver.quit();
            throw e;
        }
        
        if(!keepActive){
            driver.close();
            driver.quit();
        }
        return scrappedPlatforms;
    }
}
