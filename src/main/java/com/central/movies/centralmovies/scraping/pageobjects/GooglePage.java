package com.central.movies.centralmovies.scraping.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.central.movies.centralmovies.scraping.utils.Waiter;
import com.github.dockerjava.api.exception.NotFoundException;

public class GooglePage {
    
    private static final String GOOGLE_URL = "https://www.google.com";

    private WebDriver driver;
    private Waiter waiter;

    private By textareaLocator = By.xpath("//textarea[@aria-label='Buscar']");
    private String mainBlockSelector = "//div[@aria-label='{option}']";
    private String allAvailableOptions = "//span[text()='Todas las opciones para ver']";


    //Configs depending on the country set in the server (selenium)
    private List<String> mainBlockSelectorOptions = Arrays.asList("Mirar película", "Mirar ahora");



    public GooglePage(WebDriver driver, Waiter waiter){
        this.driver = driver;
        this.waiter = waiter;
    }

    public void enterSite() {
        driver.get(GOOGLE_URL);
        driver.manage().window().maximize();
    }

    public String getPlatformsForMovie(String query) {
        waiter.waitForElement(textareaLocator);
        WebElement textareaElement = driver.findElement(textareaLocator);
        textareaElement.sendKeys(query);
        textareaElement.sendKeys(Keys.ENTER);

        try {
            By allOptionsBy = By.xpath(mainBlockSelector.replace("{option}", mainBlockSelectorOptions.get(0)) + allAvailableOptions);
            waiter.waitForElement(allOptionsBy);
            WebElement allOptionsElement = driver.findElement(allOptionsBy);
            allOptionsElement.click();
        }catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println("Elemento para desplegar todas las opciones no esta disponible, la ejecución continua.");
        }
        WebElement mainBlockElement;
        try {
            By mainBlockBy = By.xpath(mainBlockSelector.replace("{option}", mainBlockSelectorOptions.get(0)));
            waiter.waitForElement(mainBlockBy);
            mainBlockElement = driver.findElement(mainBlockBy);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            throw new NotFoundException("Elemento no encontrado", e);
        }

        //TO DO - Add escenario when there is only 1 available option as streaming site.
        return mainBlockElement.getText();
   }

}
