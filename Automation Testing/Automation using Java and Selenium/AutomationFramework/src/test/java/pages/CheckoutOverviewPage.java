package pages;

import com.microsoft.playwright.Page;
import helper.TimeHelper;

public class CheckoutOverviewPage {
    private final Page page;

    public CheckoutOverviewPage(Page page) {
        this.page = page;
    }

    public String getOverviewLabel() {
        String label = page.textContent("span:has-text('Checkout: Overview')");
        TimeHelper.sleep(2000);
        return label;
    }

    public void finishOrder() {
        page.click("button[name='finish']");
        TimeHelper.sleep(2000);
    }
}