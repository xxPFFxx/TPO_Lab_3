package lab;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchTest {
    private Util util;

    private void doSearch(WebDriver driver, String query){
        driver.findElement(By.xpath("//div[@class=\"o-group o-group--xxl\"]//input")).clear();
        driver.findElement(By.xpath("//div[@class=\"o-group o-group--xxl\"]//input")).sendKeys(query);
        driver.findElement(By.xpath("//div[@class=\"o-group o-group--xxl\"]//button")).click();
    }

    private void doBadSearch(WebDriver driver){
        util.prepare(driver);
        doSearch(driver, "qwezxcasd");
        assertTrue(util.isElementPresent(driver, By.xpath("//div[text()=\"Поиск ничего не нашёл\"]")));
        driver.quit();
    }

    private void doGoodSearch(WebDriver driver){
        util.prepare(driver);
        doSearch(driver, "Nissan");
        assertFalse(util.isElementPresent(driver, By.xpath("//div[text()=\"Поиск ничего не нашёл\"]")));
        driver.quit();
    }

    @Before
    public void setUp() {
        util = new Util();
    }

    @Test
    public void failedSearch() {
        doBadSearch(new FirefoxDriver());
        doBadSearch(new ChromeDriver());
    }

    @Test
    public void successfulSearch() {
        doGoodSearch(new FirefoxDriver());
        doGoodSearch(new ChromeDriver());
    }
}