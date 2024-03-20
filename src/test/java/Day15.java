import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.time.Duration;
import java.util.stream.Collectors;


public class Day15 {

    public static WebDriver driver;
    public static String firstName;
    public static String lastName;

    @BeforeClass
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void fetchVinAndInputTest() throws IOException {
        driver.get("https://fs2.formsite.com/meherpavan/form2/index.html?1537702596407");
        fetchFirstAndlastName();


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
    }


