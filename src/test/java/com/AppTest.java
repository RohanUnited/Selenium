package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {

    public WebDriver driver;

    @Test
    public void testGoogleTitle() {
        System.out.println("Starting testGoogleTitle...");

        // Use WebDriverManager to setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        System.out.println("ChromeDriver setup via WebDriverManager.");

        // Configure ChromeOptions for Headless Mode (essential for Codespaces)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        options.addArguments("--disable-gpu"); // Recommended for headless mode
        options.addArguments("--no-sandbox"); // Often necessary in CI/CD environments like Codespaces
        options.addArguments("--window-size=1920,1080"); // Set a default window size for rendering consistency
        options.addArguments("--disable-dev-shm-usage"); // Another common argument for containers/headless envs

        // Initialize the ChromeDriver with the configured options
        driver = new ChromeDriver(options);
        System.out.println("Headless Chrome browser launched.");

        // Navigate to Google
        driver.get("https://www.google.com");
        System.out.println("Navigated to Google.com");

        // Get the page title
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        // Assert that the title contains "Google"
        Assert.assertTrue(pageTitle.contains("Google"), "Page title should contain 'Google'");
        System.out.println("Assertion passed: Page title contains 'Google'.");
    }

    // This method runs after each test method to clean up
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Close the browser
            System.out.println("Browser closed.");
        }
    }
}