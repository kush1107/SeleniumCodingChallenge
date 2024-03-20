import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day7 {
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


        driver.get("https://qaplayground.dev/apps/context-menu/");
        Actions actions = new Actions(driver);
        List<WebElement> elements = driver.findElements(By.cssSelector("ul.share-menu li span"));
        for (WebElement element : elements) {
            actions.contextClick(driver.findElement(By.tagName("body"))).perform();
            actions.moveToElement(driver.findElement(By.cssSelector("li.share"))).perform();
            element.click();

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#msg")));

            if (element.getText().equalsIgnoreCase("Twitter")) {
                Assert.assertTrue(driver.findElement(By.cssSelector("p#msg")).getText().equalsIgnoreCase("Menu item Twitter clicked"));
                System.out.println("Twitter link - verified ");
            }
            else if (element.getText().equalsIgnoreCase("Instagram")) {
                Assert.assertTrue(driver.findElement(By.cssSelector("p#msg")).getText().equalsIgnoreCase("Menu item Instagram clicked"));
                System.out.println("Instagram link -verified ");
            }
            else if (element.getText().equalsIgnoreCase("Dribble")) {
                Assert.assertTrue(driver.findElement(By.cssSelector("p#msg")).getText().equalsIgnoreCase("Menu item Dribble clicked"));
                System.out.println("Dribble link -verified ");
            }
            else if (element.getText().equalsIgnoreCase("Telegram")) {
                Assert.assertTrue(driver.findElement(By.cssSelector("p#msg")).getText().equalsIgnoreCase("Menu item Telegram clicked"));
                System.out.println("Telegram link -verified ");
            }
        }

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
