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

public class DatePicker_MakeMyTrip {
    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/railways/");
        driver.manage().deleteAllCookies();
    }
   @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void DataPickerTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[normalize-space()='Train Ticket Booking']")));


        driver.findElement(By.xpath("//input[@id='travelDate']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='dateFiled']"))));

        String inputmonth = "August 2024";
        String inputdate = "22";



        }

        public void setTravelDate(String inputmonth,String inputdate)
        {
            while(true)
            {
                WebElement currentMonth = driver.findElement(By.xpath("(//div[@role='heading'])[1]"));
                String currentMonthtext = currentMonth.getText();
                if(currentMonthtext.equals(inputmonth))
                {
                    break;
                }
                else
                {
                    driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            List<WebElement> dateSelector = driver.findElements(By.xpath("(//div[@role='grid'])[1]//div[@class='DayPicker-Day']"));
            for(WebElement dateElement:dateSelector)
            {
                if(dateElement.getText().equals(inputdate))
                {
                    dateElement.click();
                    break;
                }
            }
        }




}
