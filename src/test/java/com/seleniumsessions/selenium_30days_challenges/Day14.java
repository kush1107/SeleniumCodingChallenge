package com.seleniumsessions.selenium_30days_challenges;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/* Day-14 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐯𝐚𝐥𝐢𝐝𝐚𝐭𝐞𝐬 𝐟𝐨𝐥𝐥𝐨𝐰𝐢𝐧𝐠 𝐭𝐰𝐨 𝐭𝐞𝐬𝐭 𝐜𝐚𝐬𝐞𝐬:

        ✅𝐓𝐞𝐬𝐭 𝐂𝐚𝐬𝐞 1: 𝐕𝐞𝐫𝐢𝐟𝐲 𝐅𝐥𝐢𝐠𝐡𝐭 𝐑𝐞𝐬𝐮𝐥𝐭𝐬 𝐒𝐞𝐚𝐫𝐜𝐡

        𝐒𝐭𝐞𝐩𝐬:
        -Open the JetBlue website - https://www.jetblue.com/.
        -Enter "Mumbai" in the departure city field and select it from the dynamic list.
        -Enter "London-Heathrow, UK (LHR)" in the destination city field and select it from the dynamic list.
        -Enter the departure date as "03/19/2024" in the date picker.
        -Enter the return date as "03/20/2024" in the date picker.
        -Click on the "Search flights" button.
        -Verify that the results for the flights are displayed or not.

        ✅𝐓𝐞𝐬𝐭 𝐂𝐚𝐬𝐞 2: 𝐕𝐞𝐫𝐢𝐟𝐲 𝐅𝐚𝐢𝐥𝐞𝐝 𝐅𝐥𝐢𝐠𝐡𝐭 𝐒𝐞𝐚𝐫𝐜𝐡

        𝐒𝐭𝐞𝐩𝐬:
        -Open the JetBlue website - https://www.jetblue.com/.
        -Enter "Mumbai" in the departure city field and select it from the dynamic list.
        -Enter "London-Heathrow, UK (LHR)" in the destination city field and select it from the dynamic list.
        -Enter the departure date as "01/01/2024" in the date picker.
        -Enter the return date as "01/01/2024" in the date picker.
        -Click on the "Search flights" button.
        -Verify and capture the validation msg.

        𝐍𝐨𝐭𝐞: Use TestNG and execute test cases one by one using priority and the browser should be launched only once. Here date format MM/DD/YYYY is applicable. */

public class Day14 {

    public static WebDriver driver;

    @BeforeSuite
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
    }



    @Test(priority = 1)
    public void FlightResultTest()
    {
        // Navigate to the webpage
        driver.get("https://www.jetblue.com/");

        try {
            driver.switchTo().frame(1);
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Accept All Cookies']")));
            driver.findElement(By.xpath("//a[normalize-space()='Accept All Cookies']")).click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")));
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")).sendKeys("Mumbai");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Mumbai')]")));
        driver.findElement(By.xpath("//strong[contains(text(),'Mumbai')]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")));
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")).sendKeys("London-Heathrow, UK (LHR)");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'London')]")));
        driver.findElement(By.xpath("//strong[contains(text(),'London')]")).click();

        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).sendKeys(setCurrentDate());

        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).sendKeys(setCurrentDatePlusTwo());

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span//span[normalize-space()='Search flights']")));
        driver.findElement(By.xpath("//button//span//span[normalize-space()='Search flights']")).click();

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continue']")));
            driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span//span[normalize-space()='Search flights']")));
        driver.findElement(By.xpath("//button//span[normalize-space()='Continue to flight results']")).click();


        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//jb-icon[@name='loading']")));
        String confirmation_msg  = driver.findElement(By.xpath("//h2[contains(normalize-space(),'No flights have been found for your search criteria')]")).getText();
        if(confirmation_msg.contains("No flights have been found"))
        {
            System.out.println("Test is passed!");
        }
        else{
            System.out.println("Test is failed!");
        }

    }

    @Test(priority = 2)
    public void FlightValidationTest()
    {
        // Navigate to the webpage
        driver.get("https://www.jetblue.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")));
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")).sendKeys("Mumbai");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Mumbai')]")));
        driver.findElement(By.xpath("//strong[contains(text(),'Mumbai')]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")));
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")).sendKeys("London-Heathrow, UK (LHR)");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'London')]")));
        driver.findElement(By.xpath("//strong[contains(text(),'London')]")).click();

        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).sendKeys("01/01/2024");

        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).sendKeys("01/02/2024");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span//span[normalize-space()='Search flights']")));
        driver.findElement(By.xpath("//button//span//span[normalize-space()='Search flights']")).click();

      WebElement error =   new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//jb-error[@id]")));
        if(error.isDisplayed())
        {
            System.out.println("Error Validation is passed!");
        }
        else{
            System.out.println("Error Validation is failed!");
        }

    }

    @AfterSuite
    public void tearDown()
    {
        driver.quit();
    }

    public String setCurrentDate()
    {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedCurrentDate = currentDate.format(formatter);
        return formattedCurrentDate;
    }

    public String setCurrentDatePlusTwo()
    {
        LocalDate currentDate = LocalDate.now().plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedCurrentDate = currentDate.format(formatter);
        return formattedCurrentDate;
    }
}
