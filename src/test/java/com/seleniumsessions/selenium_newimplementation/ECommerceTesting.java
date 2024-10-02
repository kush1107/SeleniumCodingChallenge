package com.seleniumsessions.selenium_newimplementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class ECommerceTesting {


    public static WebDriver driver;
    public static Faker fk = new Faker();
    public static String email;
    public static String password,hashedPassword;


    @BeforeSuite
    public void setup()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--blink-settings=imagesEnabled=false");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(35));
        driver.get("https://magento.softwaretestingboard.com/");

        email = fk.internet().safeEmailAddress();
        password = "Test@1999";
        //Password Encryption using BCrypt Dependency Maven (Sometimes client wants to encrypt it)
        hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        password=hashedPassword;
    }
    @AfterSuite
    public void tearDown()
    {
        driver.quit();
    }

    @Test(priority =2,dependsOnMethods ="CreateAccountTest")
    public void LoginInTest()
    {
        driver.manage().deleteAllCookies();
        driver.get("https://magento.softwaretestingboard.com/");
        WebLocatorActions webLocatorActions = new WebLocatorActions(driver);
        webLocatorActions.waitForElementToBeClickable("xpath","(//a[contains(text(),'Sign In')])[1]");
        webLocatorActions.clickElement("xpath","(//a[contains(text(),'Sign In')])[1]");
        webLocatorActions.waitForElementToBeVisible("xpath","//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]");
        webLocatorActions.enterText("id","email",email);



        webLocatorActions.enterText("xpath","//fieldset[@class='fieldset login']//input[@id='pass']",password);
        webLocatorActions.clickElement("xpath","//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]");

        webLocatorActions.waitForElementToBeVisible("xpath","(//span[contains(normalize-space(),'Welcome')])[1]");
    }

    @Test(priority =1)
    public void CreateAccountTest()
    {
        WebLocatorActions webLocatorActions = new WebLocatorActions(driver);
        webLocatorActions.waitForElementToBeClickable("xpath","(//a[normalize-space()='Create an Account'])[1]");
        webLocatorActions.clickElement("xpath","(//a[normalize-space()='Create an Account'])[1]");

        webLocatorActions.waitForElementToBeVisible("xpath","//input[@id='firstname']");
        webLocatorActions.enterText("xpath","//input[@id='firstname']",fk.name().firstName());
        webLocatorActions.enterText("xpath","//input[@id='lastname']",fk.name().lastName());

        webLocatorActions.enterText("xpath","//input[@id='email_address']",email);
        webLocatorActions.enterText("xpath","//input[@id='password']",password);
        webLocatorActions.enterText("xpath","//input[@id='password-confirmation']",password);
        webLocatorActions.clickElement("xpath","//button[@title='Create an Account']//span[contains(text(),'Create an Account')]");

        webLocatorActions.waitForElementToBeVisible("xpath","(//div[@role='alert']//div[contains(normalize-space(),'Thank you for registering with Main Website Store')])[1]");
    }



}


class WebLocatorActions {

    private WebDriver driver;
    private static long explicitTimeout = 15;

    // Constructor to initialize WebDriver
    public WebLocatorActions(WebDriver driver) {
        this.driver = driver;
    }

    public  WebElement findElement(String locatorType, String locatorValue) {
        return switch (locatorType.toLowerCase()) {
            case "id" -> driver.findElement(By.id(locatorValue));
            case "name" -> driver.findElement(By.name(locatorValue));
            case "classname" -> driver.findElement(By.className(locatorValue));
            case "tagname" -> driver.findElement(By.tagName(locatorValue));
            case "linktext" -> driver.findElement(By.linkText(locatorValue));
            case "partiallink" -> driver.findElement(By.partialLinkText(locatorValue));
            case "css" -> driver.findElement(By.cssSelector(locatorValue));
            case "xpath" -> driver.findElement(By.xpath(locatorValue));
            default -> throw new IllegalArgumentException("Unknown locator type: " + locatorType);
        };
    }

    public   void enterText(String locatorType, String locatorValue, String value) {
        WebElement ele = findElement(locatorType, locatorValue);
        if (ele.isEnabled()) {
            ele.clear();
            ele.sendKeys(value);
        }
    }


    public  void clickElement(String locatorType, String locatorValue) {
        WebElement ele = findElement(locatorType, locatorValue);
        if (ele.isEnabled() & ele.isDisplayed()) {
            ele.click();
        }
    }

    public  void checkElement(String locatorType, String locatorValue) {
        WebElement ele = findElement(locatorType, locatorValue);
        if (ele.isEnabled() && ele.isDisplayed() && !ele.isSelected()) {
            ele.click();
        }
    }

    public  void selectFromDropDownByValue(String locatorType, String locatorValue, String value) {
        WebElement ele = findElement(locatorType, locatorValue);
        Select select = new Select(ele);
        if (ele.isEnabled()) {
            select.selectByValue(value);
        }
    }

    public  void selectFromDropDownByIndex(String locatorType, String locatorValue, int index) {
        WebElement ele = findElement(locatorType, locatorValue);
        Select select = new Select(ele);
        if (ele.isEnabled()) {
            select.selectByIndex(index);
        }
    }

    public  void moveToElement(String locatorType, String locatorValue) {
        WebElement ele = findElement(locatorType, locatorValue);
        Actions action = new Actions(driver);
        action.moveToElement(ele).pause(200).perform();
    }


    public  boolean elementIsEnabled(String locatorType, String locatorValue) {
        WebElement ele = findElement(locatorType, locatorValue);
        return ele.isEnabled() ? true : false;
    }

    public  boolean elementIsDisplayed(String locatorType, String locatorValue) {
        WebElement ele = findElement(locatorType, locatorValue);
        return ele.isDisplayed() ? true : false;
    }

    public  void waitForElementToBeClickable(String locatorType, String locatorValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout));
        wait.until(ExpectedConditions.elementToBeClickable(findElement(locatorType, locatorValue)));
    }

    public  void waitForElementToBeVisible(String locatorType, String locatorValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout));
        wait.until(ExpectedConditions.visibilityOf(findElement(locatorType, locatorValue)));
    }
}