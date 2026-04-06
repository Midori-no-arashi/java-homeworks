package mts.by.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentModalPage extends BasePage {

    public PaymentModalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Проверка открытия модального окна")
    public boolean isModalDisplayed() {
        waitForPageLoad();

        By[] modalSelectors = {
                By.xpath("//div[contains(@class, 'modal')]"),
                By.xpath("//div[contains(@class, 'Modal')]"),
                By.xpath("//div[contains(@class, 'dialog')]"),
                By.xpath("//div[contains(@role, 'dialog')]"),
                By.xpath("//iframe[contains(@src, 'pay')]")
        };

        for (By selector : modalSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                return true;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("pay") || currentUrl.contains("payment");
    }

    @Step("Получение отображаемого номера телефона")
    public String getDisplayedPhoneNumber() {
        waitForPageLoad();

        By[] phoneSelectors = {
                By.xpath("//*[contains(text(), '297777777')]"),
                By.xpath("//div[contains(@class, 'phone')]"),
                By.xpath("//span[contains(@class, 'phone')]")
        };

        for (By selector : phoneSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                return elements.get(0).getText();
            }
        }

        return "Номер не найден";
    }

    @Step("Получение отображаемой суммы")
    public String getDisplayedAmount() {
        waitForPageLoad();

        By[] amountSelectors = {
                By.xpath("//*[contains(text(), '10.00')]"),
                By.xpath("//div[contains(@class, 'amount')]"),
                By.xpath("//span[contains(@class, 'amount')]")
        };

        for (By selector : amountSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                return elements.get(0).getText();
            }
        }

        return "Сумма не найдена";
    }

    @Step("Получение суммы на кнопке оплаты")
    public String getPayButtonAmount() {
        waitForPageLoad();

        By[] buttonSelectors = {
                By.xpath("//button[contains(text(), 'Оплатить')]"),
                By.xpath("//button[contains(text(), 'Pay')]"),
                By.xpath("//button[contains(@class, 'pay')]")
        };

        for (By selector : buttonSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                return elements.get(0).getText();
            }
        }

        return "Кнопка не найдена";
    }

    @Step("Проверка полей ввода карты")
    public boolean areCardFieldsPlaceholdersCorrect() {
        waitForPageLoad();

        By[] cardFieldSelectors = {
                By.xpath("//input[@placeholder='Номер карты']"),
                By.xpath("//input[contains(@placeholder, 'срок')]"),
                By.xpath("//input[contains(@placeholder, 'CVC')] | //input[contains(@placeholder, 'cvc')]"),
                By.xpath("//input[contains(@placeholder, 'имя')]")
        };

        for (By selector : cardFieldSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (elements.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Step("Подсчет иконок платежных систем")
    public int getPaymentIconsCount() {
        waitForPageLoad();

        By[] iconSelectors = {
                By.xpath("//img[contains(@src, 'visa')]"),
                By.xpath("//img[contains(@src, 'mastercard')]"),
                By.xpath("//div[contains(@class, 'payment-icons')]//img")
        };

        int total = 0;
        for (By selector : iconSelectors) {
            total += driver.findElements(selector).size();
        }

        return total;
    }

    @Step("Проверка наличия иконки Visa")
    public boolean hasVisaIcon() {
        waitForPageLoad();
        return !driver.findElements(By.xpath("//img[contains(@src, 'visa')]")).isEmpty();
    }

    @Step("Проверка наличия иконки Mastercard")
    public boolean hasMastercardIcon() {
        waitForPageLoad();
        return !driver.findElements(By.xpath("//img[contains(@src, 'mastercard')]")).isEmpty();
    }
}