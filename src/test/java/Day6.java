import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day6 {
    public static WebDriver driver;

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
        // Navigate to the webpage


        driver.get("http://uitestingplayground.com/progressbar");
        WebElement startButton = driver.findElement(By.id("startButton"));
        WebElement stopButton = driver.findElement(By.id("stopButton"));
        WebElement progressBar = driver.findElement(By.id("progressBar"));

        startButton.click();

        while (true) {
            int percentage = Integer.parseInt(progressBar.getText().replace("%", ""));

            if(percentage >=65) {
                ((JavascriptExecutor)driver).
                        executeScript("arguments[0].click()", stopButton);
                break;

            }
        }

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
