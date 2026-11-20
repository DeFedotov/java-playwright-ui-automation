package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    private static final String LOGIN_FORM_PAGE_URL = "https://bonigarcia.dev/selenium-webdriver-java/login-form.html";

    private final Locator loginInput = page.locator("#username");
    private final Locator passwordInput = page.locator("#password");
    private final Locator submitButton = page.locator("button[type='submit']");
    private final Locator successMessage = page.locator("#success");
    private final Locator errorMessage = page.locator("#invalid");

    public LoginPage(Page page) {
        super(page);
    }

//    @Step("Open Login Page")
//    public LoginPage navigateToLoginPage() {
//        navigateTo(LOGIN_FORM_PAGE_URL);
//        return this;
//    }

    @Step("Login as user with username: {username} and password: {password}")
    public void login(String username, String password) {
        loginInput.fill(username);
        passwordInput.fill(password);
        submitButton.click();
    }

    @Step("Get success message text")
    public String getSuccessMessage() {
        return successMessage.innerText();
    }

    @Step("Get invalid message text")
    public String getErrorMessage() {
        return errorMessage.innerText();
    }
}
