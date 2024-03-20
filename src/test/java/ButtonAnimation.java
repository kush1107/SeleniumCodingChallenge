import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;

public class ButtonAnimation {

    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
        // Set Chrome options to disable animations
        // Initialize the WebDriver instance
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }



    @Test
    public void animationButtonClickTest()
    {
        // Navigate to the webpage
        driver.get("https://testpages.eviltester.com/styled/challenges/growing-clickable.html");

        // Find the button element by its ID
        WebElement button = driver.findElement(By.id("growbutton"));
        WebElement text = driver.findElement(By.id("growbuttonstatus"));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.attributeContains(button, "class", "grown"));
        if (button.isEnabled()) {
            button.click();
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(d -> text.isDisplayed());
            if (text.isDisplayed()) {
                String eventTriggerMsg = driver.findElement(By.xpath("//p[@id='growbuttonstatus']")).getText();
                System.out.println(eventTriggerMsg);
                Assert.assertEquals(eventTriggerMsg,"Event Triggered");
            }
        }
        else {
            System.out.println("Not Clicked");
        }
        
    }

       @AfterClass
       public void tearDown()
       {
           driver.quit();
       }

}
