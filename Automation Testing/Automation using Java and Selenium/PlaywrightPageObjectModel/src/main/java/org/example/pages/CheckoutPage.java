package org.example.pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public String getInfoTitle() {
        return page.textContent("span:has-text('Checkout: Your Information')");
    }

    public void enterUserInfo(String firstName, String lastName, String postalCode) {
        page.fill("#first-name", firstName);
        page.fill("#last-name", lastName);
        page.fill("#postal-code", postalCode);
        page.click("#continue");
    }

    public String getOverviewTitle() {
        return page.textContent("span:has-text('Checkout: Overview')");
    }

    public void finishOrder() {
        page.click("button[name='finish']");
    }
}