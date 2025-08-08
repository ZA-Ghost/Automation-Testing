package org.example.pages;

import com.microsoft.playwright.Page;

public class ProductsPage {
    private final Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

    public boolean isVisible() {
        return page.isVisible("span:has-text('Products')");
    }

    public String getTitle() {
        return page.textContent("span:has-text('Products')");
    }

    public void addToCart() {
        page.click("#add-to-cart-sauce-labs-backpack");
    }

    public void viewCart() {
        page.click("#shopping_cart_container a");
    }
}