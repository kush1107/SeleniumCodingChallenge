package com.seleniumsessions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class Day22 {
    public static WebDriver driver;
    public String productList,price = null;
    ArrayList<Map<String, String>> productListSort = new ArrayList<>();

    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void VerifyProductListingTest()
    {
        moveToElement("//a[normalize-space()='Mega Menu']","//a[normalize-space()='Apple']");
        waitForSpecificElementVisible("//a[normalize-space()='iPod Touch']");
        getAllProductNames();
        sortProductListByPrice();

    }

    //Utilities methods

    public void moveToElement(String target,String destination)
    {
        WebElement targetElement = new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath(target)));
        WebElement destinationElement = driver.findElement(By.xpath(destination));
        new Actions(driver)
                .moveToElement(targetElement)
                .pause(Duration.ofSeconds(1))
                .click(destinationElement)
                .perform();
    }


    public void waitForSpecificElementVisible(String xpath)
    {
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
    }

    public void getAllProductNames() {
        List<WebElement> productNames = driver.findElements(By.xpath("//h4[@class='title']/a[contains(@class,'text-ellipsis')]"));

        System.out.println("Total No.of Products: "+productNames.size());
        System.out.println("List before Unsorted from Low to High");
        for (WebElement productName : productNames) {
            productList = productName.getText();
            if (productList != null && !productList.isEmpty()) {
                price = driver.findElement(By.xpath("(//h4[@class='title']//a[text()='" + productList + "'])/following::span[@class='price-new']"))
                        .getText();
            }

            System.out.println("Product name is " + productList + " and price is " + price);
            Map<String, String> productDetails = new HashMap<>();
            productDetails.put("productName", productList);
            productDetails.put("productPrice", price);
            productListSort.add(productDetails);
        }
    }

    public void sortProductListByPrice() {
        Collections.sort(productListSort, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                double price1 = Double.parseDouble(o1.get("productPrice").replace(",", "").replace("$", ""));
                double price2 = Double.parseDouble(o2.get("productPrice").replace(",", "").replace("$", ""));
                return Double.compare(price1, price2);
            }
        });

        // Print sorted product list
        System.out.println("##################################################");
        System.out.println("Sorted Product List:");
        for (Map<String, String> product : productListSort) {
            System.out.println("Product: " + product.get("productName") + ", Price: " + product.get("productPrice"));
        }
    }
}
