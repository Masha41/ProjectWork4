package pages;

import io.opentelemetry.sdk.trace.data.EventData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.*;

public class CalendarPage extends AbsBasePage {

    public CalendarPage(WebDriver driver) {
        super(driver, "/events/near/");
    }

    @FindBy(css = ".dod_new-event")
    private List<WebElement> cardsEvent;
    @FindBy(css = ".dod_new-event__calendar-icon ~ .dod_new-event__date-text")
    private List<WebElement> dateEvents;
    @FindBy(css = ".dod_new-event .dod_new-type__text")
    private List<WebElement> eventsTypeIcon;

    private String dropdownSortingEventsListSelector = ".dod_new-events-dropdown";
    private String dropdownEventsListSelector = dropdownSortingEventsListSelector + " .dod_new-events-dropdown__list";
    private String dropdownSortingEventsItemTemplate = dropdownEventsListSelector + " [title='%s']";

    public CalendarPage checkVisibilityCardsEvent() {
        Assertions.assertTrue(waiters.waitForCondition(ExpectedConditions.visibilityOfAllElements(cardsEvent)));


        return this;


    }

    public CalendarPage checkStartDateEvent() {
        for(WebElement dateEvent: dateEvents) {
            LocalDate currentDate = LocalDate.now();

            Pattern pattern = Pattern.compile("\\d+\\s+\\[а-яА-Я]+\\s+\\d{4}");
            String dateEventStr = dateEvent.getText();
            Matcher matcher = pattern.matcher(dateEvent.getText());
            if(!matcher.find()) {
                dateEventStr += String.format(" %d", currentDate.getYear());
            }
            //LocalDate eventDate = LocalDate.parse(dateEventStr, DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.of("ru")));
            //LocalDate eventDate = LocalDate.parse(dateEventStr,
            LocalDate eventDate = LocalDate.parse(dateEventStr,
                    ofPattern("d MMMM yyyy"));
                    //ofPattern("dd MMMM yyyy"));
            Assertions.assertTrue(eventDate.isAfter(currentDate) || eventDate.isEqual(currentDate), "Date events are earlier than the current one");
        }

        return this;
    }

    private CalendarPage dropdownSortingEventsShouldNotBeOpened() {
        Assertions.assertTrue(
                waiters.waitForCondition(
                        ExpectedConditions.not(ExpectedConditions.attributeContains(
                                driver.findElement(By.cssSelector(dropdownSortingEventsListSelector)),
                                "class",
                                "dod_new-events-dropdown_opened"))
                )
        );

        return this;
    }

    private CalendarPage dropdownSortingEventsShouldBeOpened() {
        Assertions.assertTrue(
                waiters.waitForCondition((ExpectedConditions.attributeContains(
                        driver.findElement(By.cssSelector(dropdownSortingEventsListSelector)), "class", "dod_new-events-dropdown_opened"))
                )
        );

        return this;
    }

    private CalendarPage openSortingEventsDropdown() {
        driver.findElement(By.cssSelector(dropdownSortingEventsListSelector)).click();

        return this;
    }

    private CalendarPage sortingItemsShouldBeVisible() {
        Assertions.assertTrue(waiters.waitElementVisible(driver.findElement(By.cssSelector(dropdownEventsListSelector))));

        return this;
    }

    private CalendarPage clickSortingItem(EventData eventSortedData) {
        driver.findElement(By.cssSelector(String.format(dropdownSortingEventsItemTemplate, eventSortedData.getName()))).click();
        ;

        return this;

    }

    public CalendarPage selectSortedEventsType(EventData eventTypeData) {
        this.dropdownSortingEventsShouldNotBeOpened()
                .openSortingEventsDropdown()
                .dropdownSortingEventsShouldBeOpened()
                .sortingItemsShouldBeVisible()
                .clickSortingItem(eventTypeData);

        return this;
    }

    public CalendarPage checkEventsType(EventData eventData) {
        for(WebElement element: eventsTypeIcon) {
            Assertions.assertEquals(eventData.getName(), element.getText(),"Type event does not match");
        }

        return this;
    }



}
