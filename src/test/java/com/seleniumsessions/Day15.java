package com.seleniumsessions;

import com.github.javafaker.Faker;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

/*
Day -15 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐟𝐢𝐥𝐥 𝐭𝐡𝐞 𝐟𝐨𝐫𝐦 𝐚𝐧𝐝 𝐬𝐮𝐛𝐦𝐢𝐭 𝐢𝐭 𝐮𝐬𝐢𝐧𝐠 𝐀𝐏𝐈 𝐝𝐚𝐭𝐚.

        𝐒𝐭𝐞𝐩𝐬:
        1)Navigate to :
        https://lnkd.in/dk7P-rYW

        2)Fill out the entire form and submit it, but there is a twist.
        3)There are 2 fields "First Name" & "Last Name" - you need to fetch that "First Name" & "Last Name" from the GET APi given below :
        GET API- https://randomuser.me/api/
        4) Store the "First Name" & "Last Name" values fetched from API in some variable and use that in the "First Name" & "Last Name" fields in the form while filling it.
*/

public class Day15 {

    public static WebDriver driver;
    public static String firstName;
    public static String lastName;

    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void fetchVinAndInputTest() throws IOException {
        driver.get("https://fs2.formsite.com/meherpavan/form2/index.html?1537702596407");

        fetchFirstAndlastName();

        Faker faker = new Faker();

        /*

        With Rest-Assured.io
        Response response = RestAssured
                .given()
                .get("https://randomuser.me/api/")
                .then().extract().response();

        String firstName = response.getBody().jsonPath().getString("results[0].name.first");
        String lastName = response.getBody().jsonPath().getString("results[0].name.last");  */

        driver.findElement(By.id("RESULT_TextField-1")).sendKeys(firstName);
        driver.findElement(By.id("RESULT_TextField-2")).sendKeys(lastName);
        driver.findElement(By.id("RESULT_TextField-3")).sendKeys(faker.phoneNumber().phoneNumber());
        driver.findElement(By.id("RESULT_TextField-4")).sendKeys(faker.address().country());
        driver.findElement(By.id("RESULT_TextField-5")).sendKeys(faker.address().city());
        driver.findElement(By.id("RESULT_TextField-6")).sendKeys(faker.internet().emailAddress());

        WebElement gender = driver.findElement(By.id("RESULT_RadioButton-7_0"));
        clickUsingJsExecutor(driver, gender);

        WebElement days = driver.findElement(By.id("RESULT_CheckBox-8_1"));
        clickUsingJsExecutor(driver, days);

        WebElement element2 = driver.findElement(By.id("RESULT_CheckBox-8_2"));
        clickUsingJsExecutor(driver, element2);

        WebElement drpDown = driver.findElement(By.id("RESULT_RadioButton-9"));
        clickUsingJsExecutor(driver, drpDown);
        Select select = new Select(drpDown);
        select.selectByVisibleText("Morning");

        driver.findElement(By.className("file_upload")).sendKeys(System.getProperty("user.dir") +"/src/main/resources/TestFile.txt");
        driver.findElement(By.xpath("//input[@id='FSsubmit']")).submit();
    }



    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    public void fetchFirstAndlastName()
    {
        try {
            String apiUrl = "https://randomuser.me/api/";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray resultsArray = jsonResponse.getJSONArray("results");

                JSONObject firstResult = resultsArray.getJSONObject(0);
                JSONObject nameObject = firstResult.getJSONObject("name");
                firstName = nameObject.getString("first");
                lastName = nameObject.getString("last");

                // Print the extracted fields
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
            } else {
                System.out.println("Failed to fetch data from the API. Response code: " + responseCode);
            }
        } catch (Exception e) {
        }
    }
    static void clickUsingJsExecutor(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
    }


