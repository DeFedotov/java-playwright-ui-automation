package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage {

    Locator loginInput = page.locator("#username");
    Locator passwordInput = page.locator("#password");
    Locator submitButton = page.locator("xpath=//button[@type='submit']");
    Locator successMessage = page.locator("#success");


    public LoginPage(Page page) {
        super(page);
    }

    public LoginPage navigate() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
        return this;
    }

    @Step("Login as user with username: {username} and password: {password}")
    public void login(String username, String password) {
        loginInput.fill(username);
        passwordInput.fill(password);
        submitButton.click();
    }

    @Step("Get success message text")
    public String getSuccessMessage() {
        return successMessage.textContent();
    }
}
