import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaywrightUsingOrder {
    static Playwright playwright;
    static Browser browser;
    static Page page;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    @Order(1)
    void navigateToLogin() throws InterruptedException {
        page.navigate("https://www.saucedemo.com");
        Thread.sleep(3000);
    }

    @Test
    @Order(2)
    void enterCredentialsAndLogin() throws InterruptedException {
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");
        Thread.sleep(3000);
    }

    @Test
    @Order(3)
    void verifyProductsPage() throws InterruptedException {
        Assertions.assertTrue(page.isVisible("span:has-text('Products')"));
        Assertions.assertEquals("Products", page.textContent("span:has-text('Products')"));
        Thread.sleep(3000);
    }

    @Test
    @Order(4)
    void addToCartAndViewCart() throws InterruptedException {
        page.click("#add-to-cart-sauce-labs-backpack");
        page.click("#shopping_cart_container a");
        Thread.sleep(3000);
    }

    @Test
    @Order(5)
    void verifyCart() throws InterruptedException {
        Assertions.assertEquals("Your Cart", page.textContent("span:has-text('Your Cart')"));
        Thread.sleep(3000);
    }

    @Test
    @Order(6)
    void checkoutAndVerifyInfo() throws InterruptedException {
        page.click("#checkout");
        Assertions.assertEquals("Checkout: Your Information", page.textContent("span:has-text('Checkout: Your Information')"));
        Thread.sleep(3000);
    }

    @Test
    @Order(7)
    void enterUserInfoAndContinue() throws InterruptedException {
        page.fill("#first-name", "Khwezi");
        page.fill("#last-name", "Test");
        page.fill("#postal-code", "1234");
        page.click("#continue");
        Thread.sleep(3000);
    }

    @Test
    @Order(8)
    void verifyOverviewAndFinishOrder() throws InterruptedException {
        Assertions.assertEquals("Checkout: Overview", page.textContent("span:has-text('Checkout: Overview')"));
        page.click("button[name='finish']");
        Thread.sleep(3000);
    }
}