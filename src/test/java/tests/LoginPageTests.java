package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests extends BaseTest{

    @Test
    @DisplayName("Valid login test")
    public void validLoginTest(){
        LoginPage loginPage = new LoginPage(page);

        loginPage
                .navigate()
                .login("user", "user");

        assertThat(page.url())
                .contains("login-sucess.html");

        assertThat(loginPage.getSuccessMessage())
                .isNotBlank()
                .contains("Login successful");
    }

    @Test
    @DisplayName("Invalid login test")
    public void invalidLoginTest(){
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigate().login("invalid", "user");

        assertThat(page.url()).doesNotContain("login-sucess.html");
        assertThat(loginPage.getErrorMessage()).isNotBlank().doesNotContain("Login successful").contains("Invalid credentials");
    }
}
