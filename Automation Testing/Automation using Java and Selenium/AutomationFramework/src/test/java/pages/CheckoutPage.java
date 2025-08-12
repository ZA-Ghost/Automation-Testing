package pages;

import com.microsoft.playwright.Page;
import helper.TimeHelper;

public class CheckoutPage {
    private final Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public String getInfoLabel() {
        String label = page.textContent("span:has-text('Checkout: Your Information')");
        TimeHelper.sleep(2000);
        return label;
    }

    public void fillCustomerInfo(String firstName, String lastName, String postalCode) {
        page.fill("#first-name", firstName);
        TimeHelper.sleep(2000);

        page.fill("#last-name", lastName);
        TimeHelper.sleep(2000);

        page.fill("#postal-code", postalCode);
        TimeHelper.sleep(2000);

        page.click("#continue");
        TimeHelper.sleep(2000);
    }
}