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


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.Set;

  /*Day-13 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐝𝐨𝐰𝐧𝐥𝐨𝐚𝐝𝐬 𝐭𝐡𝐞 𝐩𝐝𝐟 𝐟𝐢𝐥𝐞 𝐚𝐧𝐝 𝐬𝐭𝐨𝐫𝐞𝐬 𝐢𝐧 𝐲𝐨𝐮𝐫 𝐥𝐨𝐜𝐚𝐥 𝐬𝐲𝐬𝐭𝐞𝐦

        𝐒𝐭𝐞𝐩𝐬 :
          1) 𝐍𝐚𝐯𝐢𝐠𝐚𝐭𝐞 𝐭𝐨 𝐰𝐞𝐛𝐬𝐢𝐭𝐞 𝐨𝐧𝐞 𝐛𝐲 𝐨𝐧𝐞 𝐰𝐡𝐢𝐜𝐡 𝐢𝐬 𝐦𝐞𝐧𝐭𝐢𝐨𝐧𝐞𝐝 𝐛𝐞𝐥𝐨𝐰 :
        https://lnkd.in/d6dUJb7t
        𝐘𝐨𝐮 𝐧𝐞𝐞𝐝 𝐭𝐨 𝐬𝐭𝐨𝐫𝐞 𝐔𝐑𝐋 𝐢𝐧 𝐜𝐨𝐧𝐟𝐢𝐠 𝐟𝐢𝐥𝐞.
          2) 𝐎𝐩𝐞𝐧 𝐭𝐡𝐞 𝐩𝐚𝐠𝐞 𝐚𝐧𝐝 𝐜𝐥𝐢𝐜𝐤 𝐨𝐧 𝐭𝐡𝐞 𝐛𝐞𝐥𝐨𝐰 𝐥𝐢𝐧𝐤 𝐞𝐥𝐞𝐦𝐞𝐧𝐭 𝐨𝐧 𝐭𝐡𝐞 𝐖𝐞𝐛𝐩𝐚𝐠𝐞 𝐛𝐲 𝐥𝐨𝐜𝐚𝐭𝐨𝐫 𝐬𝐭𝐫𝐚𝐭𝐞𝐠𝐲 𝐠𝐢𝐯𝐞𝐧 𝐛𝐞𝐥𝐨𝐰 :
          WebElement pdfLink = driver.findElement(By.linkText("Download a Printable PDF of this Cheat Sheet"));
            3)𝐀𝐟𝐭𝐞𝐫 𝐜𝐥𝐢𝐜𝐤𝐢𝐧𝐠 𝐨𝐧 𝐭𝐡𝐞 𝐥𝐢𝐧𝐤, 𝐚 𝐧𝐞𝐰 𝐏𝐃𝐅 𝐟𝐢𝐥𝐞 𝐢𝐬 𝐨𝐩𝐞𝐧𝐞𝐝 𝐢𝐧 𝐚 𝐧𝐞𝐰 𝐭𝐚𝐛 𝐢𝐧 𝐏𝐃𝐅 𝐯𝐢𝐞𝐰𝐞𝐫 - 𝐲𝐨𝐮 𝐧𝐞𝐞𝐝 𝐭𝐨 𝐝𝐨𝐰𝐧𝐥𝐨𝐚𝐝 𝐭𝐡𝐚𝐭 𝐏𝐃𝐅 𝐟𝐢𝐥𝐞 𝐨𝐧 𝐲𝐨𝐮𝐫 𝐥𝐨𝐜𝐚𝐥 𝐝𝐞𝐯𝐢𝐜𝐞.
          4) 𝐀𝐥𝐬𝐨 𝐯𝐞𝐫𝐢𝐟𝐲 𝐢𝐟 𝐭𝐡𝐞 𝐩𝐚𝐭𝐡 𝐲𝐨𝐮 𝐩𝐫𝐨𝐯𝐢𝐝𝐞𝐝, 𝐡𝐚𝐬 𝐝𝐨𝐰𝐧𝐥𝐨𝐚𝐝𝐞𝐝 𝐭𝐡𝐞 𝐩𝐝𝐟 𝐟𝐢𝐥𝐞 𝐨𝐫 𝐧𝐨𝐭.
          𝐂𝐡𝐚𝐥𝐥𝐞𝐧𝐠𝐞: You cannot use 𝐂𝐡𝐫𝐨𝐦𝐞𝐎𝐩𝐭𝐢𝐨𝐧𝐬,𝐅𝐢𝐫𝐞𝐟𝐨𝐱𝐎𝐩𝐭𝐢𝐨𝐧𝐬 and 𝐊𝐞𝐲𝐛𝐨𝐚𝐫𝐝 𝐬𝐡𝐨𝐫𝐭𝐜𝐮𝐭𝐬 - 𝐊𝐞𝐲𝐄𝐯𝐞𝐧𝐭𝐬 for it.*/

public class Day13 {

    public static WebDriver driver;
    public static String pdfUrl=null;
    public static String outputFilePath = "C:\\Users\\KushalParikh\\Downloads";

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
    public void animationButtonClickTest()
    {
        driver.get("https://intellipaat.com/blog/tutorial/selenium-tutorial/selenium-cheat-sheet/");
        // Find the download link and scroll to it
        WebElement downloadLink = driver.findElement(By.linkText("Download a Printable PDF of this Cheat Sheet"));
        new Actions(driver).moveToElement(downloadLink).perform();
        String mainWindowHandle = driver.getWindowHandle();

        // Wait until the download link is clickable
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(downloadLink));

        // Click on the download link
        downloadLink.click();

        // Wait until a new window handle is available
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switch to the new window
        Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println(allWindowHandles.size());
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                wait.until(driver -> {
                    pdfUrl = driver.getCurrentUrl(); // Assigning URL to pdfUrl
                    return pdfUrl != null;
                });
                break;
            }
        }

        // Download PDF
        if (!pdfUrl.isEmpty()) {
            downloadPDF(pdfUrl,outputFilePath,"pdfTest.pdf");
        } else {
            System.out.println("PDF URL not found.");
        }
    }




    private static void downloadPDF(String pdfUrl,String outputFilePath,String pdfFileName) {
        try {
            URLConnection connection = new URL(pdfUrl).openConnection();
            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(outputFilePath+ File.separator+pdfFileName)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("PDF downloaded successfully to: " + outputFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
