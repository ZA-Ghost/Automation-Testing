package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    WebDriver driver;
    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    By finishButton = By.name("finish");

    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }
}
