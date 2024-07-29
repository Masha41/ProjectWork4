package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardCoursePage extends AbsBasePage {

    public CardCoursePage(WebDriver driver, String coursesPath) {
        super(driver, String.format("/lessons/%s",coursesPath ));
    }

    public void checkTitleCourse() {
        String title = "//h1";
        Assertions.assertFalse(driver.findElement(By.xpath(title)).getText().isEmpty());

    }

    public void checkDescriptionCourse() {
        String description = "//h1/following-sibling::div[text()]";
        Assertions.assertFalse(driver.findElement(By.xpath(description)).getText().isEmpty(), "Description courses empty");
           }

    public void checkCourseDuration() {
        String durationCourse = "//div/following-sibling::p[contains(text(), 'месяц')]";
        Assertions.assertFalse(driver.findElement(By.xpath(durationCourse)).getText().isEmpty());
    }

    public void checkCourseFormat(String format) {
        WebElement formatCourse = driver.findElement(By.xpath(String.format("//p[contains(text(), '%s')]", format)));
        Assertions.assertFalse(formatCourse.getText().isEmpty());
    }
}
