package org.example.pageobject;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserRule extends ExternalResource {

    private WebDriver driver;

    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    protected void before() {
        driver = new ChromeDriver();
        // перешли на страницу тестового приложения
        driver.get(Url.MAIN_PAGE);
    }

    @Override
    protected void after() {
        driver.quit();
    }
}