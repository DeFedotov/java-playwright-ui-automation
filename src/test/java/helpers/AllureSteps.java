package helpers;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.io.ByteArrayInputStream;

public class AllureSteps {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] captureScreenshot(Page page) {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }

    @Step("Capture screenshot (spoiler)")
    public static void captureScreenshotSpoiler(Page page) {
        Allure.addAttachment(
                "Screenshot",
                "image/png",
                new ByteArrayInputStream(page.screenshot(new Page.ScreenshotOptions().setFullPage(true))),
                ".png"
        );
    }

    @Attachment(value = "Page HTML Source", type = "text/html")
    public static String capturePageSource(Page page) {
        return page.content();
    }
}