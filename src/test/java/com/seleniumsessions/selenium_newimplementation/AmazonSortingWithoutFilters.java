package com.seleniumsessions.selenium_newimplementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonSortingWithoutFilters {

    public static Logger log = LogManager.getLogger(AmazonSortingWithoutFilters.class);
    public static WebDriver driver;
    public static String URL = "https://www.amazon.in/";

    @BeforeTest
    public void setup() {
        // Initialize your WebDriver here
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver=new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.manage().deleteAllCookies();
        log.info("Launching the browser...");

    }

    @AfterTest
    public void teardown() {
        // Post Steps  - Closure
        log.info("Closing the browser...");
        if (driver!=null) {
            driver.quit();
        }
    }

    @Test
    public void  verify_AmazonProductSorting_WithoutFilters()
    {
        driver.get(URL);
        log.info("Url is launched");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
       WebElement searchBox =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='twotabsearchtextbox']")));
        searchBox.sendKeys("lg soundbar");

        WebElement searchBox_result =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'s-suggestion-ellipsis-direction')])[1]")));
        searchBox_result.click();

        WebElement results_soundbars =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Results']")));
       boolean flag_resultsDisplayed =  results_soundbars.isDisplayed();

       if(flag_resultsDisplayed)
       {
           List<WebElement> list_productName = driver.findElements(By.xpath("//a//span[contains(@class,'a-text-normal')]"));
           List<WebElement> list_productPrice = driver.findElements(By.xpath("//a//span[@class='a-price-whole']"));

           // Map to store product name as key and price as value
           Map<String, Integer> productMap = new HashMap<>();

           for (int i = 0; i < list_productName.size(); i++) {
               String product_name = list_productName.get(i).getText();

               // Ensure product has both name and price
               if (!product_name.isEmpty() && i < list_productPrice.size() && !list_productPrice.get(i).getText().isEmpty()) {
                   String product_price = list_productPrice.get(i).getText().replace(",", ""); // Remove commas from price
                   productMap.put(product_name, Integer.parseInt(product_price)); // Store in map
               }
           }

           // Convert map to a list of entries
           List<Map.Entry<String, Integer>> sortedProducts = new ArrayList<>(productMap.entrySet());

           // Sort the list based on the price (value of the map)
           sortedProducts.sort(Map.Entry.comparingByValue());

           // Print sorted product names and prices
           for (Map.Entry<String, Integer> entry : sortedProducts) {
               System.out.println("--------------------------------------------------------");
               System.out.println("Product: " + entry.getKey() + "\nPrice: " + entry.getValue());
           }
       }
    }
}
