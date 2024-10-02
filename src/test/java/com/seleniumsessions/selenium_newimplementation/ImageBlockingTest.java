package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;

public class ImageBlockingTest {

    private static WebDriver driver;

    @Test
    public void ImageBlockingTest()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--blink-settings=imagesEnabled=false");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
        driver.get("https://www.freepik.com/");
    }

}
