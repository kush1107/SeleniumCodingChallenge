import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class OKTA_Login {
    public static WebDriver driver;
    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://12@dx8otu6loe5s1.cloudfront.net/auth/signin");
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='signInFormUsername'])[2]")));
        Boolean Loginfield =  driver.findElement(By.xpath("(//input[@id='signInFormUsername'])[2]")).isDisplayed();
        if(Loginfield)
        {
            driver.findElement(By.xpath("(//input[@id='signInFormUsername'])[2]")).sendKeys("somnath.roy");
            driver.findElement(By.xpath("(//input[@id='signInFormPassword'])[2]")).sendKeys("Zebra@12");
            driver.findElement(By.xpath("(//input[@name='signInSubmitButton'])[2]")).click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter your email address or Username']")));
        driver.findElement(By.xpath("//input[@placeholder='Enter your email address or Username']")).sendKeys("Somnath.Roy");
        driver.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys("Test123@!");
        driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void AccountCreate()
    {
        System.out.println("Landing on Home Page");
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[normalize-space()='Customer'])[1]")));

        driver.findElement(By.xpath("(//a[normalize-space()='Customer'])[1]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Create Customer']")));
        driver.findElement(By.xpath("//button[normalize-space()='Create Customer']")).click();
        //Company
        selectOptionFromDropdownLabel("Company", "021");

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Process')]")));


        //Overview
        selectOptionFromDropdownSection("Overview", "DISTRIBUTOR");
        WebElement businessName =  new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Business Name']")));
        businessName.click();
        businessName.sendKeys("I3Verticals");

        driver.findElement(By.xpath("//input[@placeholder='Legacy Customer Code']")).sendKeys("48003");

       /* //Contact
        clickAddWithRelative("Contacts");
        selectOptionFromDropdownSection("Contacts", "EMAIL");
        selectOptionFromDropdownLabel("Status", "VERIFIED");
        driver.findElement(By.xpath("//input[@id='aainlinecheckk_primaryind']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("test@gmail.com");
        clickDoneWithRelative("Contacts");*/

        //Identifiers
        clickAddWithRelative("Identifiers");
        selectOptionFromDropdownSection("Identifiers", "VISA");

        driver.findElement(By.xpath("//input[@placeholder='Value']")).sendKeys("453453454");
        //driver.findElement(By.xpath("//input[@id='aainlinecheckk_primaryind']")).click();
        selectOptionFromDropdownLabel("Primary", "VERIFIED");
        clickDoneWithRelative("Identifiers");

        //Saving
        WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));
        clickElementWithJavaScript(driver,ele);

        WebElement ele2 = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Customer has been successfully created.')]")));
        String s = ele2.getText();
        System.out.println(s);

        /*//Addresses
        scrollToBottom(driver);
        clickAddWithRelative("Addresses");

        WebElement nameField =  new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Name']")));
        nameField.click();
        nameField.sendKeys("TestQA");

        selectOptionFromDropdownSectionRightOf("Address Type", "PREM_ADDR");

        new Actions(driver)
                .scrollToElement(driver.findElement(By.xpath("//input[@placeholder='Street Number']")))
                .perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        WebElement streetNumber = driver.findElement(By.xpath("//input[@placeholder='Street Number']"));
        streetNumber.click();
        streetNumber.sendKeys("Test34");


        selectOptionFromDropdownSectionRightOf("Pre Direction", "E");
        new Actions(driver)
                .scrollToElement(driver.findElement(By.xpath("//input[@placeholder='Street Name']")))
                .perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        WebElement streetName = driver.findElement(By.xpath("//input[@placeholder='Street Name']"));
        streetName.click();
        streetName.sendKeys("AtlantisK10");
        selectOptionFromDropdownSectionRightOf("Suffix", "ALY");

        selectOptionFromDropdownSectionRightOf("Post Direction", "E");
        selectOptionFromDropdownSectionRightOf("Unit Type", "APT");


        WebElement unit = driver.findElement(By.xpath("//input[@placeholder='Unit']"));
        unit.click();
        unit.sendKeys("5");

        WebElement postalCode = driver.findElement(By.xpath("//input[@placeholder='Postal Code']"));
        postalCode.click();
        postalCode.sendKeys("45642");

        WebElement select_Auto =  new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='search-dropdown']")));
        select_Auto.click();
       WebElement input_Search =  new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search']")));
        input_Search.click();
        input_Search.sendKeys("kansas");
        WebElement Searchtext =  new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Kansas')]")));
        Searchtext.click();



        selectOptionFromDropdownSectionRightOf("Status", "VERIFIED");
        selectOptionFromDropdownSectionRightOf("Country", "USA");

        selectOptionFromDropdownSectionRightOf("State", "KS");

        enterTextWithJavaScript(driver, driver.findElement(By.xpath("(//input[@placeholder='MM/DD/YYYY'])[1]")),"01/28/2024");
        enterTextWithJavaScript(driver, driver.findElement(By.xpath("(//input[@placeholder='MM/DD/YYYY'])[2]")),"06/02/2024");

        clickElementWithJavaScript(driver, driver.findElement(RelativeLocator.with(By.xpath("//button[@type='button'][normalize-space()='Done']")).toRightOf(By.xpath("//div[normalize-space()='Addresses']"))));

        System.out.println("Test Passed");*/
    }

    public static void selectOptionFromDropdownSection(String dropSelectName, String value) {
        WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(RelativeLocator.with(By.tagName("select")).below(By.xpath("//h5[normalize-space()='"+dropSelectName+"']"))));
        Select dropdown = new Select(ele);
        dropdown.selectByValue(value);
    }

    public static void selectOptionFromDropdownLabel(String dropSelectName, String value) {
        WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(RelativeLocator.with(By.tagName("select")).below(By.xpath("//div[contains(text(),'"+dropSelectName+"')]"))));
        Select dropdown = new Select(ele);
        dropdown.selectByValue(value);
    }


    public static void selectOptionFromDropdownSectionRightOf(String labelName, String value) {
        WebElement  ele = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(RelativeLocator.with(By.tagName("select")).toRightOf(By.xpath("//span[normalize-space()='"+labelName+"']"))));
        Select dropdown = new Select(ele);
        dropdown.selectByValue(value);
    }

    public static void clickAddWithRelative(String addButtonSection) {
        WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(RelativeLocator.with(By.xpath("//button[@type='button'][normalize-space()='Add']")).toRightOf(By.xpath("//div[normalize-space()='"+addButtonSection+"']"))));
        ele.click();

    }
    public static void clickDoneWithRelative(String DoneButtonSection) {
        WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(RelativeLocator.with(By.xpath("//button[@type='button'][normalize-space()='Done']")).toRightOf(By.xpath("//div[normalize-space()='"+DoneButtonSection+"']"))));
        ele.click();

    }

    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public static void enterTextWithJavaScript(WebDriver driver, WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        js.executeScript("arguments[0].value = arguments[1];", element, text);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public static void clickElementWithJavaScript(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

}
