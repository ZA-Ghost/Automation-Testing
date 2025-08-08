import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.testng.annotations.Test;

public class PlayWright {

    @Test
    public void testBrowser() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            page.navigate("https://www.saucedemo.com");

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            page.fill("#user-name", "standard_user");
            page.fill("#password", "secret_sauce");
            page.click("#login-button");

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            Assertions.assertTrue(page.isVisible("span:has-text('Products')"));
            Assertions.assertEquals("Products", page.textContent("span:has-text('Products')"));

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            page.click("#add-to-cart-sauce-labs-backpack");
            page.click("#shopping_cart_container a");

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            Assertions.assertEquals("Your Cart", page.textContent("span:has-text('Your Cart')"));

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            page.click("#checkout");
            Assertions.assertEquals("Checkout: Your Information", page.textContent("span:has-text('Checkout: Your Information')"));

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            page.fill("#first-name", "Khwezi");
            page.fill("#last-name", "Test");
            page.fill("#postal-code", "1234");
            page.click("#continue");

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            Assertions.assertEquals("Checkout: Overview", page.textContent("span:has-text('Checkout: Overview')"));

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            page.click("button[name='finish']");

            Thread.sleep(3000); // Wait for 3 seconds to see the result

            browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}