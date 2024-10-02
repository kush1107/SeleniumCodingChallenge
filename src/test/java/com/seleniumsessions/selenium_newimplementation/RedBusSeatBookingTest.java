package com.seleniumsessions.selenium_newimplementation;

import com.seleniumsessions.selenium_30days_challenges.Day2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;

public class RedBusSeatBookingTest {
    public static WebDriver driver;
    public static Logger log = LogManager.getLogger(Day2.class);

    @BeforeClass
    public void setup()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
        driver.get("https://www.redbus.in");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void testRedBusSeatBooking() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);

        WebElement sourceInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='src']")));
        sourceInput.sendKeys("Vadodara");
        WebElement sourceOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//text[@class='placeHolderMainText'][normalize-space()='Vadodara']")));
        action.moveToElement(sourceOption).build().perform();

        WebElement destinationInput = driver.findElement(By.xpath("//input[@id='dest']"));
        destinationInput.sendKeys("Mumbai");
        WebElement destinationOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//text[@class='placeHolderMainText'][normalize-space()='Mumbai']")));
        destinationOption.click();

        WebElement date_picker = driver.findElement(By.cssSelector(".dateText"));
        date_picker.click();
        WebElement date = driver.findElement(By.xpath("//div//span[normalize-space()='20']"));
        date.click();


        WebElement searchButton = driver.findElement(By.xpath("//button[@id='search_button']"));
        searchButton.click();

        WebElement viewSeats = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li//div[@class='button view-seats fr'][normalize-space()='View Seats'])[1]")));
        viewSeats.click();

        WebElement hideSeats = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'hideSeats')]")));
        if (hideSeats.isDisplayed()) {
            WebElement canvas = driver.findElement(By.cssSelector(".upper-canvas.canvas-wrapper canvas"));
            WebElement seatWrapper = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='SeatsSelectorWrapper']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", canvas);
            Dimension canvasSize = canvas.getSize();
            int relativeX = canvasSize.getWidth() / 5;
            int relativeY = canvasSize.getHeight() / 5;
            int count_available_seats = 0;
            for (int x = 0; x < canvasSize.getWidth(); x += 20) {
                for (int y = 0; y < canvasSize.getHeight(); y += 20) {
                    int relativePosX = relativeX + x - (canvasSize.getWidth() / 2);
                    int relativePosY = relativeY + y - (canvasSize.getHeight() / 2);
                    Actions actions = new Actions(driver);
                    actions.moveToElement(canvas, relativePosX, relativePosY).build().perform();
                    String currentClass = canvas.getAttribute("class");
                    if (currentClass.contains("pointer")) {

                    }
                }
            }
        }
    }
}
