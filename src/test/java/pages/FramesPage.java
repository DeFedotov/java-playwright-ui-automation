package pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class FramesPage extends BasePage {

    private final FrameLocator frame;
    private final Locator paragraphs;

    public FramesPage(Page page){
        super(page);
        this.frame = page.frameLocator("frame[name='frame-body']");
        this.paragraphs = this.frame.locator("p");
    }

    @Step("Wait for paragraphs inside frame and get count")
    public int getParagraphsCount() {
        paragraphs.first().waitFor();
        return paragraphs.count();
    }

    @Step("Wait for paragraph at index {index} inside frame")
    public void waitForParagraph(int index) {
        paragraphs.nth(index).waitFor();
    }

    @Step("Check if paragraph at index {index} is visible")
    public boolean isParagraphVisible(int index) {
        return paragraphs.nth(index).isVisible();
    }

    @Step("Get paragraph text at index {index}")
    public String getParagraphText(int index) {
        paragraphs.nth(index).waitFor();
        return paragraphs.nth(index).innerText();
    }
}
