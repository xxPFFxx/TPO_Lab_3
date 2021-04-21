package lab;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MarketTest {
    private Util util;

    private void doFindOffer(WebDriver driver) {
        util.prepare(driver);
        driver.findElement(By.xpath("//div[contains(text(), \"Все компании\")]")).click();
        Select select = new Select(driver.findElement(By.xpath("//select[@name=\"region\"]")));
        select.selectByVisibleText("Санкт-Петербург и Ленинградская область");
        Select select2 = new Select(driver.findElement(By.xpath("//select[@name=\"brand\"]")));
        select2.selectByVisibleText("Nissan");
        util.tryClick(driver, By.xpath("(//button[@class=\"c-button c-button--primary c-button--l u-100 c-companies-form__button\"])[1]"));
        assertTrue(util.isElementPresent(driver, By.xpath("//div[@class=\"c-companies\"]")));
        driver.quit();
    }


    @Before
    public void setUp() {
        util = new Util();
    }

    @Test
    public void findOffer() {
        doFindOffer(new FirefoxDriver());
        doFindOffer(new ChromeDriver());
    }

}