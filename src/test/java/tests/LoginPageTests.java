package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests extends BaseTest{

    private static final String EXPECTED_SUCCESS_URL_PART = "login-sucess.html";
    private static final String EXPECTED_SUCCESS_TEXT = "Login successful";
    private static final String EXPECTED_ERROR_TEXT = "Invalid credentials";

    private LoginPage loginPage;

    @BeforeEach
    void openLoginPage() {
        loginPage = new LoginPage(page).navigate();
    }

    @Test
    @Tag("Smoke")
    @Order(1)
    @DisplayName("Valid login test")
    void validLoginTest(){

        loginPage.login("users", "user");

        assertThat(page.url())
                .contains(".*" +  EXPECTED_SUCCESS_URL_PART);

        assertThat(loginPage.getSuccessMessage())
                .isNotBlank()
                .contains(EXPECTED_SUCCESS_TEXT);
    }

    @Test
    @Tag("Smoke")
    @Order(2)
    @DisplayName("Invalid login test")
    void invalidLoginTest(){

        loginPage.login("invalid", "user");

        assertThat(page.url()).doesNotContain(".*" +  EXPECTED_SUCCESS_URL_PART);

        assertThat(loginPage.getErrorMessage())
                .isNotBlank()
                .doesNotContain(EXPECTED_SUCCESS_TEXT)
                .contains(EXPECTED_ERROR_TEXT);
    }

    @Test
    @Tag("Smoke")
    @Order(3)
    @DisplayName("Empty username login test")
    void emptyUsernameLoginTest() {

        loginPage.login("", "user");

        assertThat(page.url()).doesNotContain(EXPECTED_SUCCESS_TEXT);
        assertThat(loginPage.getErrorMessage())
                .isNotBlank()
                .contains(EXPECTED_ERROR_TEXT);
    }

    @Test
    @Tag("Smoke")
    @Order(4)
    @DisplayName("Empty password login test")
    void emptyPasswordLoginTest() {

        loginPage.login("user", "");

        assertThat(page.url()).doesNotContain(".*" +  EXPECTED_SUCCESS_URL_PART);
        assertThat(loginPage.getErrorMessage())
                .isNotBlank()
                .contains(EXPECTED_ERROR_TEXT);
    }

    @Test
    @Tag("Smoke")
    @Order(5)
    @DisplayName("Empty fields login test")
    void emptyFieldsLoginTest() {

        loginPage.login("", "");

        assertThat(page.url()).doesNotContain(".*" +  EXPECTED_SUCCESS_URL_PART);
        assertThat(loginPage.getErrorMessage())
                .isNotBlank()
                .contains(EXPECTED_ERROR_TEXT);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @CsvSource({
            "Invalid credentials, invalid, user",
            "Empty username, '', user",
            "Empty password, user, ''",
            "Empty username and password, '', ''"
    })
    @DisplayName("Parameterized invalid login cases")
    void invalidLoginCaseTest(String caseName, String username, String password) {
        loginPage.login(username, password);

        assertThat(page.url()).doesNotContain(EXPECTED_SUCCESS_URL_PART);
        assertThat(loginPage.getErrorMessage())
                .isNotBlank()
                .doesNotContain(EXPECTED_SUCCESS_TEXT)
                .contains(EXPECTED_ERROR_TEXT);
    }
}
