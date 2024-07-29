package waiters;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    private WebDriver driver;

    public Waiters(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10)).until(condition);
            return true;
        } catch (TimeoutException ignore) {
            return false;
        }
    }

    public boolean waitElementVisible(WebElement element) {
        return this.waitForCondition(ExpectedConditions.visibilityOf((WebElement) element));
    }
}
