package com.central.movies.centralmovies.scraping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.central.movies.centralmovies.scraping.pageobjects.GooglePage;
import com.central.movies.centralmovies.scraping.utils.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class WebDriverManagerHead {

    private WebDriver driver;
    private Waiter waiter;
    
    private static final String GOOGLE_URL = "www.google.com";
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
            waiter = new Waiter(driver);
            googlePage = new GooglePage(driver, waiter);
        }else{
            ChromeOptions options = new ChromeOptions();
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            //options.addArguments("--headless");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver(service, options);
        }
    }

    public List<String> getListOfPlatforms(String query, Boolean keepActive) {
        driver.get(GOOGLE_URL);
        
        List<String> scrappedPlatforms = googlePage.getPlatformsForMovie(query);

        if(!keepActive){
            driver.close();
            driver.quit();
        }

        return scrappedPlatforms;
    }
}
