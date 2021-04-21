package lab;

import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Util {
    public String getBaseUrl() {
        return baseUrl;
    }

    private String baseUrl;
    private final String correctLogin = "cucumber_2000@mail.ru";
    private final String correctPassword = "anv66meMsPFEU.J";

    public String getCorrectLogin() {
        return correctLogin;
    }

    public String getCorrectPassword() {
        return correctPassword;
    }

    public Util() {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Daniil\\Downloads\\geckodriver-v0.29.1-win64\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Daniil\\Downloads\\chromedriver_win32\\chromedriver.exe");
        baseUrl = "https://www.drive2.ru/";
    }

    public void prepare(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(getBaseUrl());
    }

    public boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void tryClick(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

//        element.click();
        element.sendKeys(Keys.ENTER);
    }

    public void auth(WebDriver driver, String login, String password){
        String loginPath = "//input[@name='Login']";
        String passwordPath = "//input[@name='Password']";
        driver.findElement(By.xpath("//a[text()='Войти']")).click();
        driver.findElement(By.xpath(loginPath)).clear();
        driver.findElement(By.xpath(loginPath)).sendKeys(login);
        driver.findElement(By.xpath(passwordPath));
        driver.findElement(By.xpath(passwordPath)).sendKeys(password);;
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
    }
}