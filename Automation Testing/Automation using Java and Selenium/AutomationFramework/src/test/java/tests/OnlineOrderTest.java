package tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;
import config.Config;
import config.BrowserConfig;
import reports.ReportManager;
import pages.*;

public class OnlineOrderTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        BrowserType browserType = switch (BrowserConfig.BROWSER_TYPE) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(BrowserConfig.HEADLESS));
        page = browser.newPage();
        page.navigate(Config.BASE_URL);
        ReportManager.log("Navigated to " + Config.BASE_URL);
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(page);
        Assert.assertEquals(productsPage.getProductsLabel(), "Products");
        ReportManager.log("Login successful and Products page loaded.");
    }

    @Test(dependsOnMethods = "testLogin")
    public void testAddToCart() {
        ProductsPage productsPage = new ProductsPage(page);
        productsPage.addItemToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(page);
        Assert.assertEquals(cartPage.getYourCartLabel(), "Your Cart");
        ReportManager.log("Item added to cart and navigated to Cart page.");
    }

    @Test(dependsOnMethods = "testAddToCart")
    public void testCheckout() {
        CartPage cartPage = new CartPage(page);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(page);
        Assert.assertEquals(checkoutPage.getInfoLabel(), "Checkout: Your Information");
        checkoutPage.fillCustomerInfo("Khwezi", "Test", "1234");
        ReportManager.log("Checkout information entered.");
    }

    @Test(dependsOnMethods = "testCheckout")
    public void testFinishOrder() {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(page);
        Assert.assertEquals(overviewPage.getOverviewLabel(), "Checkout: Overview");
        overviewPage.finishOrder();
        ReportManager.log("Order finished.");
    }

    @AfterClass
    public void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        ReportManager.log("Browser closed.");
    }



}