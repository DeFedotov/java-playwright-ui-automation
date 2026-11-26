package pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class IFramePage extends BasePage{

    private final FrameLocator iframe = page.frameLocator("#my-iframe");
    private final Locator paragraphs = iframe.locator("p");

    public IFramePage(Page page) {
        super(page);
    }

    @Step("Wait for paragraphs inside iframe and get count")
    public int getParagraphsCount() {
        paragraphs.first().waitFor();
        return paragraphs.count();
    }

    @Step("Wait for paragraph at index {index} inside iframe")
    public IFramePage waitForParagraphs(int index) {
        paragraphs.nth(index).waitFor();
        return this;
    }

    @Step("Check if {index} paragraph is visible")
    public boolean isParagraphVisible(int index) {
        return paragraphs.nth(index).isVisible();
    }

    @Step("Get paragraph text by index: {index}")
    public String getParagraphText(int index) {
        return paragraphs.nth(index).innerText();
    }
}
