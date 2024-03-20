import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Day1 {
    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
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
    public void AuthenticationTest()
    {
        ((HasAuthentication) driver)
                .register(() -> new UsernameAndPassword("admin", "admin"));
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        WebElement text = driver.findElement(By.xpath("//p"));
        Assert.assertEquals(text.getText(),"Congratulations! You must have the proper credentials.");

    }
}
