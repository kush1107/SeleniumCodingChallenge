import io.github.bonigarcia.wdm.WebDriverManager;
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

public class Day14 {

    public static WebDriver driver;

    @BeforeSuite
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
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
        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).sendKeys("03/19/2024");

        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).sendKeys("03/20/2024");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span//span[normalize-space()='Search flights']")));
        driver.findElement(By.xpath("//button//span//span[normalize-space()='Search flights']")).click();


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
}
