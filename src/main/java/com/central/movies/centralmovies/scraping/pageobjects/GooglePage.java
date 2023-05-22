package com.central.movies.centralmovies.scraping.pageobjects;

import org.openqa.selenium.WebDriver;

import com.central.movies.centralmovies.scraping.utils.Waiter;

public class GooglePage {

    private WebDriver driver;
    private Waiter waiter;

    public GooglePage(WebDriver driver, Waiter waiter){
        this.driver = driver;
        this.waiter = waiter;
    }

}
