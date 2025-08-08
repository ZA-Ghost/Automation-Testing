package org.example.pages;

import com.microsoft.playwright.Page;

public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public String getTitle() {
        return page.textContent("span:has-text('Your Cart')");
    }

    public void checkout() {
        page.click("#checkout");
    }
}