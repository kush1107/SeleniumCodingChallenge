import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;

public class Day13 {

    public static WebDriver driver;
    public static String pdfUrl;
    public static String outputFilePath;

    @BeforeClass
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }



    @Test
    public void animationButtonClickTest()
    {
        driver.get("https://intellipaat.com/blog/tutorial/selenium-tutorial/selenium-cheat-sheet/");
        WebElement ele = driver.findElement(By.linkText("Download a Printable PDF of this Cheat Sheet"));
        new Actions(driver)
                .scrollToElement(ele)
                .perform();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
// Download PDF
        URLConnection connection = null;
        try {
            connection = new URL(pdfUrl).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("PDF downloaded successfully to: " + outputFilePath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
