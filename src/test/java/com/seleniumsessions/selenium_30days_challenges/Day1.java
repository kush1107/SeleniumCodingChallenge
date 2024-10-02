package com.seleniumsessions.selenium_30days_challenges;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

/*𝐃𝐚𝐲 1 -𝐂𝐨𝐝𝐢𝐧𝐠 𝐂𝐡𝐚𝐥𝐥𝐞𝐧𝐠𝐞:
        𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨: Develop an automation script that bypasses the Basic Browser Authentication Popup.
        Link: https://lnkd.in/dB3ZAGi7

        𝐔𝐬𝐞𝐫𝐧𝐚𝐦𝐞: admin
        𝐏𝐚𝐬𝐬𝐰𝐨𝐫𝐝: admin*/

public class Day1 {
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
    public void AuthenticationTest()
    {
        ((HasAuthentication) driver)
                .register(() -> new UsernameAndPassword("admin", "admin"));
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        WebElement text = driver.findElement(By.xpath("//p"));
        Assert.assertEquals(text.getText(),"Congratulations! You must have the proper credentials.");

    }
}
