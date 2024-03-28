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

/*✅𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨:

𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐯𝐞𝐫𝐢𝐟𝐲 𝐛𝐮𝐭𝐭𝐨𝐧 𝐢𝐬 𝐯𝐢𝐬𝐢𝐛𝐥𝐞 𝐨𝐧 𝐬𝐜𝐫𝐨𝐥𝐥 𝐚𝐧𝐝 𝐮𝐬𝐞𝐫 𝐧𝐞𝐞𝐝 𝐭𝐨 𝐜𝐥𝐢𝐜𝐤 𝐨𝐧 𝐭𝐡𝐚𝐭 𝐛𝐮𝐭𝐭𝐨𝐧.

𝐒𝐭𝐞𝐩𝐬:
1)Navigate to: https://lnkd.in/dRA3mQgh
2)Click on the "YOU FOUND ME!" button.
4)Verify that the "Mission accomplished" message after clicking on the button.*/


public class Day18 {
    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://qaplayground.dev/apps/covered/#");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void ScrollButtonTest() {
        String initialText = driver.findElement(By.id("info")).getText();
        Assert.assertEquals(initialText,"Click the button below", "Verify Initial Text");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement youFoundMe = driver.findElement(By.id("fugitive"));
        js.executeScript("arguments[0].scrollIntoView();", youFoundMe);
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(youFoundMe));
        youFoundMe.click();
        String verifyText = driver.findElement(By.id("info")).getText();
        Assert.assertEquals(verifyText,"Mission accomplished","Verify Message");
    }
}
