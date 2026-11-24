package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import io.qameta.allure.Step;

public class DragAndDropPage extends BasePage {

    private final Locator source = page.locator("#draggable");
    private final Locator target = page.locator("#target");

    public DragAndDropPage(Page page) {
        super(page);
    }

    @Step("Get target element location")
    public BoundingBox getTargetLocation() {
        return target.boundingBox();
    }

    @Step("Drag and drop item to target")
    public void dragAndDropItem() {
        source.dragTo(target);
    }

    @Step("Get source element final location")
    public BoundingBox getSourceFinalLocation() {
        return source.boundingBox();
    }
}
