package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;

public class ChromeExtensionTest {

    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        ChromeOptions options = new ChromeOptions ();
        //Replace the File path : - with your CRX File download path
        options.addExtensions (new File("C:\\SelectorsHub 5.2.9.0.crx"));
       /* options.addExtensions (Arrays.asList(new File("C:\\SelectorsHub 5.2.9.0.crx"),new File("C:\\Load Lazyload Images 0.2022.502.0.crx")));*/
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
    }

    @Test
    public void ChromeExtensionCRXTest() {
        // Navigate to the webpage
        driver.get("https://naveenautomationlabs.com/opencart/");
    }
}
