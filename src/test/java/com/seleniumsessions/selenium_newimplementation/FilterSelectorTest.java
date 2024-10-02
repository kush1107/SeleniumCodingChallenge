package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FilterSelectorTest {

    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }
    /*@AfterClass
    public void tearDown()
    {
        driver.quit();
    }*/

    @Test
    public void FilterSelectTest() {
        // Navigate to the webpage
        try {
            ScreenRecorderUtil.startRecord("FilterSelectTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.get("https://www.t-mobile.com/commerce/tablets");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//legend[normalize-space()='Brands']/../..//button[normalize-space()='Show more']")));
        select_Filter("Brands", "TCL");
       /* select_Filter("Deals", "New","Specialoffers");
        select_Filter("OS", "Other");
        select_Filter("Deals", "all");
        select_Filter("Deals", "Invalid Options");
        select_Filter("Invalid Category", "TCL");*/


        try {
            ScreenRecorderUtil.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




















    public void select_Filter(String filterType, String... options) {
        if (options.length == 0) return;

        // Convert filter type to lowercase to avoid case sensitivity issues
        filterType = filterType.toLowerCase();

        // Determine the base locator for the filter type
        String baseLocator = "";
        switch (filterType) {
            case "deals":
                baseLocator = "//legend[normalize-space()='Deals']";
                break;
            case "brands":
                baseLocator = "//legend[normalize-space()='Brands']";
                break;
            case "os":
                baseLocator = "//legend[normalize-space()='Operating system']";
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }

        // If "all" is specified, select all checkboxes in the filter
        if (options[0].equalsIgnoreCase("all")) {
            List<WebElement> checkboxes = driver.findElements(By.xpath(baseLocator + "/../..//span[@class='phx-field__indicator']"));
            WebElement showMoreBtn = findElementIfExists(driver, By.xpath(baseLocator + "/../..//button[normalize-space()='Show more']"));

            if (showMoreBtn != null && showMoreBtn.isDisplayed()) {
                    scrollToAndClick(showMoreBtn);
                }

            for (WebElement checkbox : checkboxes) {
                if (!checkbox.isSelected()) {
                    scrollToAndClick(checkbox);
                }
            }
        } else {
            // Otherwise, select specific options
            for (String option : options) {
                WebElement checkbox = findElementIfExists(driver,By.xpath(baseLocator + "/../..//label[@for='filter-" + option + "']//span[@class='phx-field__indicator']"));
                WebElement showMoreBtn = findElementIfExists(driver, By.xpath(baseLocator + "/../..//button[normalize-space()='Show more']"));

                if (checkbox!=null) {
                    if (!checkbox.isDisplayed()) {
                        if (showMoreBtn != null && showMoreBtn.isDisplayed()) {
                            scrollToAndClick(showMoreBtn);
                        }
                    }

                    if (!checkbox.isSelected()) {
                        scrollToAndClick(checkbox);
                    }
                } else {
                    throw new SkipException("Filter option not found: " + option);
                }
            }
        }
    }

    private WebElement findElementIfExists(WebDriver driver, By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.isEmpty() ? null : elements.get(0);
    }

    private void scrollToAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {;
        }

        element.click();
    }
}
