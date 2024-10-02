package com.seleniumsessions.selenium_newimplementation;

import com.github.javafaker.Faker;
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
import java.util.List;

public class BrandValues_ForEachLoop {

    public static WebDriver driver;
    public static Faker faker = new Faker();

    @BeforeTest
    public void setup()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://brandcalue.static.domains/");
    }
    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void brandValuesForEachLoopTest() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Automation -Challenge - Brands Value']")));
        List<WebElement> brandValues_Fields = driver.findElements(By.xpath("//input[contains(@id,'brandValue')]"));
        WebElement exclude_brand = driver.findElement(By.xpath("//input[@name='brandmax']"));
        for (WebElement brandValue : brandValues_Fields) {
            if(!brandValue.equals(exclude_brand))
            {
                brandValue.sendKeys(faker.company().name());
            }
        }

    }
}
