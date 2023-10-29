package org.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

        // верхняя кнопка "Заказать"  локатор
    private final By orderUpperButton = By.xpath(".//button[@class='Button_Button__ra12g']");

    // нижняя кнопка "Заказать"  локатор
    private final By orderLowerButton = By.className("Button_Middle__1CSJM");

   //раздел "Вопросы о важном"  - локатор раздела
   private final By listOfFAQ = By.className("Home_FAQ__3uVm4");

   public MainPage(WebDriver driver) {
        this.driver = driver;}


//  нажатие верхней кнопки "Заказать"
   public void clickOrderUpperButton(){
        driver.findElement(orderUpperButton).click();
    }


    // прокрутить до нижней кнопки "Заказать"
    public void scrollToOrderLowerButton(){
        WebElement element = driver.findElement(orderLowerButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
    }

    //  нажатие нижней кнопки "Заказать"
    public void clickOrderLowerButton(){
        driver.findElement(orderLowerButton).click();
    }


    // прокрутить до вопросов о важном
   public void scrollToListOfFAQ(){
       WebElement element = driver.findElement(listOfFAQ);
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
   }

   public String findAnswerByQuestion(String questionText){
       By question = By.xpath(".//div[@class='accordion__button' and text()='"+ questionText +"']");
       driver.findElement(question).click();
       String attrib = driver.findElement(question).getAttribute("id");
       int idNum = attrib.lastIndexOf("-");
       String idNumStr = attrib.substring(idNum+1);
       String attribAnswer = "accordion__panel-" + idNumStr;
       String answerXPath = ".//div[@id='"+attribAnswer+"']/p";

       By answer = By.xpath(answerXPath);

       return driver.findElement(answer).getText() ;

   }

}
