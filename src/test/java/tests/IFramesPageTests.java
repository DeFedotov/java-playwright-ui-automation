package tests;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.IFramePage;

import static org.assertj.core.api.Assertions.assertThat;

public class IFramesPageTests extends BaseTest {

    private IFramePage iFramePage;

    @BeforeEach
    void openIFramePageFromHomePage() {
        iFramePage = new HomePage(page)
                .openHomePage()
                .openIFramePage();
    }

    @Test
    @DisplayName("IFrames test")
    void iFramesTest() {
        FrameLocator iframe = page.frameLocator("#my-iframe");
        Locator paragraphs = iframe.locator("p");

        paragraphs.first().waitFor();
        assertThat(paragraphs.count()).isEqualTo(20);
    }

}
