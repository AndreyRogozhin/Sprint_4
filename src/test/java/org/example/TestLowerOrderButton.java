package org.example;

import org.example.pageobject.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestLowerOrderButton {

    private WebDriver driver;

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void runOrderLowerButton()  {

        MainPage objMainPage = new MainPage(browserRule.getWebDriver());
        OrderPage objOrderPage = new OrderPage(browserRule.getWebDriver());

        String orderPageUrl = Url.ORDER_PAGE;

        objMainPage.scrollToOrderLowerButton();
        objMainPage.clickOrderLowerButton();

        // дождаться открытия страницы
        // проверить, что открылась страница с первой формой для заполнения
        Assert.assertTrue("По нажатию нижней кнопки не открылась страница для оформления заказа",objOrderPage.checkOrderPageShown(orderPageUrl));

    }

}
