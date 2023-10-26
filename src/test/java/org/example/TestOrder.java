package org.example;

import org.example.pageobject.ConfirmPage;
import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;
import org.example.pageobject.RentPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class TestOrder{

    private WebDriver driver;

    @Before
    public void setDriver() {

        driver = new ChromeDriver();
        // перешли на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // дождаться открытия страницы


    }

    @Test
    public void RunTestOrder()  {

        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);
        RentPage objRentPage = new RentPage(driver);
        ConfirmPage objConfirmPage = new ConfirmPage(driver);

//        String rentPageUrl = "";
        String orderpageUrl = "https://qa-scooter.praktikum-services.ru/order";




      /*  WebElement element = driver.findElement(By.className("Home_FAQ__3uVm4"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);

       */

        objMainPage.clickOrderButton();

        // дождаться открытия страницы
        // проверить, что открылась страница с первой формой для заполнения
        Assert.assertTrue("Не открылась страница для оформления заказа",objOrderPage.checkOrderPageShown(orderpageUrl));
        objOrderPage.fillOrderForm("Антон", "Сергеев", "Москва, Лесная, 21", "Белорусская", "+79990001122");

        // второй набор данных о заказчике - латинские буквы не проходят валидацию в форме ввода данных
        //        objOrderPage.fillOrderForm("Andrew", "Fawler", "Moscow-city, Tverskaya str,15", "Римская", "NNNNNNNNNN");


        objOrderPage.orderDataSendButtonClick();

        // дождаться открытия страницы
        // проверить, что открылась страница со второй формой для заполнения
        assertTrue("Не открылась страница заполнения данных об аренде", objRentPage.checkRentPageShown());

        objRentPage.fillRentForm("02.05.2015", "трое суток", "black", "Нет никаких комментариев");
        objRentPage.rentDataSendButtonClick();

        objConfirmPage.clickConfirmButton();

        // проверить, что открылась страница с информацией об оформленном заказе
        assertTrue("Не открылась форма с подтверждением оформления заказа", objConfirmPage.checkOrderFinished());

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}

