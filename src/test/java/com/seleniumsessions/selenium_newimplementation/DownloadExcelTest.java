package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class DownloadExcelTest {
    public static WebDriver driver;
    //Download path
    public static String downloadFilePath = "C:\\Users\\KushalParikh\\Downloads";
    //Download filename
    public static String fileName = "jdk-22_windows-x64_bin.msi";

    @BeforeTest
    public void setup()
    {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        //Enable safe download - if you have issue while downloading like - "download blocked" or "unverified downlaod b"
       // chromePrefs.put("safebrowsing.enabled", "true");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        //Disable notifications
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
        driver.get("https://testpages.eviltester.com/styled/download/download-via-js.html");

    }
    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void WaitDownloadFileTest()
    {
        // Wait until the download link is clickable
        WebElement downLoadLink = new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@id='direct-download']")));
        downLoadLink.click();
        File file = new File(downloadFilePath,fileName);
        FluentWait<File> wait = new FluentWait<>(file)
                .withTimeout(Duration.ofMinutes(5))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class)
                .withMessage("File is not downloaded completely...");

        boolean  isfileDownloaded = false;
        try {
            isfileDownloaded  = wait.until(f->f.exists() && f.canRead());
        } catch (Exception e) {
            System.out.println("File is not downloaded successfully..");
        }
        if(isfileDownloaded)
        {
            System.out.println("File is downloaded successfully..");
        }
    }
}
