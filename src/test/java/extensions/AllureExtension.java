package extensions;

import com.microsoft.playwright.Page;
import helpers.AllureSteps;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import tests.BaseTest;

public class AllureExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            Page page = BaseTest.getPage();
            if (page != null) {
                AllureSteps.captureScreenshotSpoiler(page);
                AllureSteps.capturePageSource(page);
            }
        }
    }
}