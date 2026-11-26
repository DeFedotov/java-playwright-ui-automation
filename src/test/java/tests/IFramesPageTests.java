package tests;

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
    @DisplayName("Verify IFrames count test")
    void verifyIFramesCountTest() {
        assertThat(iFramePage.getParagraphsCount()).isEqualTo(20);
    }

    @Test
    @DisplayName("Verify paragraph content inside iframe")
    void verifyParagraphContentInsideIframe() {

        iFramePage.waitForParagraphs(0);
        assertThat(iFramePage.isParagraphVisible(0)).isTrue();
        assertThat(iFramePage.getParagraphText(0))
                .isNotBlank()
                .contains("Lorem ipsum");

        assertThat(iFramePage.isParagraphVisible(4)).isTrue();
        assertThat(iFramePage.getParagraphText(4))
                .isNotBlank()
                .contains("Lobortis luctus");
        assertThat(iFramePage.isParagraphVisible(19)).isTrue();
    }
}
