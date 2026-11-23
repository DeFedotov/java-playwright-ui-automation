package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import constants.Constants;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

    private final Locator chapterLocator = page.locator("h5.card-title");
    private final Locator titleLocator = page.locator("h1.display-4");
    private final Locator loginFormLink = page.locator("a[href='login-form.html']");

    public  HomePage(Page page) {
        super(page);
    }

    @Step("Open Home Page")
    public HomePage openHomePage() {
        navigateTo(Constants.BASE_URL);
        return this;
    }

    @Step("Get Home Page title")
    public String getTitle() {
        return titleLocator.innerText();
    }

    @Step("Count all chapters on Home Page")
    public int countChapters() {
        return chapterLocator.count();
    }

    @Step("Open Login Form Page")
    public LoginPage openLoginFormPage() {
        loginFormLink.click();
        return new LoginPage(page);
    }
}
