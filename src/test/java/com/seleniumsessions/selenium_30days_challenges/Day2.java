package com.seleniumsessions.selenium_30days_challenges;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

 /*𝐃𝐚𝐲 2 -𝐂𝐨𝐝𝐢𝐧𝐠 𝐂𝐡𝐚𝐥𝐥𝐞𝐧𝐠𝐞:
         𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨: Develop an automation script that will input a value in a disabled field
         (You cannot able to input value in field with sendkeys() directly if field is disabled)
         𝐋𝐢𝐧𝐤: https://lnkd.in/deJDkARE*/

public class Day2 {
    public static WebDriver driver;
    public static Logger log = LogManager.getLogger(Day2.class);

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
    public void DisabledFieldTest()
    {
        driver.get("https://seleniumpractise.blogspot.com/2016/09/how-to-work-with-disable-textbox-or.html");
        log.info("Url is launched");
        driver.manage().window().maximize();
        log.debug("Url is launched");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('pass').removeAttribute('disabled')");
        driver.findElement(By.id("pass")).sendKeys("Hello");
    }
}
