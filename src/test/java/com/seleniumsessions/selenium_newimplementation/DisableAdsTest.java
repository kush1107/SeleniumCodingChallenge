package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Set;

public class DisableAdsTest {

    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addExtensions (new File("C:\\Stands AdBlocker 1.574.0.0.crx"));
        /* options.addExtensions (Arrays.asList(new File("C:\\SelectorsHub 5.2.9.0.crx"),new File("C:\\Load Lazyload Images 0.2022.502.0.crx")));*/
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
    }

    @Test
    public void DisableAdsTest() {
        // Navigate to the webpage
        String mainHandle = driver.getWindowHandle();
        driver.get("https://automationexercise.com/");
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();

        // Iterate over all window handles
        for (String handle : windowHandles) {
            // Switch to each window/tab
            driver.switchTo().window(handle);
            if (!driver.getTitle().equals("Automation Exercise")) {
                driver.close();
                try {
                    Thread.sleep(2000);// Close the tab
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Switch back to the original window
        driver.switchTo().window(mainHandle);
        try {
            Thread.sleep(500);// Close the tab
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement productView = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'View Product')])[1]")));
        productView.click();

        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add to cart']")));
        addToCart.click();

        WebElement addedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Added!']")));
        if (addedMsg.isDisplayed())
        {
            WebElement viewProductCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='View Cart']")));
            viewProductCart.click();
            WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Proceed To Checkout']")));
            checkout.click();
        }

    }
}
