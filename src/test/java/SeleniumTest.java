import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    @Test
    public void executeFirefoxDriver() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.drive2.ru");
        String title = driver.getTitle();
        assertEquals("DRIVE2.RU", title);
        driver.quit();
    }

    @Test
    public void executeChrome() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.drive2.ru");
        String title = driver.getTitle();
        assertEquals("DRIVE2.RU", title);
        driver.quit();
    }

}