package lab;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchTest {
    private Util util;

    private void doSearch(WebDriver driver, String query){
        driver.findElement(By.xpath("//div[@class=\"o-group o-group--xxl\"]//input")).clear();
        driver.findElement(By.xpath("//div[@class=\"o-group o-group--xxl\"]//input")).sendKeys(query);
        driver.findElement(By.xpath("//div[@class=\"o-group o-group--xxl\"]//button")).click();
    }

    private void doWrongSearch(WebDriver driver){
        util.prepare(driver);
        doSearch(driver, "qwezxcasddhksafhas");
        assertTrue(util.isElementPresent(driver, By.xpath("//div[text()=\"Поиск ничего не нашёл\"]")));
        driver.quit();
    }

    private void doSuccessfulSearch(WebDriver driver){
        util.prepare(driver);
        doSearch(driver, "Nissan");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        util.isElementPresent(driver, By.xpath("//div[@class=\"c-top-nav\"][3]//button"));
        assertFalse(util.isElementPresent(driver, By.xpath("//div[text()=\"Поиск ничего не нашёл\"]")));
        driver.quit();
    }

    @Before
    public void setUp() {
        util = new Util();
    }

    @Test
    public void wrongSearch() {
        doWrongSearch(new FirefoxDriver());
        doWrongSearch(new ChromeDriver());
    }

    @Test
    public void successfulSearch() {
        doSuccessfulSearch(new FirefoxDriver());
        doSuccessfulSearch(new ChromeDriver());
    }
}