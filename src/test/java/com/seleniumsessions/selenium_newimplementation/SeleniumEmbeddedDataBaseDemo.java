package com.seleniumsessions.selenium_newimplementation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.h2.tools.Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class SeleniumEmbeddedDataBaseDemo {
    private Connection connection;
    // Start H2 Web Console
    private Server h2WebServer;

    @BeforeTest
    public void setUpDatabase() throws SQLException, IOException {
        // Initialize embedded H2 database
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        Statement stmt = connection.createStatement();

        // Create User tables
        String createTableSQL1 = "CREATE TABLE Users(username VARCHAR(255), password VARCHAR(255));";
        stmt.execute(createTableSQL1);
        //Insert Data
        String insertDataSQL1 = "INSERT INTO Users VALUES('testuser', 'testpass');";
        stmt.execute(insertDataSQL1);

        // Create Order tables
        String createTableSQL2 = "CREATE TABLE Orders(order_id INT, product_name VARCHAR(255), quantity INT);";
        stmt.execute(createTableSQL2);
        //Insert Data
        String insertDataSQL2 = "INSERT INTO Orders VALUES(1, 'Laptop', 2);";
        stmt.execute(insertDataSQL2);

        // Add more table creation as needed

        // Start H2 Web Console
        h2WebServer = Server.createWebServer().start();
    }

    @Test
    public void performLoginTest() throws SQLException {
        // Retrieve data from the database
        String selectSQL = "SELECT * FROM Users";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);

        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");

            // Perform the login test using Selenium
            // driver.findElement(By.id("username")).sendKeys(username);
            // driver.findElement(By.id("password")).sendKeys(password);
            // driver.findElement(By.id("login")).click();
        }
    }

    @AfterTest
    public void exportDataToExcel() throws SQLException, IOException {
        // Create Excel workbook
        Workbook workbook = new XSSFWorkbook();

        // Define tables to be exported
        String[] tablesToExport = {"Users", "Orders"};

        // Loop through each specified table and export to Excel
        for (String tableName : tablesToExport) {
            System.out.println("Exporting table: " + tableName); // Print table name being exported

            // Create a sheet for each table
            Sheet sheet = workbook.createSheet(tableName);

            // Query data from the current table
            String selectSQL = "SELECT * FROM " + tableName;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL);

            // Write the header row
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            Row header = sheet.createRow(0);
            for (int i = 1; i <= columnCount; i++) {
                header.createCell(i - 1).setCellValue(rsMetaData.getColumnName(i));
            }

            // Write data rows
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    row.createCell(i - 1).setCellValue(rs.getString(i));
                }
            }
        }

        // Save the Excel file to the local directory
        try (FileOutputStream fileOut = new FileOutputStream("TestData.xlsx")) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    @AfterTest
    public void tearDown() throws SQLException, IOException {
        // Drop the database
        Statement stmt = connection.createStatement();
        stmt.execute("DROP ALL OBJECTS DELETE FILES");

        // Close the connection
        connection.close();
    }
}
