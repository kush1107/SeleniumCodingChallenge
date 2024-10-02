package com.seleniumsessions.selenium_30days_challenges;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

/*Day-4 𝐔𝐬𝐢𝐧𝐠 𝐬𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 - 𝐂𝐥𝐢𝐜𝐤 𝐨𝐧 𝐭𝐡𝐞 𝐠𝐫𝐨𝐰𝐢𝐧𝐠 𝐛𝐮𝐭𝐭𝐨𝐧 𝐚𝐧𝐝 𝐨𝐧𝐜𝐞 𝐜𝐥𝐢𝐜𝐤𝐞𝐝 𝐲𝐨𝐮 𝐬𝐡𝐨𝐮𝐥𝐝 𝐬𝐞𝐞 "𝐄𝐯𝐞𝐧𝐭 𝐓𝐫𝐢𝐠𝐠𝐞𝐫𝐞𝐝" 𝐦𝐞𝐬𝐬𝐚𝐠𝐞.
        𝐕𝐞𝐫𝐢𝐟𝐲 𝐭𝐡𝐚𝐭 "𝐄𝐯𝐞𝐧𝐭 𝐓𝐫𝐢𝐠𝐠𝐞𝐫𝐞𝐝".
        𝐇𝐢𝐧𝐭: Not all elements are instantly clickable, particularly when animations etc. are in use.
        https://lnkd.in/d9HmwQu7  */


public class Day4 {

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

}
