package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTestBankForm {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\QA igabi\\Projects\\Autotesting\\Selenium\\driver\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

//    @AfterEach
//    void tearDown() {
//        driver.quit();
//        driver = null;
//    }

    @Test
    void cardOrderForm(){
        driver.get("http://localhost:9999/");
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("Ильдар");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+79784440001");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".Success_successBlock__2L3Cw")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", checkText.trim());
    }
    @Test
    void cardOrderFormIfNoName(){
        driver.get("http://localhost:9999/");
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+79784440001");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", checkText);
    }

    @Test
    void cardOrderFormIfNoTel(){
        driver.get("http://localhost:9999/");
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("Ильдар");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", checkText);
    }

    @Test
    void cardOrderFormIfNotValidName(){
        driver.get("http://localhost:9999/");
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("znqa-11");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+79784440001");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", checkText);
    }

    @Test
    void cardOrderFormIfShortTel(){
        driver.get("http://localhost:9999/");
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("Ильдар");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+7978");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", checkText);
    }
}

