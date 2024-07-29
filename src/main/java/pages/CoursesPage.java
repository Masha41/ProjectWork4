package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

public class CoursesPage extends AbsBasePage {

    public CoursesPage(WebDriver driver) {
        super(driver, "/catalog/courses");

    }

    @FindBy(xpath = "//section//div[not(@style)]/a[contains(@href, '/lessons/')][.//h6]")
    public  List<WebElement> cardsCourses;




    public void cardsCoursesCountShouldBeSameAs(int count) {
        Assertions.assertEquals(count, count);

        //"check count courses. Cards = 10"
    }

    public void clickRandomCardCourses() {
        //faker.options().<WebElement>nextElement(cardsCourses).click();
       faker.options().<WebElement>nextElement(Collections.unmodifiableList(cardsCourses)).click();
        //"check random card courses success"
    }
}
