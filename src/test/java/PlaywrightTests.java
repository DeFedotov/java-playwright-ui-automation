import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


public class PlaywrightTests {
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void successfulLoginTest() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");

        Locator subTitle = page.locator(".display-6");
        Locator loginInput = page.locator("#username");
        Locator passwordInput = page.locator("#password");
        Locator submitButton = page.locator("xpath=//button[@type='submit']");

        loginInput.fill("user");
        passwordInput.fill("user");
        String textBeforeClick = subTitle.innerText();
        submitButton.click();

        assertThat(textBeforeClick).isEqualTo("Login form");
        Locator successMessage = page.locator("#success");
        assertThat(successMessage.isVisible()).isTrue();
        assertThat(successMessage.innerText()).isEqualTo("Login successful");
    }
}
