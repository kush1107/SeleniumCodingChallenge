package com.seleniumsessions;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/*    Day 10 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐝𝐨𝐰𝐧𝐥𝐨𝐚𝐝 𝐟𝐢𝐥𝐞 𝐢𝐧 𝐏𝐃𝐅 𝐟𝐢𝐥𝐞 𝐢𝐧 𝐲𝐨𝐮𝐫 𝐥𝐨𝐜𝐚𝐥 𝐚𝐧𝐝 𝐯𝐞𝐫𝐢𝐟𝐲 𝐭𝐡𝐞 𝐭𝐞𝐱𝐭 "𝐆𝐞𝐭 𝐓𝐢𝐜𝐤𝐞𝐭𝐬" 𝐢𝐧𝐬𝐢𝐝𝐞 𝐭𝐡𝐚𝐭 𝐏𝐃𝐅 𝐟𝐢𝐥𝐞.

        𝐒𝐭𝐞𝐩𝐬 :
        1) 𝐍𝐚𝐯𝐢𝐠𝐚𝐭𝐞 𝐭𝐨 𝐰𝐞𝐛𝐬𝐢𝐭𝐞:
        https://lnkd.in/dE-sxjvG
        2) 𝐂𝐥𝐢𝐜𝐤 𝐨𝐧 𝐭𝐡𝐞 "𝐃𝐨𝐰𝐧𝐥𝐨𝐚𝐝" 𝐛𝐮𝐭𝐭𝐨𝐧 𝐛𝐞𝐥𝐨𝐰 𝐭𝐡𝐞 𝐭𝐞𝐱𝐭 "𝐅𝐢𝐥𝐞 𝐃𝐨𝐰𝐧𝐥𝐨𝐚𝐝 𝐃𝐞𝐦𝐨 𝐟𝐨𝐫 𝐀𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧"
        3) 𝐃𝐨𝐰𝐧𝐥𝐨𝐚𝐝 𝐭𝐡𝐞 𝐟𝐢𝐥𝐞 𝐢𝐧 𝐲𝐨𝐮𝐫 𝐥𝐨𝐜𝐚𝐥.
        4) 𝐏𝐫𝐢𝐧𝐭 𝐭𝐡𝐞 𝐟𝐢𝐥𝐞 𝐧𝐚𝐦𝐞, 𝐟𝐢𝐥𝐞 𝐬𝐢𝐳𝐞, 𝐚𝐧𝐝 𝐥𝐨𝐜𝐚𝐭𝐢𝐨𝐧 𝐨𝐟 𝐭𝐡𝐞 𝐩𝐚𝐭𝐡 𝐰𝐡𝐞𝐫𝐞 𝐭𝐡𝐞 𝐟𝐢𝐥𝐞 𝐢𝐬 𝐬𝐚𝐯𝐞𝐝.
        5) 𝐕𝐞𝐫𝐢𝐟𝐲 𝐭𝐡𝐚𝐭 𝐭𝐡𝐞 𝐭𝐞𝐱𝐭 "𝐆𝐞𝐭 𝐓𝐢𝐜𝐤𝐞𝐭𝐬" 𝐞𝐱𝐢𝐬𝐭𝐬 𝐢𝐧 𝐭𝐡𝐞 𝐟𝐢𝐥𝐞 𝐝𝐨𝐰𝐧𝐥𝐨𝐚𝐝𝐞𝐝 (𝐏𝐃𝐅) 𝐨𝐫 𝐧𝐨𝐭.

        𝐇𝐢𝐧𝐭: 𝐔𝐬𝐞 𝐂𝐡𝐫𝐨𝐦𝐞𝐎𝐩𝐭𝐢𝐨𝐧𝐬 𝐚𝐧𝐝 𝐀𝐩𝐚𝐜𝐡𝐞 𝐏𝐃𝐅𝐁𝐨𝐱 𝐃𝐞𝐩𝐞𝐧𝐝𝐞𝐧𝐜𝐢𝐞𝐬         */


public class Day10 {
    public static WebDriver driver;
    public static String downloadFilepath = "C:\\Users\\KushalParikh\\Downloads";

    @BeforeClass
    public void setup() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilepath);
        prefs.put("browser.download.manager.showWhenStarting", false);
        // disable save address popup
        // PDF download
        prefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("plugins.always_open_pdf_externally", true);

        // Set up ChromeOptions
        ChromeOptions opt = new ChromeOptions();
        opt.setExperimentalOption("prefs", prefs);

        // Set up WebDriver
        driver = new ChromeDriver(opt);
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
    public void day10() throws IOException {
        driver.get("https://demo.automationtesting.in/FileDownload.html");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));

        WebElement download = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@type='button']")));

        //Need to scroll as Click is intercepted due to advertise banner
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", download);

        download.click();
        // Wait for the file to download
        wait.until((WebDriver wd) -> {
            File downloadedFile = new File(downloadFilepath+"\\sampleFile.pdf");
            return downloadedFile.exists() && downloadedFile.length() > 0;
        });

        PDDocument document = Loader.loadPDF(new File(downloadFilepath+"\\sampleFile.pdf"));
        PDFTextStripper stripper = new PDFTextStripper();
        String pdfText = stripper.getText(document);
        String searchText = "Get Tickets";
        if (pdfText.contains(searchText)) {
            System.out.println("Text found in the PDF.");
        } else {
            System.out.println("Text not found in the PDF.");
        }
    }
}
