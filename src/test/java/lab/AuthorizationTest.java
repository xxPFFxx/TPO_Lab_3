package lab;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuthorizationTest {
    Util util;

    private void doSuccessfulLogin(WebDriver driver){
        util.prepare(driver);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
        util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        util.tryClick(driver, By.xpath("//div[@class=\"c-top-nav\"][3]//button"));
        driver.findElement(By.xpath("//div[@class=\"c-top-dropdown\"]//button")).click();
        driver.quit();
    }

    private void doWrongLogin(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        util.prepare(driver);
        util.auth(driver, util.getCorrectLogin(), "asdasdasd");
        assertTrue(util.isElementPresent(driver, By.cssSelector("span.field-validation-error")));
        driver.quit();
    }

    @Before
    public void setUp() {
        util = new Util();
    }


    @Test
    public void successfulLogin() {
        doSuccessfulLogin(new FirefoxDriver());
        doSuccessfulLogin(new ChromeDriver());
    }

    @Test
    public void wrongPassword() {
        doWrongLogin(new FirefoxDriver());
        doWrongLogin(new ChromeDriver());
    }
}