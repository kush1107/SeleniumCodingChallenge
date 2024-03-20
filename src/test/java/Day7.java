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

/*Day -7  𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐩𝐞𝐫𝐟𝐨𝐫𝐦𝐬 𝐭𝐡𝐞 "𝐑𝐢𝐠𝐡𝐭 𝐂𝐥𝐢𝐜𝐤" 𝐨𝐟 𝐦𝐨𝐮𝐬𝐞 𝐚𝐧𝐝
        𝐲𝐨𝐮 𝐰𝐢𝐥𝐥 𝐬𝐞𝐞 𝐭𝐡𝐞 𝐦𝐞𝐧𝐮 𝐭𝐡𝐞𝐧 𝐧𝐚𝐯𝐢𝐠𝐚𝐭𝐞 𝐭𝐨 "𝐒𝐡𝐚𝐫𝐞 𝐦𝐞𝐧𝐮" 𝐨𝐩𝐭𝐢𝐨𝐧 𝐚𝐧𝐝 𝐜𝐥𝐢𝐜𝐤 𝐨𝐧 𝐚𝐥𝐥 "𝐬𝐨𝐜𝐢𝐚𝐥 𝐦𝐞𝐝𝐢𝐚 𝐥𝐢𝐧𝐤𝐬" 𝐢𝐧 𝐬𝐮𝐛-𝐦𝐞𝐧𝐮.
        𝐚𝐧𝐝 𝐚𝐬𝐬𝐞𝐫𝐭𝐬 𝐭𝐡𝐞 𝐯𝐞𝐫𝐢𝐟𝐢𝐜𝐚𝐭𝐢𝐨𝐧 𝐦𝐞𝐬𝐬𝐚𝐠𝐞 𝐟𝐨𝐫 𝐚𝐥𝐥 𝐬𝐨𝐜𝐢𝐚𝐥 𝐥𝐢𝐧𝐤𝐬.
        e.g: "Menu item Twitter clicked"
        https://lnkd.in/dFjNKBKE*/

public class Day7 {
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
    public void SocialIconClickTest()
    {
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

}
