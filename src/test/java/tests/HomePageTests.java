package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTests extends BaseTest {

    private HomePage homePage;

    @BeforeEach
    void openHomePage() {
        homePage = new HomePage(page).navigateToHomePage();
    }

    @Test
    @DisplayName("Verify Home Page title")
    public void verifyHomePageTitle() {
        assertThat(homePage.getTitle()).isEqualTo("Hands-On Selenium WebDriver with Java");
    }

    @Test
    @DisplayName("Verify chapters count")
    public void countChapters() {
        assertThat(homePage.countChapters()).isEqualTo(6);
    }
}
