package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginPageTests extends BaseTest{

    @Test
    @Tag("Smoke")
    @Order(1)
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
    @Tag("Smoke")
    @Order(2)
    @DisplayName("Invalid login test")
    public void invalidLoginTest(){
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigate().login("invalid", "user");

        assertThat(page.url()).doesNotContain("login-sucess.html");
        assertThat(loginPage.getErrorMessage()).isNotBlank().doesNotContain("Login successful").contains("Invalid credentials");
    }

    @Test
    @Tag("Smoke")
    @Order(3)
    @DisplayName("Empty username login test")
    public void emptyUsernameLoginTest() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigate().login("", "user");

        assertThat(page.url()).doesNotContain("login-sucess.html");
        assertThat(loginPage.getErrorMessage())
                .isNotBlank()
                .contains("Invalid credentials");
    }

    @Test
    @Tag("Smoke")
    @Order(4)
    @DisplayName("Empty password login test")
    public void emptyPasswordLoginTest() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigate().login("user", "");

        assertThat(page.url()).doesNotContain("login-sucess.html");
        assertThat(loginPage.getErrorMessage())
                .isNotBlank()
                .contains("Invalid credentials");
    }

    @Test
    @Tag("Smoke")
    @Order(5)
    @DisplayName("Empty fields login test")
    public void emptyFieldsLoginTest() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigate().login("", "");

        assertThat(page.url()).doesNotContain("login-sucess.html");
        assertThat(loginPage.getErrorMessage())
                .isNotBlank()
                .contains("Invalid credentials");
    }
}
