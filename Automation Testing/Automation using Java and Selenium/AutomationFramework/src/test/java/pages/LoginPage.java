package pages;

import com.microsoft.playwright.Page;
import helper.TimeHelper;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        page.fill("#user-name", username);
        TimeHelper.sleep(2000);

        page.fill("#password", password);
        TimeHelper.sleep(2000);

        page.click("#login-button");
        TimeHelper.sleep(2000);
    }
}