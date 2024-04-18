package com.seleniumsessions;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

/*✅𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨:
𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐀𝐝𝐝 𝐚𝐧𝐝 𝐫𝐞𝐦𝐨𝐯𝐞 𝐭𝐚𝐠𝐬 𝐚𝐧𝐝 𝐚𝐬𝐬𝐞𝐫𝐭 𝐭𝐚𝐠'𝐬 𝐩𝐫𝐞𝐬𝐞𝐧𝐜𝐞 𝐚𝐧𝐝 𝐜𝐨𝐮𝐧𝐭.
✅𝐒𝐭𝐞𝐩𝐬 :

1) 𝐍𝐚𝐯𝐢𝐠𝐚𝐭𝐞 𝐭𝐨 𝐰𝐞𝐛𝐬𝐢𝐭𝐞:
https://lnkd.in/ddcs5QEj
2) 𝐏𝐫𝐢𝐧𝐭 𝐭𝐡𝐞 𝐜𝐨𝐮𝐧𝐭 𝐧𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐭𝐡𝐞 𝐭𝐚𝐠𝐬 & 𝐑𝐞𝐦𝐨𝐯𝐞 𝐚𝐥𝐥 𝐭𝐡𝐞 𝐭𝐚𝐠𝐬 𝐢𝐧𝐬𝐢𝐝𝐞 𝐭𝐡𝐞 𝐛𝐨𝐱.
3) 𝐀𝐝𝐝 10 𝐭𝐚𝐠𝐬 𝐨𝐟 𝐚𝐧𝐲 𝐤𝐞𝐲𝐰𝐨𝐫𝐝𝐬 𝐲𝐨𝐮 𝐥𝐢𝐤𝐞.
4) 𝐕𝐞𝐫𝐢𝐟𝐲 𝐭𝐡𝐚𝐭 𝐂𝐨𝐮𝐧𝐭 𝐨𝐟 𝐧𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐭𝐚𝐠𝐬 𝐢𝐬 "0" 𝐚𝐧𝐝 𝐩𝐫𝐢𝐧𝐭 𝐢𝐧 𝐜𝐨𝐧𝐬𝐨𝐥𝐞.
5) 𝐍𝐨𝐰 𝐑𝐞𝐦𝐨𝐯𝐞 𝐚𝐥𝐥 𝐭𝐚𝐠𝐬 𝐚𝐧𝐝 𝐭𝐫𝐲 𝐭𝐨 𝐢𝐧𝐩𝐮𝐭 "<𝐬𝐜𝐫𝐢𝐩𝐭>𝐚𝐥𝐞𝐫𝐭()</𝐬𝐜𝐫𝐢𝐩𝐭>" 𝐚𝐬 𝐤𝐞𝐲𝐰𝐨𝐫𝐝 𝐚𝐧𝐝 𝐚𝐝𝐝 𝐢𝐭.
6)𝐓𝐫𝐲 𝐭𝐨 𝐠𝐞𝐭 𝐭𝐡𝐚𝐭 𝐯𝐚𝐥𝐮𝐞 𝐨𝐟 𝐭𝐡𝐞 𝐭𝐚𝐠 𝐚𝐧𝐝 𝐩𝐫𝐢𝐧𝐭 𝐢𝐭 𝐨𝐧 𝐜𝐨𝐧𝐬𝐨𝐥𝐞. 𝐀𝐫𝐞 𝐲𝐨𝐮 𝐚𝐛𝐥𝐞 𝐭𝐨 𝐟𝐞𝐭𝐜𝐡 𝐭𝐡𝐞 𝐯𝐚𝐥𝐮𝐞 𝐨𝐫 𝐧𝐨𝐭? 𝐈𝐟 𝐲𝐞𝐬 𝐭𝐡𝐞𝐧 𝐰𝐡𝐚𝐭 𝐢𝐬 𝐩𝐫𝐢𝐧𝐭𝐞𝐝 𝐨𝐧 𝐜𝐨𝐧𝐬𝐨𝐥𝐞?  */

public class Day11 {
    public static WebDriver driver;

    Faker faker = new Faker();
    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
        driver.get("https://qaplayground.dev/apps/tags-input-box/");
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void TagsTest()
    {
        int number_of_tags=10;
        List<WebElement> tags=driver.findElements (By.xpath("//div[@class='content']//ul/li"));
        WebElement removeAll_button=driver.findElement(By.xpath("//button [text() = 'Remove All']"));
        System.out.println("Total No.of Tags:"+tags.size());
        removeAll_button.click();
        List<WebElement> tags1=driver.findElements (By.xpath("//div[@class='content']//ul/li"));
        System.out.println("number of tags after removeall-->"+tags1.size());
        WebElement textfield=driver.findElement(By.xpath("//input[@type='text']"));

        for (int i = 1; i <=number_of_tags; i++) {
            String alphanumericString = faker.lorem().characters(10, true, true);
            System.out.println(alphanumericString);
            textfield.sendKeys (alphanumericString);
            textfield.sendKeys(Keys.ENTER);
        }

        List<WebElement> tags2=driver.findElements (By.xpath("//div[@class='content']//ul/li"));
        Assert.assertEquals(tags2.size(), number_of_tags);
        removeAll_button.click();
        textfield.sendKeys("<script>alert()</script>");
        textfield.sendKeys (Keys.ENTER);
        WebElement element=driver.findElement(By.xpath("//div[@class='content']//ul/li"));
        System.out.println(element.getAttribute("innerHTML"));
    }
}
