package course;

import data.CoursesData;
import exception.BrowserNorSupportedException;
import factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.CardCoursePage;
import pages.CoursesPage;

import java.util.ArrayList;
import java.util.List;

public class CourseTest {
    private CoursesPage coursesPage = null;

    private WebDriver driver;

    @BeforeEach
    public void init() {
        try {
            this.driver = new DriverFactory().create();
        } catch (BrowserNorSupportedException e) {
            throw new RuntimeException(e);
        }

        List<String> queryParams = new ArrayList<>();
        queryParams.add(String.format("categories=%s", CoursesData.TESTING.name().toLowerCase()));

        this.coursesPage = new CoursesPage(driver);
        coursesPage.open(queryParams);
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test

    public void checkCountCourses() {
        coursesPage.cardsCoursesCountShouldBeSameAs(10);
    }

    @Test

    public void checkDataCardCourse() {
        coursesPage.clickRandomCardCourses();
        CardCoursePage cardCoursePage = new CardCoursePage (driver, "");
        cardCoursePage.checkTitleCourse();
        cardCoursePage.checkDescriptionCourse();
        cardCoursePage.checkCourseDuration();
        cardCoursePage.checkCourseFormat("Онлайн");
    }
}
