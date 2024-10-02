package com.seleniumsessions.selenium_newimplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.Network;
import org.openqa.selenium.bidi.network.ResponseDetails;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.performance.Performance;
import org.openqa.selenium.devtools.v120.performance.model.Metric;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RelativeLocators {

    public static ChromeDriver driver;
    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Username']")));
        username.sendKeys("Admin");
        WebElement loginBtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        //By using Relative Locator -above()
        WebElement password = driver.findElement(RelativeLocator.with(By.tagName("input")).above(loginBtn));
        password.sendKeys("admin123");
        loginBtn.click();
        driver.quit();
        for (Metric m : metricList) {
            System.out.println(m.getName() + " = " + m.getValue());
        }
    }
}
