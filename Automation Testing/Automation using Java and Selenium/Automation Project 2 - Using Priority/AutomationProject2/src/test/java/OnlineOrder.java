import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class OnlineOrder {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");  // Suppress browser popups
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // Suppress automation warning

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test(priority = 1)
    public void testLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Verify we are on inventory page
        WebElement title = driver.findElement(By.className("title"));
        Assert.assertEquals(title.getText(), "Products", "Login failed or wrong page.");
    }

    @Test(priority = 2)
    public void testAddToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Verify that the cart count is updated
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "1", "Item was not added to cart.");
    }

    @Test(priority = 3)
    public void testGoToCartPage() {
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a")).click();

        WebElement cartTitle = driver.findElement(By.xpath("//span[contains(text(),'Your Cart')]"));
        Assert.assertTrue(cartTitle.isDisplayed(), "Cart page not loaded.");
    }

    @Test(priority = 4)
    public void testCheckoutStart() {
        driver.findElement(By.id("checkout")).click();

        WebElement checkoutTitle = driver.findElement(By.xpath("//span[contains(text(),'Checkout: Your Information')]"));
        Assert.assertTrue(checkoutTitle.isDisplayed(), "Checkout page not opened.");
    }

    @Test(priority = 5)
    public void testCheckoutInformation() {
        driver.findElement(By.id("first-name")).sendKeys("Khwezi");
        driver.findElement(By.id("last-name")).sendKeys("Test");
        driver.findElement(By.id("postal-code")).sendKeys("1234");
        driver.findElement(By.id("continue")).click();

        WebElement overviewTitle = driver.findElement(By.xpath("//span[contains(text(),'Checkout: Overview')]"));
        Assert.assertTrue(overviewTitle.isDisplayed(), "Overview page not loaded.");
    }

    @Test(priority = 6)
    public void testFinishCheckout() {
        driver.findElement(By.name("finish")).click();

        WebElement thankYou = driver.findElement(By.className("complete-header"));
        Assert.assertEquals(thankYou.getText(), "Thank you for your order!", "Checkout not completed successfully.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

