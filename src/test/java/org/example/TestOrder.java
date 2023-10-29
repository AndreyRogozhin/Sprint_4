package org.example;

import org.example.pageobject.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOrder{

@Parameterized.Parameter(0)
public String firstName;
@Parameterized.Parameter(1)
public String lastName;
@Parameterized.Parameter(2)
public String address;
@Parameterized.Parameter(3)
public String metroStation;
@Parameterized.Parameter(4)
public String phoneNumber;
@Parameterized.Parameter(5)
public String dateStart;
@Parameterized.Parameter(6)
public String numberOfDays;
@Parameterized.Parameter(7)
public String color;
@Parameterized.Parameter(8)
public String comment;




    private WebDriver driver;

    @Rule
    public BrowserRule browserRule = new BrowserRule();


    @Parameterized.Parameters(name = "Тест {index}: набор параметров {0}, {1}, {2}, {3} ,{4} ,{5} ,{6}, {7}, {8}, {9}")
    public static Object[][] data() {
        return new Object[][]{
                {"Антон", "Сергеев", "Москва, Лесная, 21", "Белорусская", "+79990001122", "02.05.2015", "трое суток", "black", "Нет никаких комментариев"},
                // второй набор данных о заказчике - латинские буквы не проходят валидацию в форме ввода данных
                {"Andrew", "Fawler", "Moscow-city, Tverskaya str,15", "Римская", "NNNNNNNNNN", "01.05.2015", "двое суток", "grey", "Нет никаких комментариев"},
        };

    }

    @Test
    public void runTestOrder()  {

        MainPage objMainPage = new MainPage(browserRule.getWebDriver());
        OrderPage objOrderPage = new OrderPage(browserRule.getWebDriver());
        RentPage objRentPage = new RentPage(browserRule.getWebDriver());
        ConfirmPage objConfirmPage = new ConfirmPage(browserRule.getWebDriver());

        String orderPageUrl = Url.ORDER_PAGE;

        objMainPage.clickOrderUpperButton();

        // дождаться открытия страницы
        // проверить, что открылась страница с первой формой для заполнения
        Assert.assertTrue("По нажатию верхней кнопки не открылась страница для оформления заказа",objOrderPage.checkOrderPageShown(orderPageUrl));
        objOrderPage.fillOrderForm(firstName, lastName, address, metroStation, phoneNumber );
        objOrderPage.orderDataSendButtonClick();

        // дождаться открытия страницы
        // проверить, что открылась страница со второй формой для заполнения
        assertTrue("Не открылась страница заполнения данных об аренде", objRentPage.checkRentPageShown());

        objRentPage.fillRentForm(dateStart, numberOfDays, color,comment);
        objRentPage.rentDataSendButtonClick();

        objConfirmPage.clickConfirmButton();

        // проверить, что открылась страница с информацией об оформленном заказе
        assertTrue("Не открылась форма с подтверждением оформления заказа", objConfirmPage.checkOrderFinished());

    }

}

