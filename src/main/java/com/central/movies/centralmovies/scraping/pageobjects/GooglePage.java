package com.central.movies.centralmovies.scraping.pageobjects;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.central.movies.centralmovies.scraping.utils.Waiter;

public class GooglePage {

    private static final String GOOGLE_URL = "https://www.google.com";

    private WebDriver driver;
    private Waiter waiter;

    private By textareaLocator = By.xpath("//textarea[@aria-label='Buscar']");
    private String mainBlockSelector = "(//div[text()='{option}'])[last()]/ancestor::div[5]";
    private String allAvailableOptions = "//span[text()='Todas las opciones para ver']";
    private String uniqueOptionFound = "(//div[text()='{option}'])[last()]/ancestor::div[2]/parent::div";

    private List<String> mainBlockSelectorOptions = Arrays.asList("Mirar película", "Mirar programa", "Mirar ahora");
    private String mainBlockSelectorOption = "Mirar ahora";

    public GooglePage(WebDriver driver, Waiter waiter) {
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
        String textFound = findFirstVisibleText(driver, this.mainBlockSelectorOptions);

        try {
            By allOptionsBy = By.xpath(mainBlockSelector.replace("{option}", textFound) + allAvailableOptions);
            waiter.waitForElement(allOptionsBy);
            WebElement allOptionsElement = driver.findElement(allOptionsBy);
            allOptionsElement.click();
        } catch (Exception e) {
            System.out.println("Elemento para desplegar todas las opciones no esta disponible, la ejecución continua.");
        }
        WebElement mainBlockElement;
        try {
            Thread.sleep(1000);
            By mainBlockBy = By.xpath(mainBlockSelector.replace("{option}", textFound));
            waiter.waitForElement(mainBlockBy);
            mainBlockElement = driver.findElement(mainBlockBy);
        } catch (Exception e) {
            throw new NoSuchElementException("Elemento no encontrado");
        }

        String finalScrappedText = mainBlockElement.getText();

        if (finalScrappedText.split("\\r?\\n").length < 7) {
            try {
                By uniqueOptionBy = By.xpath(uniqueOptionFound.replace("{option}", mainBlockSelectorOption));
                waiter.waitForElement(uniqueOptionBy);
                mainBlockElement = driver.findElement(uniqueOptionBy);
                WebElement linkElement = mainBlockElement.findElement(By.tagName("a"));
                finalScrappedText = linkElement.getAttribute("href");
            } catch (Exception e) {
                throw new NoSuchElementException("Elemento no encontrado");
            }
        }
        return finalScrappedText;
    }


    private static String findFirstVisibleText(WebDriver driver, List<String> textsToCheck) {
        for (String text : textsToCheck) {
            List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    return text;
                }
            }
        }
        return null;
    }

}
