package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class StarsPOC {

    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        /* options.addExtensions (Arrays.asList(new File("C:\\SelectorsHub 5.2.9.0.crx"),new File("C:\\Load Lazyload Images 0.2022.502.0.crx")));*/
        driver = new ChromeDriver(options);
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        driver.get("https://dev.bisonline.com/#/Login");
    }

    @Test
    public void test1()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

       JavascriptExecutor jse = (JavascriptExecutor) driver;

        // Get the element which is currently active in the webpage
        // Execute JavaScript to find the element by XPath and focus on it
        String script = "var element = document.evaluate(arguments[0], document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
                + "if (element) { element.focus(); }";


        // Add some wait or interaction to observe the focus (optional)
        try {
            Thread.sleep(3000); // Wait for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jse.executeScript(script,"//div//input[@data-test-id='login_username']" );
        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-test-id='login_username']")));
        username.sendKeys("staradmin@state");

        jse.executeScript(script,"//div//input[@data-test-id='login_password']");

        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-test-id='login_password']")));
        password.sendKeys("Password1!");


        WebElement btn_Check = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-test-id='login_acceptableUsePolicy']")));
        btn_Check.click();


        jse.executeScript(script,"//button[@data-test-id='login_submitButton']");
        WebElement signIn2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-test-id='login_submitButton']")));
        signIn2.click();

        WebElement newTitleLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-test-id='widgetActions_newTitleRegistrationAction']")));
        newTitleLink.click();

        WebElement inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-test-id='displayVehicleData_formerTitleState']")));
        inputField.sendKeys("AL");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement select_drop = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" (//span[@class='v-autocomplete__mask'])[1]")));
        select_drop.click();


    }

}
