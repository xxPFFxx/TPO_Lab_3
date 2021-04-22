package lab;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommunitiesTest {
    Util util = new Util();

    private void doViewCommunity(WebDriver driver){
        util.prepare(driver);
        util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        driver.get(util.getBaseUrl() + "communities/search"); // Просмотр всех сообществ
        util.tryClick(driver, By.xpath("(//a[@class='c-link c-link--text'])[2]")); // Переходим по первому сообществу
        driver.quit();
    }

    private void doJoinAndLeaveCommunity(WebDriver driver){
        util.prepare(driver);
        util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        driver.get(util.getBaseUrl() + "communities/search"); // Просмотр всех сообществ
        util.tryClick(driver, By.xpath("(//a[@class='c-link c-link--text'])[2]")); // Переходим по первому сообществу
        util.tryClick(driver, By.xpath("(//button[text()='Хочу вступить'])")); // Попытка вступить в сообщество
        assertTrue(util.isElementPresent(driver, By.xpath("//button[text()='Пригласить']")));
        assertTrue(util.isElementPresent(driver, By.xpath("//button[text()='Покинуть']")));
        driver.get(util.getBaseUrl() + "communities/");
        assertFalse(util.isElementPresent(driver, By.xpath("//div[@class='c-empty-content']"))); // Проверка, что список сообществ не пустой
        util.tryClick(driver, By.xpath("(//a[@class=\"c-link c-link--text\"])[1]"));
        util.tryClick(driver, By.xpath("//button[text()='Покинуть']"));
        util.tryClick(driver, By.xpath("(//button[@class=\"c-button c-button--s c-bubble__confirm\"])"));
        assertTrue(util.isElementPresent(driver, By.xpath("//div[@class='c-empty-content']")));
        driver.quit();
    }

    @Test
    public void view() {
        doViewCommunity(new FirefoxDriver());
        doViewCommunity(new ChromeDriver());

    }

    @Test
    public void joinAndLeave() {
        doJoinAndLeaveCommunity(new FirefoxDriver());
        doJoinAndLeaveCommunity(new ChromeDriver());

    }
}