import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

 /*Day-3 𝐔𝐬𝐢𝐧𝐠 𝐬𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 - 𝐫𝐞𝐚𝐝 𝐭𝐡𝐞 * 𝐫𝐚𝐭𝐢𝐧𝐠 𝐨𝐟 𝐭𝐡𝐞 𝐛𝐨𝐨𝐤,
        𝐞𝐧𝐭𝐞𝐫 𝐢𝐭 𝐢𝐧 𝐭𝐡𝐞 𝐭𝐞𝐱𝐭 𝐛𝐨𝐱 𝐚𝐧𝐝 𝐜𝐥𝐢𝐜𝐤 "𝐜𝐡𝐞𝐜𝐤 𝐫𝐚𝐭𝐢𝐧𝐠" 𝐛𝐮𝐭𝐭𝐨𝐧. 𝐲𝐨𝐮 𝐬𝐡𝐨𝐮𝐥𝐝 𝐬𝐞𝐞 "𝐰𝐞𝐥𝐥 𝐝𝐨𝐧𝐞!" 𝐦𝐞𝐬𝐬𝐚𝐠𝐞.
         𝐋𝐢𝐧𝐤: https://lnkd.in/dr5adTZK
        𝐇𝐢𝐧𝐭: Use CSS Pseudo-elements Concept*/

public class Day3 {

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
    public void RatingTest()
    {
        driver.get("https://play1.automationcamp.ir/advanced.html");

        // Execute JavaScript to retrieve pseudo-element text
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return window.getComputedStyle(document.querySelector('.star-rating'), '::after').getPropertyValue('content').replace(/\"/g, '')";
        String pseudoElementText = (String) js.executeScript(script);

        // Find the text box and enter the rating
        WebElement ratingTextBox = driver.findElement(By.id("txt_rating"));
        ratingTextBox.sendKeys(pseudoElementText);

        // Find and click the "Check Rating" button
        WebElement checkRatingButton = driver.findElement(By.id("check_rating"));
        checkRatingButton.click();

        // Explicit wait for the "Well done!" message
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement wellDoneMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_rating")));

        // Verify if the message is displayed
        if (wellDoneMessage.isDisplayed()) {
            System.out.println("Well done message: " + wellDoneMessage.getText());
        } else {
            System.out.println("Well done message not found!");
        }
    }

}
