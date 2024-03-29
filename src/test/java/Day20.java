import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Day20 {
    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://qaplayground.dev/apps/qr-code-generator/");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void ReadTextFrom_QRCodeTest() {

        WebElement QR_InputField = new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter text or URL']")));
        QR_InputField.sendKeys("I am a Automation QA Engineer");

        WebElement generateQAButton = driver.findElement(By.xpath("//button[normalize-space()='Generate QR Code']"));
        generateQAButton.click();

        WebElement QR_generated = new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='qr-code']")));
        String qrCodeURL = QR_generated.getAttribute("src");
        System.out.println(qrCodeURL);
        URL url= null;
        try {
            url = new URL(qrCodeURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedImage bufferedimage= null;
        try {
            bufferedimage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LuminanceSource luminanceSource=new BufferedImageLuminanceSource(bufferedimage);
        BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = null;
        try {
            result = new MultiFormatReader().decode(binaryBitmap);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Text Fetched from QR Code is: "+result.getText());
    }
}
