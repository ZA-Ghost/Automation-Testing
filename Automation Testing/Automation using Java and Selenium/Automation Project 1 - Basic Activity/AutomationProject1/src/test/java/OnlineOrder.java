
// Import required classes for Selenium WebDriver and TestNG framework

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;                       // Controls the browser
import org.openqa.selenium.chrome.ChromeDriver;            // Launches Chrome browser
import org.testng.Assert;                                  // Used for test validations
import org.testng.annotations.Test;                        // Marks method as a test case

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class OnlineOrder {

    // Declare the WebDriver object to control the browser
    WebDriver driver;



    // Annotation to mark this method as a test case
    @Test
    public void TestBrowser() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Set Chrome options to disable password manager popups
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
            put("profile.password_manager_leak_detection", false);
            put("credentials_enable_service", false);
        }});

        // Initialize WebDriver with options
        WebDriver driver = new ChromeDriver(options);

        // Maximize the browser window for better visibility
        driver.manage().window().maximize();

        // Navigate to the login page of the SauceDemo website
        driver.navigate().to("https://www.saucedemo.com");

        // Enter the username 'standard_user' into the input field with ID 'user-name'
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        // Enter the password 'secret_sauce' into the input field with ID 'password'
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        // Click the login button with ID 'login-button' to submit the login form
        driver.findElement(By.id("login-button")).click();

        // Check whether the 'Products' label is displayed after login
        // This helps to confirm that the login was successful
        driver.findElement(By.xpath("//span[contains(., 'Products')]")).isDisplayed();

        // Get the text value from the span element that contains the word 'Products'
        // This is used to compare with the expected result
        String actualValue = driver.findElement(By.xpath("//span[contains(., 'Products')]")).getText();

        // Verify if the actual text is equal to 'Products'
        // If the values do not match, the test will fail
        Assert.assertEquals(actualValue, "Products");

        // Verify that the "Products" page is displayed
        String productPageText = driver.findElement(By.xpath("//span[contains(., 'Products')]")).getText();
        Assert.assertEquals(productPageText, "Products");

        // Add item to cart by clicking "Add to cart" button
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        //Click on the cart icon to go to cart/checkout page
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a")).click();

        // Verify that the "Your Cart" page is displayed
        // If the values do not match, the test will fail
        String yourCartText = driver.findElement(By.xpath("//span[contains(.,'Your Cart')]")).getText();
        Assert.assertEquals(yourCartText, "Your Cart");

        //Click the checkout button
        driver.findElement(By.id("checkout")).click();

        // Verify we are on the "Checkout: Your Information" page
        // If the values do not match, the test will fail
        String checkoutInfoText = driver.findElement(By.xpath("//span[contains(.,'Checkout: Your Information')]")).getText();
        Assert.assertEquals(checkoutInfoText, "Checkout: Your Information");

        //Fill in customer information
        driver.findElement(By.id("first-name")).sendKeys("Khwezi");
        driver.findElement(By.id("last-name")).sendKeys("Test");
        driver.findElement(By.id("postal-code")).sendKeys("1234");

        // Click Continue button
        driver.findElement(By.id("continue")).click();

        // Verify we are on the "Checkout: Overview" page
        // If the values do not match, the test will fail
        String checkoutOverviewText = driver.findElement(By.xpath("//span[contains(.,'Checkout: Overview')]")).getText();
        Assert.assertEquals(checkoutOverviewText, "Checkout: Overview");

        // Click the finish button to complete the order
        driver.findElement(By.name("finish")).click();

        // Close the browser
        //driver.quit();
    }


}
