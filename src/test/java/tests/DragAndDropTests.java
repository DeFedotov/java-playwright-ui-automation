package tests;

import com.microsoft.playwright.options.BoundingBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DragAndDropPage;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DragAndDropTests extends BaseTest {

    private DragAndDropPage dragAndDropPage;

    @BeforeEach
    void openDragAndDropPageFromHomePage() {
        dragAndDropPage = new HomePage(page)
                .openHomePage()
                .openDragAndDropPage();
    }

    @Test
    @DisplayName("Drag and drop item")
    void dragAndDropItemTest() {
        BoundingBox expectedLocation = dragAndDropPage.getTargetLocation();

        dragAndDropPage.dragAndDropItem();

        BoundingBox actualLocation = dragAndDropPage.getSourceFinalLocation();
        assertEquals(Math.round(expectedLocation.x), Math.round(actualLocation.x));
        assertEquals(Math.round(expectedLocation.y), Math.round(actualLocation.y));
    }
}
