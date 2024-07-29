package common;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import waiters.Waiters;

public abstract class AbsCommon {
    protected WebDriver driver;
    protected Waiters waiters;
    protected Faker faker;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        waiters = new Waiters(driver);
        this.faker = new Faker();
    }

}
