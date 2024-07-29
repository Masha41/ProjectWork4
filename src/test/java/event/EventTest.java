package event;

import data.EventData;
import exception.BrowserNorSupportedException;
import factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.CalendarPage;

public class EventTest {

    private WebDriver driver;
    private CalendarPage calendarPage;

    @BeforeEach
    public void init() {
        try {
            this.driver = new DriverFactory().create();
        } catch (BrowserNorSupportedException e) {
            throw new RuntimeException(e);
        }

        this.calendarPage = new CalendarPage(driver);
        calendarPage.update();

    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }

    }

    @Test

    public void validationDateEvents() {
        calendarPage
                .checkVisibilityCardsEvent()
                .checkStartDateEvent();
    }

    @Test

    public void selectEventsOfType() {
        calendarPage
                .selectSortedEventsType(EventData.OPEN)
                .checkEventsType(EventData.OPEN);
    }
}
