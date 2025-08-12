package pages;

import com.microsoft.playwright.Page;
import helper.TimeHelper;


/*
The purpose of this class is to represent the Cart page in the application.
It provides methods to interact with the Cart page, such as retrieving the "Your Cart" label
and clicking the checkout button.
This class is part of a Playwright-based test automation framework for a web application.
*/


/*
We imported the necessary Playwright classes to interact with the page,
and the TimeHelper class to manage sleep times for synchronization purposes.
The purpose of page in regards to Playwright is to represent a single tab or window in the browser,
allowing us to perform actions and retrieve information from that page.
 */

public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public String getYourCartLabel() {
        String label = page.textContent("span:has-text('Your Cart')");
        TimeHelper.sleep(2000);
        return label;
    }

    public void clickCheckout() {
        page.click("#checkout");
        TimeHelper.sleep(2000);
    }
}