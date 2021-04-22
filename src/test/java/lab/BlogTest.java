package lab;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlogTest {
    Util util = new Util();

    private void doAddEntry(WebDriver driver){
        util.prepare(driver);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
        util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        util.tryClick(driver, By.xpath("//a[text()='Личный блог']")); // Переход в личный блог
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        util.tryClick(driver, By.xpath("//a[text()='Написать в блог']")); // Создание нового поста
        driver.findElement(By.id("jtitle")).clear();
        driver.findElement(By.id("jtitle")).sendKeys("test"); // Тема поста
        driver.findElement(By.id("text")).clear();
        driver.findElement(By.id("text")).sendKeys("test"); // Содержимое поста
        util.tryClick(driver, By.xpath("//button[text()='Опубликовать запись']"));
        driver.quit();
    }

    private void doBookmarkEntry(WebDriver driver) {
        util.prepare(driver);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
        util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        util.tryClick(driver, By.xpath("//a[@class=\"c-cotd__caption c-link c-link--white c-link--nd\"]")); // Нажимаем на один из постов (тут Машина Дня)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        util.tryClick(driver, By.xpath("//button[@class=\"r-button-unstyled c-bookmarks-button c-bookmarks-button--l c-bookmarks-button--inverted \"]")); // Добавляем в закладки
        assertTrue(util.isElementPresent(driver, By.xpath("//button[contains(@class, 'c-bookmarks-button') and contains(@class, 'is-active')]")));
        util.tryClick(driver, By.xpath("//button[contains(@class, 'c-bookmarks-button') and contains(@class, 'is-active')]")); // Удаляем из закладок
        driver.quit();
    }

    @Test
    public void addEntry(){
        doAddEntry(new FirefoxDriver());
        doAddEntry(new ChromeDriver());

    }

    @Test
    public void bookmarkEntry() {
        doBookmarkEntry(new FirefoxDriver());
        doBookmarkEntry(new ChromeDriver());

    }
}