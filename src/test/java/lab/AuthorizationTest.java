package lab;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class AuthorizationTest {
    Util util;

    private void doSuccessfulLogin(WebDriver driver){
        util.prepare(driver);
        util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        util.tryClick(driver, By.xpath("//div[@class=\"c-top-nav\"][3]//button")); //Нажимаем на настройки
        driver.findElement(By.xpath("//div[@class=\"c-top-dropdown\"]//button")).click(); //Нажимаем на выход
        driver.quit();
    }

    private void doWrongLogin(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        util.isElementPresent(driver, By.xpath("//div[@class=\"c-top-nav\"][3]//button"));
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