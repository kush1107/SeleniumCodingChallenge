import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {
    public static WebDriver driver;
    // Map to store page titles and their corresponding link counts
    Map<String, Integer> pageTitleLinkCountMap = new HashMap<>();
    // URLs
    String[] pageURLs = {
            "https://www.lambdatest.com/blog/selenium-best-practices-for-web-testing/",
            "https://www.ministryoftesting.com/articles/websites-to-practice-testing",
            "https://naveenautomationlabs.com/opencart/",
            "https://demo.guru99.com/"
    };


    @BeforeClass
    public void setup()
    {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("-headless");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }



    @Test
    public void linkVerificationTest()  {
        driver.get("https://www.ministryoftesting.com/articles/websites-to-practice-testing");
        //Fetching Footer Links
        for (String pageURL : pageURLs) {
            System.out.println("Navigating to URL:"+pageURL);
            driver.get(pageURL);
            String pageTitle = driver.getTitle();
            List<WebElement> links = driver.findElements(By.tagName("a"));
            int linkCount = links.size();
            System.out.println("Page Title: " + pageTitle + ", Number of Links: " + linkCount);
            pageTitleLinkCountMap.put(pageTitle, linkCount);
        }

        // Find page title with maximum link count
        int maxLinkCount = 0;
        String maxLinkPageTitle = null;
        for (Map.Entry<String, Integer> entry : pageTitleLinkCountMap.entrySet()) {
            if (entry.getValue() >= maxLinkCount) {
                maxLinkCount = entry.getValue();
                maxLinkPageTitle = entry.getKey();
            }
        }
        System.out.println("=========================================================================");
        // Print the page title with maximum link count
        System.out.println("Page with Maximum Links: " + maxLinkPageTitle + " - " + maxLinkCount + " links)");






    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
