import com.microsoft.playwright.*;
import org.example.pages.*;
import org.junit.jupiter.api.*;

/*
  Playwright test suite using Page Object Model.
 Each test step is ordered and interacts with encapsulated page objects.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PageObjectModel {
    // Playwright and browser instances
    static Playwright playwright;
    static Browser browser;
    static Page page;

    // Page Object Model instances
    static LoginPage loginPage;
    static ProductsPage productsPage;
    static CartPage cartPage;
    static CheckoutPage checkoutPage;

    /*
      Setup Playwright, browser, and page objects before all tests.
     */
    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        // Initialize page objects
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
    }

    /*
     Close browser and Playwright after all tests.
     */
    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    /*
     Step 1: Navigate to the login page.
     */
    @Test
    @Order(1)
    void navigateToLogin() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(3000);
    }

    /*
      Step 2: Enter credentials and log in.
     */
    @Test
    @Order(2)
    void enterCredentialsAndLogin() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(3000);
    }

    /*
     Step 3: Verify the products page is displayed.
     */
    @Test
    @Order(3)
    void verifyProductsPage() throws InterruptedException {
        Assertions.assertTrue(productsPage.isVisible());
        Assertions.assertEquals("Products", productsPage.getTitle());
        Thread.sleep(3000);
    }

    /*
     Step 4: Add product to cart and view cart.
     */
    @Test
    @Order(4)
    void addToCartAndViewCart() throws InterruptedException {
        productsPage.addToCart();
        productsPage.viewCart();
        Thread.sleep(3000);
    }

    /*
     Step 5: Verify cart page is displayed.
     */
    @Test
    @Order(5)
    void verifyCart() throws InterruptedException {
        Assertions.assertEquals("Your Cart", cartPage.getTitle());
        Thread.sleep(3000);
    }

    /*
     Step 6: Proceed to checkout and verify information page.
     */
    @Test
    @Order(6)
    void checkoutAndVerifyInfo() throws InterruptedException {
        cartPage.checkout();
        Assertions.assertEquals("Checkout: Your Information", checkoutPage.getInfoTitle());
        Thread.sleep(3000);
    }

    /*
      Step 7: Enter user information and continue.
     */
    @Test
    @Order(7)
    void enterUserInfoAndContinue() throws InterruptedException {
        checkoutPage.enterUserInfo("Khwezi", "Test", "1234");
        Thread.sleep(3000);
    }

    /*
     Step 8: Verify overview page and finish order.
     */
    @Test
    @Order(8)
    void verifyOverviewAndFinishOrder() throws InterruptedException {
        Assertions.assertEquals("Checkout: Overview", checkoutPage.getOverviewTitle());
        checkoutPage.finishOrder();
        Thread.sleep(3000);
    }
}