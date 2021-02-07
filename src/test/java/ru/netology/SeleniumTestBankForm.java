package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTestBankForm {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
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


}

