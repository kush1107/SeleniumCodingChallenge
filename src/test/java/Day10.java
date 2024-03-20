import io.github.bonigarcia.wdm.WebDriverManager;
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
        WebDriverManager.chromedriver().setup();
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
