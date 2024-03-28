import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  ✅𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨:

𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐯𝐞𝐫𝐢𝐟𝐲 𝐊𝐅𝐂 𝐥𝐨𝐜𝐚𝐭𝐢𝐨𝐧𝐬 𝐝𝐢𝐬𝐩𝐥𝐚𝐲𝐞𝐝 𝐛𝐚𝐬𝐞𝐝 𝐨𝐧 𝐆𝐞𝐨𝐥𝐨𝐜𝐚𝐭𝐢𝐨𝐧.

𝐓𝐞𝐬𝐭 𝐟𝐨𝐫 𝐛𝐨𝐭𝐡 𝐭𝐡𝐞 𝐠𝐢𝐯𝐞𝐧 𝐆𝐞𝐨𝐋𝐨𝐜𝐚𝐭𝐢𝐨𝐧𝐬 𝐚𝐬 𝐛𝐞𝐥𝐨𝐰 :

1) 𝐒𝐞𝐭 𝐥𝐨𝐜𝐚𝐭𝐢𝐨𝐧 𝐢𝐧 𝐆𝐞𝐨𝐋𝐨𝐜𝐚𝐭𝐢𝐨𝐧 𝐚𝐬 𝐠𝐢𝐯𝐞𝐧 𝐛𝐞𝐥𝐨𝐰 𝐢𝐧 𝐲𝐨𝐮𝐫 𝐬𝐜𝐫𝐢𝐩𝐭:
latitude =38.9072
longitude= -77.0369
accuracy = 100

2) 𝐒𝐞𝐭 𝐥𝐨𝐜𝐚𝐭𝐢𝐨𝐧 𝐢𝐧 𝐆𝐞𝐨𝐋𝐨𝐜𝐚𝐭𝐢𝐨𝐧 𝐚𝐬 𝐠𝐢𝐯𝐞𝐧 𝐛𝐞𝐥𝐨𝐰 𝐢𝐧 𝐲𝐨𝐮𝐫 𝐬𝐜𝐫𝐢𝐩𝐭:
latitude =19.076090
longitude = 72.877426
accuracy =100

𝐒𝐭𝐞𝐩𝐬:
1)Navigate to: https://lnkd.in/dvHJEV-a
2)Click on the "use my location" button to allow the browser to access the current location.
3)Wait for the page to display the locations near the detected location.
4)Verify that the locations displayed on the page are within a reasonable distance from the detected location or no result is found.  */


public class Day17 {
    public static WebDriver driver;

    @BeforeClass
    public void setup()
    {

        driver = new ChromeDriver();
        setGeolocation(driver, 19.076090, 72.877426, 100);
        //Change according to testcase it is need to be set at initial for geolocation testing
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://locations.kfc.com/search");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void GeoLocationTest()
    {
        WebElement geolocateButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable((By.xpath("//button[contains(normalize-space(),'use my location')]"))));

        // Click on the geolocate trigger button
        geolocateButton.click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));


        try {
           WebElement results = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[contains(@class,'Teaser')]")));
            if (results.isDisplayed()) {
                List<WebElement> locationLists = driver.findElements(By.xpath("//article[contains(@class,'Teaser')]"));
                for (WebElement location : locationLists) {
                    System.out.println(location.getText() + "\n");
                }
                System.out.println("Test passed: Locations near me are displayed.");
            }
        } catch (Exception e) {
            WebElement noLocationMsg = driver.findElement(By.xpath("//div[@class='Locator-noResults']"));
            String actualText = noLocationMsg.getText();
            if(actualText.contains("Sorry"))
            {
                System.out.println("Test passed: No locations found near me.");
            }
        }

    }


    // Method to set geolocation coordinates
    private static void setGeolocation(WebDriver driver, double latitude, double longitude, int accuracy) {
        Map<String, Object> geoloc = new HashMap<>();
        geoloc.put("latitude", latitude);
        geoloc.put("longitude", longitude);
        geoloc.put("accuracy", accuracy);
        ((ChromeDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride", geoloc);
    }

}
