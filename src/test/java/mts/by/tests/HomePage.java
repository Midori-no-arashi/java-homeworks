package mts.by.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void closeCookieBanner() {
        try {
            By cookieButton = By.xpath("//button[contains(@class, 'cookie')] | //div[contains(@class, 'cookie')]//button | //*[contains(text(), 'Принять')] | //button[contains(text(), 'OK')]");
            List<WebElement> buttons = driver.findElements(cookieButton);
            if (!buttons.isEmpty()) {
                buttons.get(0).click();
                System.out.println("Баннер куки закрыт");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Баннер куки не найден или уже закрыт");
        }
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnlinePaymentBlockDisplayed() {
        try {
            waitForPageLoad();
            closeCookieBanner();
            By selector = By.xpath("//h2[contains(text(), 'Онлайн пополнение')]");
            return driver.findElements(selector).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String getBlockTitle() {
        waitForPageLoad();
        closeCookieBanner();
        WebElement title = driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]"));
        return title.getText();
    }

    public int getPaymentLogosCount() {
        waitForPageLoad();
        closeCookieBanner();

        By[] logoSelectors = {
                By.xpath("//div[contains(@class, 'payment')]//img"),
                By.xpath("//section[contains(@class, 'payment')]//img"),
                By.xpath("//img[contains(@src, 'visa')]"),
                By.xpath("//img[contains(@src, 'mastercard')]"),
                By.xpath("//div[contains(@class, 'cards')]//img"),
                By.xpath("//div[contains(@class, 'payment-methods')]//img")
        };

        int total = 0;
        for (By selector : logoSelectors) {
            List<WebElement> elements = driver.findElements(selector);
            total += elements.size();
        }

        return total;
    }

    public List<WebElement> getPaymentLogos() {
        waitForPageLoad();
        closeCookieBanner();
        return driver.findElements(By.xpath("//div[contains(@class, 'payment')]//img"));
    }

    public void clickMoreDetails() {
        waitForPageLoad();
        closeCookieBanner();

        try {
            Thread.sleep(1000);

            By[] linkSelectors = {
                    By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"),
                    By.xpath("//a[contains(text(), 'Подробнее')]"),
                    By.xpath("//a[contains(@href, 'poryadok-oplaty')]"),
                    By.xpath("//a[contains(@href, 'help')]"),
                    By.xpath("//div[contains(@class, 'payment-block')]//a")
            };

            WebElement link = null;
            for (By selector : linkSelectors) {
                List<WebElement> elements = driver.findElements(selector);
                if (!elements.isEmpty()) {
                    link = elements.get(0);
                    System.out.println("Найдена ссылка по селектору: " + selector);
                    break;
                }
            }

            if (link == null) {
                System.out.println("Ссылка не найдена");
                return;
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
            Thread.sleep(500);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
            System.out.println("Клик по ссылке 'Подробнее о сервисе' выполнен через JavaScript");

        } catch (Exception e) {
            System.out.println("Не удалось кликнуть по ссылке: " + e.getMessage());
        }
    }

    public void selectServiceType() {
        waitForPageLoad();
        closeCookieBanner();

        try {
            By serviceSelector = By.xpath("//span[contains(text(), 'Услуги связи')] | //label[contains(text(), 'Услуги связи')] | //div[contains(text(), 'Услуги связи')]");
            WebElement service = wait.until(ExpectedConditions.elementToBeClickable(serviceSelector));
            service.click();
            System.out.println("Выбрана услуга 'Услуги связи'");
        } catch (Exception e) {
            System.out.println("Не удалось выбрать услугу: " + e.getMessage());
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        waitForPageLoad();
        closeCookieBanner();

        try {
            By phoneSelector = By.xpath("//input[@placeholder='Номер телефона'] | //input[@type='tel'] | //input[contains(@id, 'phone')]");
            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneSelector));
            phoneField.clear();
            phoneField.sendKeys(phoneNumber);
            System.out.println("Введен номер телефона: " + phoneNumber);
        } catch (Exception e) {
            System.out.println("Не удалось найти поле для номера телефона: " + e.getMessage());
        }
    }

    public void enterAmount(String amount) {
        waitForPageLoad();
        closeCookieBanner();

        try {
            By amountSelector = By.xpath("//input[@placeholder='Сумма'] | //input[@type='number'] | //input[contains(@id, 'amount')]");
            WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(amountSelector));
            amountField.clear();
            amountField.sendKeys(amount);
            System.out.println("Введена сумма: " + amount);
        } catch (Exception e) {
            System.out.println("Не удалось найти поле для суммы: " + e.getMessage());
        }
    }

    public void clickContinue() {
        waitForPageLoad();
        closeCookieBanner();

        try {
            By buttonSelector = By.xpath("//button[contains(text(), 'Продолжить')] | //button[contains(@class, 'continue')] | //button[@type='submit']");
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonSelector));
            button.click();
            System.out.println("Нажата кнопка 'Продолжить'");
        } catch (Exception e) {
            System.out.println("Не удалось нажать кнопку 'Продолжить': " + e.getMessage());
        }
    }

    public boolean isPaymentModalDisplayed() {
        try {
            Thread.sleep(3000);
            By[] modalSelectors = {
                    By.xpath("//div[contains(@class, 'modal')]"),
                    By.xpath("//div[contains(@role, 'dialog')]"),
                    By.xpath("//iframe[contains(@src, 'payment')]")
            };

            for (By selector : modalSelectors) {
                List<WebElement> elements = driver.findElements(selector);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    System.out.println("Найдено модальное окно");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}