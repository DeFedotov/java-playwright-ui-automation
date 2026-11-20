package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    private final Locator chapterLocator = page.locator("h5.card-title");
    private final Locator titleLocator = page.locator("h1.display-4");

    public  HomePage(Page page) {
        super(page);
    }

    @Step("Open Home Page")
    public HomePage navigateToHomePage() {
        navigateTo(HOME_PAGE_URL);
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
}
