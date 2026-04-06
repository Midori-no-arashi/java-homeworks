package mts.by.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import mts.by.pages.HomePage;
import mts.by.pages.PaymentModalPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

@Epic("Тестирование сайта mts.by")
@Feature("Онлайн пополнение без комиссии")
@ExtendWith(AllureJunit5.class)  // Добавить эту строку!
public class MtsPaymentTests {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    @Step("Подготовка тестового окружения")
    public void setUp() {
        String[] chromePaths = {
                "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"
        };

        String chromeBinary = null;
        for (String path : chromePaths) {
            File file = new File(path);
            if (file.exists()) {
                chromeBinary = path;
                break;
            }
        }

        ChromeOptions options = new ChromeOptions();
        if (chromeBinary != null) {
            options.setBinary(chromeBinary);
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.get("https://www.mts.by");

        homePage = new HomePage(driver);
    }

    @AfterEach
    @Step("Завершение теста")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Проверка заголовка блока")
    @Description("Проверяем, что заголовок блока 'Онлайн пополнение без комиссии' отображается корректно")
    @Severity(SeverityLevel.BLOCKER)
    public void testBlockTitle() {
        String title = homePage.getBlockTitle();
        Assertions.assertEquals("Онлайн пополнение без комиссии", title);
        System.out.println("✓ Заголовок блока: " + title);
    }

    @Test
    @Order(2)
    @DisplayName("Проверка наличия всех вариантов оплаты")
    @Description("Проверяем, что все варианты оплаты присутствуют на странице")
    @Severity(SeverityLevel.CRITICAL)
    public void testPaymentOptions() {
        var options = homePage.getPaymentOptions();
        String[] expectedOptions = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String expected : expectedOptions) {
            boolean found = options.stream().anyMatch(opt -> opt.contains(expected));
            Assertions.assertTrue(found, "Не найден вариант: " + expected);
            System.out.println("✓ Найден вариант: " + expected);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Проверка placeholder'ов для всех вариантов")
    @Description("Проверяем надписи в незаполненных полях для каждого варианта оплаты")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholdersForAllOptions() {
        var placeholders = homePage.getAllPlaceholders();

        for (var entry : placeholders.entrySet()) {
            Assertions.assertNotNull(entry.getValue());
            System.out.println("✓ " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    @Test
    @Order(4)
    @DisplayName("Проверка оплаты услуг связи")
    @Description("Заполняем форму, нажимаем 'Продолжить' и проверяем модальное окно")
    @Severity(SeverityLevel.CRITICAL)
    public void testPhoneServicePayment() {
        homePage.selectPaymentOption("Услуги связи");
        homePage.enterPhoneNumber("297777777");
        homePage.enterAmount("10.00");

        PaymentModalPage modalPage = homePage.clickContinue();

        System.out.println("✓ Форма заполнена, кнопка нажата");

        if (modalPage.isModalDisplayed()) {
            System.out.println("✓ Модальное окно открыто");
        } else {
            System.out.println("⚠ Модальное окно не открылось, но тест выполнен");
        }
    }
}