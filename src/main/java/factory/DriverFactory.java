package factory;

import exception.BrowserNorSupportedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public class DriverFactory {

    private String browserName = System.getProperty("browser.name","chrome");
    private String[] arguments;
    public DriverFactory(String... arguments) {
        this.arguments = arguments;
    }

    public WebDriver create() throws BrowserNorSupportedException {
        browserName = browserName.valueOf(browserName.toLowerCase(Locale.ROOT));


        switch (browserName) {
            case "chrome": {

                return new ChromeDriver((ChromeOptions) new ChromeDriverSettings().settings());
            }
            case "firefox":{

                return new FirefoxDriver((FirefoxOptions) new FirefoxDriverSettings().settings());
            }
        }
        return null;
        //throw new  BrowserNotSupportedException(browserName);
    }
}
