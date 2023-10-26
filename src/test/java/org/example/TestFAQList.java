package org.example;

import org.example.pageobject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestFAQList {

    //@Parameterized.Parameter(0)
    //public int number;
    @Parameterized.Parameter(0)
    public String questionText;
    @Parameterized.Parameter(1)
    public String answerExpected;



        private WebDriver driver;

        @Before
        public void setDriver() {
            // Закрой браузер
            driver = new ChromeDriver();
            // перешли на страницу тестового приложения
            driver.get("https://qa-scooter.praktikum-services.ru/");
        }


    @Parameterized.Parameters(name = "Тест {index}: на вопрос {0} нужно получить ответ {1}")
    public static Object[][] data() {
        return new Object[][] {
    {"Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
    {"Хочу сразу несколько самокатов! Так можно?",  "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
    {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."}, {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
    {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
    {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
    {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
    {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }


        // дождаться открытия страницы

    @Test
    public void RunTestFAQList()  {

        MainPage objMainPage = new MainPage(driver);
        objMainPage.scrollToFAQList();


        By question = By.xpath(".//div[@class='accordion__button' and text()='"+ questionText +"']");
        driver.findElement(question).click();
        String attrib = driver.findElement(question).getAttribute("id");
        int idNum = attrib.lastIndexOf("-");
        String idNumStr = attrib.substring(idNum+1);
        String attribAnswer = "accordion__panel-" + idNumStr;
        String answerXPath = ".//div[@id='"+attribAnswer+"']/p";

        By answer = By.xpath(answerXPath);

        WebElement ans = driver.findElement(answer);
        String answerFound = ans.getText() ;
        assertEquals("Не тот ответ!", answerExpected, answerFound);
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}



