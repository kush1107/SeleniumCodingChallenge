import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/*✅𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨:

𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐯𝐞𝐫𝐢𝐟𝐲 - 𝐬𝐞𝐭 𝐞𝐚𝐜𝐡 𝐚𝐯𝐚𝐢𝐥𝐚𝐛𝐥𝐞 𝐫𝐚𝐭𝐞 𝐯𝐚𝐥𝐮𝐞 𝐚𝐧𝐝 𝐚𝐬𝐬𝐞𝐫𝐭 𝐛𝐲 𝐢𝐦𝐚𝐠𝐞, 𝐭𝐞𝐱𝐭, 𝐚𝐧𝐝 𝐧𝐮𝐦𝐛𝐞𝐫

𝐒𝐭𝐞𝐩𝐬:
1)Navigate to: https://lnkd.in/dgNMwyt9
2)Click on the set of each available rate value and assert by image, text, and number.
4)Verify that the Emoji image, the text message e.g-"I don't like it" and the number out of 5 e.g- "2 out of 5". Verify for all the 5 ratings. */

public class Day19 {
    public static WebDriver driver;
    public static int input=4;  //You can take user input from excel,properties file for config file or from command line as per real scenario

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://qaplayground.dev/apps/rating/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void EmojiRating() {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        List<WebElement> starsRating = driver.findElements(By.xpath("//label[contains(@class,'star-') and contains(@class,'fa-star')]"));
        // Click on stars based on user input using a simple loop
        for (int i = 0; i < Math.min(input, starsRating.size()); i++) {
            starsRating.get(i).click();
        }

        switch (input) {
            case 1:
                WebElement star1_emoji = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[src='emojis/emoji-"+input+".png']")));
                Assert.assertTrue(star1_emoji.isDisplayed());
                Assert.assertEquals(fetchPsuedoElement_Comment(),"I just hate it");
                Assert.assertEquals(fetchPsuedoElement_Numb(),"1 out of 5");
                System.out.println("Test is passed..");
                break;
            case 2:
                WebElement star2_emoji= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[src='emojis/emoji-"+input+".png']")));
                Assert.assertTrue(star2_emoji.isDisplayed());
                Assert.assertEquals(fetchPsuedoElement_Comment(),"I don't like it");
                Assert.assertEquals(fetchPsuedoElement_Numb(),"2 out of 5");
                System.out.println("Test is passed..");
                break;
            case 3:
                WebElement star3_emoji = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[src='emojis/emoji-"+input+".png']")));
                Assert.assertTrue(star3_emoji.isDisplayed());
                Assert.assertEquals(fetchPsuedoElement_Comment(),"This is awesome");
                Assert.assertEquals(fetchPsuedoElement_Numb(),"3 out of 5");
                System.out.println("Test is passed..");
                break;
            case 4:
                WebElement star4_emoji = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[src='emojis/emoji-"+input+".png']")));
                Assert.assertTrue(star4_emoji.isDisplayed());
                Assert.assertEquals(fetchPsuedoElement_Comment(),"I just like it");
                Assert.assertEquals(fetchPsuedoElement_Numb(),"4 out of 5");
                System.out.println("Test is passed..");
                break;
            case 5:
                WebElement star5_emoji = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[src='emojis/emoji-"+input+".png']")));
                Assert.assertTrue(star5_emoji.isDisplayed());
                Assert.assertEquals(fetchPsuedoElement_Comment(),"I just love it");
                Assert.assertEquals(fetchPsuedoElement_Numb(),"5 out of 5");
                System.out.println("Test is passed..");
                break;
            default:
                System.out.println("Something went wrong...");
        }




    }
    public static String fetchPsuedoElement_Comment()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return window.getComputedStyle(document.querySelector('span.text'),'::before').getPropertyValue('content').replace(/\"/g, '')";
        return  (String) js.executeScript(script);
    }

    public static String fetchPsuedoElement_Numb()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return window.getComputedStyle(document.querySelector('span.numb'), '::before').getPropertyValue('content').replace(/\"/g, '')";
        return (String) js.executeScript(script);
    }
}
