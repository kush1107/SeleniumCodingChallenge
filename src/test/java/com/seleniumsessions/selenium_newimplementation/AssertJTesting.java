package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import java.time.Duration;

public class AssertJTesting {
    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void animationButtonClickTest() {
        // Navigate to the webpage
        driver.get("https://naveenautomationlabs.com/opencart/");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        WebElement menu_text = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Desktops']")));
        String text = menu_text.getText();
        assertThat(text).as("Verify text of Menu link").isEqualToIgnoringCase("Desktops");

    }
}
