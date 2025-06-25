package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartLink = By.className("shopping_cart_link");

    public void goToCart() {
        driver.findElement(cartLink).click();
    }
}
