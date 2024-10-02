package com.seleniumsessions.selenium_30days_challenges;

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

/* Day-5 𝐔𝐬𝐢𝐧𝐠 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐞𝐧𝐭𝐞𝐫 𝐭𝐡𝐞 𝐯𝐚𝐥𝐢𝐝 𝐜𝐨𝐝𝐞 𝐛𝐲 𝐤𝐞𝐲𝐛𝐨𝐚𝐫𝐝 𝐤𝐞𝐲𝐬 𝐛𝐲 𝐩𝐫𝐞𝐬𝐬𝐢𝐧𝐠 𝐭𝐡𝐞 𝐨𝐧𝐥𝐲 𝐤𝐞𝐲 𝐛𝐮𝐭𝐭𝐨𝐧 𝐚𝐧𝐝 𝐚𝐬𝐬𝐞𝐫𝐭𝐢𝐧𝐠 "𝐬𝐮𝐜𝐜𝐞𝐬𝐬" 𝐦𝐞𝐬𝐬𝐚𝐠𝐞.
        The confirmation code is - "999999".
        You cannot use sendkeys("9") directly.
        https://lnkd.in/ddfR-Gpa
        𝐇𝐢𝐧𝐭: Use keyboard events Keys concept
         */

public class Day5 {

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
    public void ValidateCodeTest()
    {
        // Navigate to the webpage
        driver.get("https://qaplayground.dev/apps/verify-account/");

        List<WebElement> inputFields = driver.findElements(By.cssSelector(".code-container input"));
        Actions actions = new Actions(driver);
        for (WebElement input : inputFields) { actions.moveToElement(input).keyDown(Keys.NUMPAD9).keyUp(Keys.NUMPAD9).perform();
        }
        Assert.assertTrue(driver.findElement(By.cssSelector(".success")).getText().equalsIgnoreCase("Success"));

    }
}
