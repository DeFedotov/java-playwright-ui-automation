package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.HomePage;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests extends BaseTest{

    private static final String EXPECTED_SUCCESS_URL_PART = "login-sucess.html";
    private static final String EXPECTED_SUCCESS_TEXT = "Login successful";
    private static final String EXPECTED_ERROR_TEXT = "Invalid credentials";

    private LoginPage loginPage;

    @BeforeEach
    void openLoginPageFromHomePage() {
        loginPage = new HomePage(page)
                .openHomePage()
                .openLoginFormPage();
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Valid login test")
    void validLoginTest(){

        loginPage.login("users", "user");

        assertThat(page.url())
                .contains(".*" +  EXPECTED_SUCCESS_URL_PART);

        assertThat(loginPage.getSuccessMessage())
                .isNotBlank()
                .contains(EXPECTED_SUCCESS_TEXT);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @CsvSource({
            "Invalid credentials, invalid, user",
            "Empty username, '', user",
            "Empty password, user, ''",
            "Empty username and password, '', ''"
    })
    @Tag("Smoke")
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
