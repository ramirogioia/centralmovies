package com.central.movies.centralmovies.scraping.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.central.movies.centralmovies.scraping.utils.Waiter;

import antlr.collections.impl.LList;

public class GooglePage {

    private WebDriver driver;
    private Waiter waiter;

    private String mainBlockSelector = "//div[@aria-label='{option}']";


    //Configs depending on the country set in the server (selenium)
    private List<String> mainBlockSelectorOptions = Arrays.asList("Mirar película", "Mirar ahora");



    public GooglePage(WebDriver driver, Waiter waiter){
        this.driver = driver;
        this.waiter = waiter;
    }



    public List<String> getPlatformsForMovie(String query) {
        WebElement element = driver.findElement(By.xpath(mainBlockSelector.replace("{option}", mainBlockSelectorOptions.get(0))));
        System.err.println(element);

        return null;
    }

}
