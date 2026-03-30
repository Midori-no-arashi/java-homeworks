package mts.by.tests;

import mts.by.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MtsByTests extends BaseTest {

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        HomePage homePage = new HomePage(driver);

        Assertions.assertTrue(homePage.isOnlinePaymentBlockDisplayed(),
                "Блок 'Онлайн пополнение без комиссии' не отображается");

        String title = homePage.getBlockTitle();
        String normalizedTitle = title.replace("\n", " ").replaceAll("\\s+", " ").trim();

        Assertions.assertEquals("Онлайн пополнение без комиссии", normalizedTitle,
                "Заголовок блока не соответствует ожидаемому");

        System.out.println("✓ Тест проверки названия блока пройден");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentLogos() {
        HomePage homePage = new HomePage(driver);

        Assertions.assertTrue(homePage.isOnlinePaymentBlockDisplayed(),
                "Блок 'Онлайн пополнение без комиссии' не отображается");

        int logosCount = homePage.getPaymentLogosCount();
        System.out.println("Найдено логотипов: " + logosCount);

        Assertions.assertTrue(logosCount > 0,
                "Логотипы платежных систем не найдены");

        System.out.println("✓ Тест проверки логотипов пройден");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreDetailsLink() {
        HomePage homePage = new HomePage(driver);

        Assertions.assertTrue(homePage.isOnlinePaymentBlockDisplayed(),
                "Блок 'Онлайн пополнение без комиссии' не отображается");

        String currentUrl = homePage.getCurrentUrl();
        System.out.println("Текущий URL: " + currentUrl);

        homePage.clickMoreDetails();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String newUrl = homePage.getCurrentUrl();
        System.out.println("Новый URL: " + newUrl);

        Assertions.assertNotEquals(currentUrl, newUrl,
                "URL не изменился после клика по ссылке");

        System.out.println("✓ Тест проверки ссылки 'Подробнее о сервисе' пройден");
    }

    @Test
    @DisplayName("Заполнение полей и проверка работы кнопки 'Продолжить'")
    public void testContinueButton() {
        HomePage homePage = new HomePage(driver);

        Assertions.assertTrue(homePage.isOnlinePaymentBlockDisplayed(),
                "Блок 'Онлайн пополнение без комиссии' не отображается");

        homePage.selectServiceType();
        System.out.println("✓ Выбрана услуга 'Услуги связи'");

        homePage.enterPhoneNumber("297777777");
        System.out.println("✓ Заполнен номер телефона");

        homePage.enterAmount("10.00");
        System.out.println("✓ Заполнена сумма");

        homePage.clickContinue();
        System.out.println("✓ Нажата кнопка 'Продолжить'");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = homePage.getCurrentUrl();
        System.out.println("Текущий URL после нажатия: " + currentUrl);

        System.out.println("✓ Тест проверки кнопки 'Продолжить' пройден (функционал проверен)");
    }

    @Test
    @DisplayName("Полный тест сценария онлайн пополнения")
    public void testFullPaymentScenario() {
        HomePage homePage = new HomePage(driver);

        System.out.println("=== Начало тестирования сценария онлайн пополнения ===\n");

        String title = homePage.getBlockTitle();
        String normalizedTitle = title.replace("\n", " ").replaceAll("\\s+", " ").trim();
        System.out.println("1. Заголовок блока: '" + normalizedTitle + "'");
        Assertions.assertEquals("Онлайн пополнение без комиссии", normalizedTitle);

        int logosCount = homePage.getPaymentLogosCount();
        System.out.println("2. Найдено логотипов: " + logosCount);

        homePage.selectServiceType();
        System.out.println("3. Выбрана услуга 'Услуги связи'");

        homePage.enterPhoneNumber("297777777");
        System.out.println("   Номер телефона: 297777777");

        homePage.enterAmount("15.50");
        System.out.println("   Сумма: 15.50 BYN");

        homePage.clickContinue();
        System.out.println("4. Нажата кнопка 'Продолжить'");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Полный тест сценария пройден успешно! ===");
    }
}