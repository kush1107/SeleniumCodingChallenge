package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class InsecureDownloadFileTest {

    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        //To handle insecure file download warning -pop-up in Selenium
        // Use chrome options - "--unsafely-treat-insecure-origin-as-secure= <Your Domain Name>"
        options.addArguments("--unsafely-treat-insecure-origin-as-secure=https://automationexercise.com/");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
    }

    @Test
    public void InsecureDownloadFileTest() {
        driver.get("https://automationexercise.com/");

        //Code to handle file download
    }
}
