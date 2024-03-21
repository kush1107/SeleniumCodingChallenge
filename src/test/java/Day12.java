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

 /* Day-12 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐯𝐞𝐫𝐢𝐟𝐲 𝐥𝐢𝐧𝐤 𝐜𝐨𝐮𝐧𝐭 𝐜𝐚𝐥𝐜𝐮𝐥𝐚𝐭𝐢𝐨𝐧 𝐚𝐧𝐝 𝐢𝐝𝐞𝐧𝐭𝐢𝐟𝐢𝐜𝐚𝐭𝐢𝐨𝐧 𝐨𝐟 𝐩𝐚𝐠𝐞 𝐰𝐢𝐭𝐡 𝐦𝐚𝐱𝐢𝐦𝐮𝐦 𝐥𝐢𝐧𝐤𝐬 𝐛𝐲 𝐮𝐬𝐢𝐧𝐠 𝐇𝐚𝐬𝐡𝐌𝐚𝐩 𝐜𝐨𝐧𝐜𝐞𝐩𝐭 𝐨𝐟 𝐉𝐚𝐯𝐚.

        𝐒𝐭𝐞𝐩𝐬 :
         1) 𝐍𝐚𝐯𝐢𝐠𝐚𝐭𝐞 𝐭𝐨 4 𝐰𝐞𝐛𝐬𝐢𝐭𝐞 𝐨𝐧𝐞 𝐛𝐲 𝐨𝐧𝐞 𝐰𝐡𝐢𝐜𝐡 𝐢𝐬 𝐦𝐞𝐧𝐭𝐢𝐨𝐧𝐞𝐝 𝐛𝐞𝐥𝐨𝐰 :
        - https://lnkd.in/gRnwQiz5
        - https://lnkd.in/gvXtHnEM
        - https://lnkd.in/dS393xhT
        - https://demo.guru99.com/
         y𝐨𝐮 𝐧𝐞𝐞𝐝 𝐭𝐨 𝐬𝐭𝐨𝐫𝐞 𝐚𝐥𝐥 4 𝐔𝐑𝐋𝐬 𝐢𝐧 𝐒𝐭𝐫𝐢𝐧𝐠 𝐀𝐫𝐫𝐚𝐲 𝐬𝐞𝐭.
         2) 𝐏𝐫𝐢𝐧𝐭 𝐭𝐡𝐞 𝐔𝐫𝐥 𝐨𝐟 𝐏𝐚𝐠𝐞,𝐏𝐚𝐠𝐞 𝐓𝐢𝐭𝐥𝐞 & 𝐍𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐥𝐢𝐧𝐤𝐬 𝐨𝐧 𝐭𝐡𝐚𝐭 𝐬𝐩𝐞𝐜𝐢𝐟𝐢𝐞𝐝 𝐔𝐑𝐋 𝐏𝐚𝐠𝐞(𝐇𝐨𝐦𝐞 𝐏𝐚𝐠𝐞).
         3)𝐀𝐟𝐭𝐞𝐫 𝐩𝐫𝐨𝐜𝐞𝐬𝐬𝐢𝐧𝐠 𝐚𝐥𝐥 𝐔𝐑𝐋𝐬, 𝐯𝐞𝐫𝐢𝐟𝐲 𝐭𝐡𝐚𝐭 𝐭𝐡𝐞 𝐜𝐨𝐧𝐬𝐨𝐥𝐞 𝐥𝐨𝐠 𝐝𝐢𝐬𝐩𝐥𝐚𝐲𝐬 𝐭𝐡𝐞 𝐩𝐚𝐠𝐞 𝐭𝐢𝐭𝐥𝐞 𝐰𝐢𝐭𝐡 𝐭𝐡𝐞 𝐦𝐚𝐱𝐢𝐦𝐮𝐦 𝐧𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐥𝐢𝐧𝐤𝐬.
        𝐓𝐡𝐞 𝐜𝐨𝐧𝐬𝐨𝐥𝐞 𝐬𝐡𝐨𝐮𝐥𝐝 𝐝𝐢𝐬𝐩𝐥𝐚𝐲 𝐢𝐧 𝐭𝐡𝐢𝐬 𝐟𝐨𝐫𝐦: "𝐏𝐚𝐠𝐞 𝐰𝐢𝐭𝐡 𝐌𝐚𝐱𝐢𝐦𝐮𝐦 𝐋𝐢𝐧𝐤𝐬: [𝐏𝐚𝐠𝐞 𝐓𝐢𝐭𝐥𝐞] - [𝐍𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐋𝐢𝐧𝐤𝐬] 𝐥𝐢𝐧𝐤𝐬".
         𝐂𝐡𝐚𝐥𝐥𝐞𝐧𝐠𝐞: 𝘠𝘰𝘶 𝘯𝘦𝘦𝘥 𝘵𝘰 𝘶𝘴𝘦 𝘏𝘢𝘴𝘩𝘔𝘢𝘱 𝘊𝘰𝘯𝘤𝘦𝘱𝘵 𝘧𝘰𝘳 𝘤𝘰𝘮𝘱𝘢𝘳𝘪𝘯𝘨 𝘵𝘩𝘦 𝘔𝘢𝘹𝘪𝘮𝘶𝘮 𝘯𝘶𝘮𝘣𝘦𝘳 𝘰𝘧 𝘭𝘪𝘯𝘬𝘴 of 𝘢𝘭𝘭 𝘵𝘩𝘦 4 𝘱𝘢𝘨𝘦 𝘜𝘙𝘓𝘴 by storing in entry set.  */


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
