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

public class CommunitiesTest {
    Util util = new Util();

    private void doViewCommunity(WebDriver driver){
        util.prepare(driver);
        //util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        driver.get(util.getBaseUrl() + "communities/search");
        util.tryClick(driver, By.xpath("(//a[@class='c-link c-link--text'])[2]"));
        driver.quit();
    }

    @Test
    public void view() {
        doViewCommunity(new FirefoxDriver());
        doViewCommunity(new ChromeDriver());

    }
}