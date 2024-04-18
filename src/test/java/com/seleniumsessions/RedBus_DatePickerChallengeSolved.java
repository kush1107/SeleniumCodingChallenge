package com.seleniumsessions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RedBus_DatePickerChallengeSolved {

    static WebDriver driver;

    public static ArrayList<String> getWeekEndDates(String month) {
        ArrayList<String> weekendList1 = new ArrayList<>();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement monthElement = driver.findElement(By.cssSelector("div.DayNavigator__IconBlock-qj8jdz-2:nth-of-type(2)"));
        String searchingMonth = monthElement.getText();
        while (!searchingMonth.contains(month)) {
             monthElement = driver.findElement(By.cssSelector("div.DayNavigator__IconBlock-qj8jdz-2:nth-of-type(2)"));
             searchingMonth = monthElement.getText();

            if (searchingMonth.contains(month)) {
                List<WebElement> weekends1 = driver.findElements(By.cssSelector("span.bwoYtA"));

                for (WebElement w : weekends1) {
                    weekendList1.add(w.getText());
                }
                System.out.println(monthElement.getText());
                break;
            } else {
                System.out.println(searchingMonth);
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.DayNavigator__IconBlock-qj8jdz-2:nth-of-type(3) > svg"))).click();
            }
        }

        return weekendList1;
    }

    public static void main(String[] args) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.redbus.in/");
        driver.findElement(By.cssSelector(".dateText")).click();
        ArrayList<String> weekendList = getWeekEndDates("Dec 2024");
        System.out.println("Weekends dates for given Month & year are :"+weekendList);
        driver.quit();
    }

}
//https://www.linkedin.com/search/results/content/?keywords=redbus%20seat%20booking%20automation&origin=GLOBAL_SEARCH_HEADER&sid=9m3