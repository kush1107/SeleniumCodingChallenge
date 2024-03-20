import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class Day9 {
    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
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
    public void ShadowDOMTest() throws IOException, UnsupportedFlavorException {
        driver.get("https://uitestingplayground.com/shadowdom");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement advance_safe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("details-button")));
        advance_safe.click();
        WebElement proceed_link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("proceed-link")));
        proceed_link.click();
        SearchContext shadowDOMContext = driver.findElement(By.tagName("guid-generator")).getShadowRoot();
        shadowDOMContext.findElement(By.cssSelector(".button-generate")).click();
        WebElement element = shadowDOMContext.findElement(By.cssSelector("#editField"));
        String inputTextString = element.getText();


        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Object clipData = clipboard.getData(DataFlavor.stringFlavor);
        Assert.assertTrue(((String) clipData).equals(inputTextString));
    }
}
