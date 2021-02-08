package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTestBankForm {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void cardOrderForm() {
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("Ильдар");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+79784440001");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".Success_successBlock__2L3Cw")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", checkText.trim());
    }

    @Test
    void cardOrderFormIfNoName() {
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+79784440001");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", checkText);
    }

    @Test
    void cardOrderFormIfNoTel() {
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("Ильдар");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", checkText);
    }

    @Test
    void cardOrderFormIfNotValidName() {
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("znqa-11");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+79784440001");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", checkText);
    }

    @Test
    void cardOrderFormIfShortTel() {
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("Ильдар");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+7978");
        driver.findElement((By.cssSelector(".checkbox__box"))).click();
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", checkText);
    }

    @Test
    void cardOrderFormIfNotClickCheckBox() {
        driver.findElement((By.cssSelector("[type=text]"))).sendKeys("Ильдар");
        driver.findElement((By.cssSelector("[type=tel]"))).sendKeys("+79784440001");
        driver.findElement((By.cssSelector(".button__text"))).submit();
        String checkText = driver.findElement(By.cssSelector(".checkbox__text")).getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", checkText);
    }
}

