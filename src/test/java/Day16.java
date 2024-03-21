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

/* Day-16  𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐡𝐨𝐯𝐞𝐫 𝐨𝐧 𝐭𝐡𝐞 𝐦𝐨𝐯𝐢𝐞 𝐩𝐨𝐬𝐭𝐞𝐫 𝐚𝐧𝐝 𝐨𝐧 𝐡𝐨𝐯𝐞𝐫 𝐢𝐭 𝐬𝐡𝐨𝐮𝐥𝐝 𝐟𝐞𝐭𝐜𝐡 𝐩𝐫𝐢𝐜𝐞 𝐨𝐟 𝐢𝐭.

        𝐒𝐭𝐞𝐩𝐬:
        1)Navigate to: https://lnkd.in/dwDQbUKz
        2)Do the mouse hover on the Movie poster.
        3)Fetch the price and verify it.  */

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
