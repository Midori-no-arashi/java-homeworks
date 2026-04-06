package mts.by.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    public boolean isModalDisplayed() {
        waitForPageLoad();

        By[] modalSelectors = {
                By.xpath("//div[contains(@class, 'modal')]"),
                By.xpath("//div[contains(@class, 'Modal')]"),
                By.xpath("//div[contains(@class, 'dialog')]"),
                By.xpath("//div[contains(@role, 'dialog')]"),
                By.xpath("//iframe[contains(@src, 'pay')]"),
                By.xpath("//div[contains(@class, 'payment')]//iframe")
        };

        for (By selector : modalSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                System.out.println("Найдено модальное окно по селектору: " + selector);
                return true;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("pay") || currentUrl.contains("payment")) {
            System.out.println("Редирект на страницу оплаты: " + currentUrl);
            return true;
        }

        return false;
    }

    public String getDisplayedPhoneNumber() {
        waitForPageLoad();

        By[] phoneSelectors = {
                By.xpath("//*[contains(text(), '297777777')]"),
                By.xpath("//div[contains(@class, 'phone')]"),
                By.xpath("//span[contains(@class, 'phone')]"),
                By.xpath("//*[contains(text(), 'Номер')]/following-sibling::*")
        };

        for (By selector : phoneSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                String text = elements.get(0).getText();
                if (text != null && !text.isEmpty()) {
                    System.out.println("Найден номер телефона: " + text);
                    return text;
                }
            }
        }

        return "Номер не найден";
    }

    public String getDisplayedAmount() {
        waitForPageLoad();

        By[] amountSelectors = {
                By.xpath("//*[contains(text(), '10.00')]"),
                By.xpath("//div[contains(@class, 'amount')]"),
                By.xpath("//span[contains(@class, 'amount')]"),
                By.xpath("//*[contains(text(), 'Сумма')]/following-sibling::*")
        };

        for (By selector : amountSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                String text = elements.get(0).getText();
                if (text != null && !text.isEmpty()) {
                    System.out.println("Найдена сумма: " + text);
                    return text;
                }
            }
        }

        return "Сумма не найдена";
    }

    public String getPayButtonAmount() {
        waitForPageLoad();

        By[] buttonSelectors = {
                By.xpath("//button[contains(text(), 'Оплатить')]"),
                By.xpath("//button[contains(text(), 'Pay')]"),
                By.xpath("//button[contains(@class, 'pay')]"),
                By.xpath("//button[@type='submit']")
        };

        for (By selector : buttonSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            if (!elements.isEmpty()) {
                String text = elements.get(0).getText();
                if (text != null && !text.isEmpty()) {
                    System.out.println("Найдена кнопка с текстом: " + text);
                    return text;
                }
            }
        }

        return "Кнопка не найдена";
    }

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
                System.out.println("Не найдено поле: " + selector);
                return false;
            } else {
                String placeholder = elements.get(0).getAttribute("placeholder");
                System.out.println("Поле найдено, placeholder: " + placeholder);
            }
        }

        return true;
    }

    public int getPaymentIconsCount() {
        waitForPageLoad();

        By[] iconSelectors = {
                By.xpath("//img[contains(@src, 'visa')]"),
                By.xpath("//img[contains(@src, 'mastercard')]"),
                By.xpath("//div[contains(@class, 'payment-icons')]//img"),
                By.xpath("//div[contains(@class, 'cards')]//img")
        };

        int total = 0;
        for (By selector : iconSelectors) {
            total += driver.findElements(selector).size();
        }

        System.out.println("Найдено иконок: " + total);
        return total;
    }

    public boolean hasVisaIcon() {
        waitForPageLoad();
        return !driver.findElements(By.xpath("//img[contains(@src, 'visa')]")).isEmpty();
    }

    public boolean hasMastercardIcon() {
        waitForPageLoad();
        return !driver.findElements(By.xpath("//img[contains(@src, 'mastercard')]")).isEmpty();
    }

    public boolean hasBelcardIcon() {
        waitForPageLoad();
        return !driver.findElements(By.xpath("//img[contains(@src, 'belcard')]")).isEmpty();
    }
}