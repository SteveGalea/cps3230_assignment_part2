package com.task1_screenscraper.pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {
    public WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }
}
