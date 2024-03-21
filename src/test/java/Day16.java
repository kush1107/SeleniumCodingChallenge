import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.time.Duration;

public class Day16 {
    public static WebDriver driver;

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
    public void MouseHoverTest() {
        driver.get("https://qaplayground.dev/apps/mouse-hover/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".poster-container img")));


        WebElement currentPrice = driver.findElement(By.cssSelector(".current-price"));
        WebElement poster = driver.findElement(By.cssSelector(".poster"));

        // Perform mouse hover
        actions.moveToElement(poster).perform();

        // Wait for the current price to change
        wait.until(ExpectedConditions.textToBePresentInElement(currentPrice, "$24.96"));

        // Assertion
        String actualPrice = currentPrice.getText();
        String expectedPrice = "$24.96";
        if (actualPrice.equals(expectedPrice)) {
            System.out.println("Test Passed! Current price is: " + actualPrice);
        } else {
            System.out.println("Test Failed! Expected price: " + expectedPrice + ", but found: " + actualPrice);
        }
    }
}
