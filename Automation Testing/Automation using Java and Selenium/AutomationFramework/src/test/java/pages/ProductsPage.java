package pages;

import com.microsoft.playwright.Page;
import helper.TimeHelper;

public class ProductsPage {
    private final Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

    public String getProductsLabel() {
        String label = page.textContent("span:has-text('Products')");
        TimeHelper.sleep(2000);
        return label;
    }

    public void addItemToCart() {
        page.click("#add-to-cart-sauce-labs-backpack");
        TimeHelper.sleep(2000);
    }

    public void goToCart() {
        page.click("#shopping_cart_container a");
        TimeHelper.sleep(2000);
    }
}