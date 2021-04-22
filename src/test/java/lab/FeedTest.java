package lab;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FeedTest {
    Util util = new Util();

    private void doViewFeed(WebDriver driver){
        util.prepare(driver);
        util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        driver.get(util.getBaseUrl()+"/my/feed");
        assertTrue(util.isElementPresent(driver, By.xpath("//h1[text()=\"Лента\"]")));
        assertTrue(util.isElementPresent(driver, By.xpath("//h3[text()=\"Рекомендуемые сообщества\"]")));
        assertTrue(util.isElementPresent(driver, By.xpath("//h3[text()=\"Стоит почитать\"]")));
        driver.quit();
    }

    @Test
    public void viewFeed() {
        doViewFeed(new FirefoxDriver());
        doViewFeed(new ChromeDriver());
    }
}