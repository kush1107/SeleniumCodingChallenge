package com.seleniumsessions.selenium_30days_challenges;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Collections;

/**
 *
 * Create an test script that perform e-commerce flow -  which includes - creating a customer,adding product to cart
 * and checkout process.
 *
 * */
public class Day21 {

    public static WebDriver driver;

    @BeforeTest
    public void setup()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--headless=new");
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--disable-popup-blocking");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
       options.addArguments("--window-size=720,600");
       options.addArguments("--proxy-server=http://myproxy:8080");

        options.addArguments("user-data-dir=C:/Users/KushalParikh/AppData/Local/Google/Chrome/User Data/");
        options.addArguments("--profile-directory=Profile 2");

        driver = new ChromeDriver(options=options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(35));


        driver.get("https://magento.softwaretestingboard.com/");
    }
    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void ECommerceFlowTest() {


    }


}
