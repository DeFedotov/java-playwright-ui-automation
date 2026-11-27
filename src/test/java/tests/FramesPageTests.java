package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FramesPage;
import pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesPageTests extends BaseTest {

    private FramesPage framesPage;

    @BeforeEach
    void openFramesPageFromHomePage() {
        framesPage = new HomePage(page)
                .openHomePage()
                .openFramesPage();
    }

    @Test
    @DisplayName("IFrame test")
    void iFrameTest() {
        assertThat(framesPage.getParagraphsCount()).isEqualTo(20);
    }

    @Test
    @DisplayName("Verify paragraph content inside frames")
    void verifyParagraphContentInsideFrames() {

        framesPage.waitForParagraph(5);
        assertThat(framesPage.isParagraphVisible(5)).isTrue();
        assertThat(framesPage.getParagraphText(5)).contains("Blandit tincidunt neque");
    }
}
