package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HandlingWebTableDynamically {
    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/web-table-element.php");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void HandleWebTableDynamicallyTest() {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        boolean all = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='All']"))).isDisplayed();

        if(all)
        {
            List<WebElement>  ele_row = driver.findElements(By.xpath("//table[@class='dataTable']//tbody//tr//td//a "));
            for(WebElement ele:ele_row)
            {
                System.out.println(ele.getText());
            }
        }


    }
}
