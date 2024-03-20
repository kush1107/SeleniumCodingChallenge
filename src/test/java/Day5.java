import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day5 {

    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }



    @Test
    public void animationButtonClickTest()
    {
        // Navigate to the webpage
        driver.get("https://qaplayground.dev/apps/verify-account/");

        List<WebElement> inputFields = driver.findElements(By.cssSelector(".code-container input"));
        Actions actions = new Actions(driver);
        for (WebElement input : inputFields) { actions.moveToElement(input).keyDown(Keys.NUMPAD9).keyUp(Keys.NUMPAD9).perform();
        }
        Assert.assertTrue(driver.findElement(By.cssSelector(".success")).getText().equalsIgnoreCase("Success"));

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
