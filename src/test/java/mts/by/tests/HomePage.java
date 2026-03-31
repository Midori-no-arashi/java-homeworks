package mts.by.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение')]")
    private WebElement blockTitle;

    private By serviceTab = By.xpath("//*[contains(text(), 'Услуги связи')]");
    private By internetTab = By.xpath("//*[contains(text(), 'Домашний интернет')]");
    private By installmentTab = By.xpath("//*[contains(text(), 'Рассрочка')]");
    private By debtTab = By.xpath("//*[contains(text(), 'Задолженность')]");

    private By phoneInput = By.xpath("//input[@placeholder='Номер телефона']");
    private By internetInput = By.xpath("//input[@placeholder='Номер абонента']");
    private By installmentInput = By.xpath("//input[contains(@placeholder, 'Номер')] | //input[contains(@placeholder, 'номер')] | //input[contains(@placeholder, 'договор')]");
    private By debtInput = By.xpath("//input[contains(@placeholder, 'лицевой')]");
    private By amountInput = By.xpath("//input[@placeholder='Сумма']");

    private By continueButton = By.xpath("//button[contains(text(), 'Продолжить')]");

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка заголовка блока")
    public String getBlockTitle() {
        waitForPageLoad();
        closeCookieBanner();
        return blockTitle.getText().replace("\n", " ").replaceAll("\\s+", " ").trim();
    }

    @Step("Получение списка вариантов оплаты")
    public List<String> getPaymentOptions() {
        waitForPageLoad();
        closeCookieBanner();

        List<String> options = new java.util.ArrayList<>();
        By[] selectors = {serviceTab, internetTab, installmentTab, debtTab};
        String[] names = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (int i = 0; i < selectors.length; i++) {
            List<WebElement> elements = driver.findElements(selectors[i]);
            if (!elements.isEmpty()) {
                options.add(names[i]);
            }
        }

        return options;
    }

    @Step("Выбор варианта оплаты: {option}")
    public void selectPaymentOption(String option) {
        waitForPageLoad();
        closeCookieBanner();

        By tabSelector = null;
        switch (option) {
            case "Услуги связи":
                tabSelector = serviceTab;
                break;
            case "Домашний интернет":
                tabSelector = internetTab;
                break;
            case "Рассрочка":
                tabSelector = installmentTab;
                break;
            case "Задолженность":
                tabSelector = debtTab;
                break;
        }

        if (tabSelector != null) {
            List<WebElement> tabs = driver.findElements(tabSelector);
            if (!tabs.isEmpty()) {
                WebElement tab = tabs.get(0);
                scrollToElement(tab);
                clickByJS(tab);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Step("Заполнение номера телефона: {phoneNumber}")
    public void enterPhoneNumber(String phoneNumber) {
        waitForPageLoad();
        closeCookieBanner();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        field.clear();
        field.sendKeys(phoneNumber);
    }

    @Step("Заполнение суммы: {amount}")
    public void enterAmount(String amount) {
        waitForPageLoad();
        closeCookieBanner();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput));
        field.clear();
        field.sendKeys(amount);
    }

    @Step("Нажатие кнопки 'Продолжить'")
    public PaymentModalPage clickContinue() {
        waitForPageLoad();
        closeCookieBanner();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        scrollToElement(button);
        clickByJS(button);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new PaymentModalPage(driver);
    }

    @Step("Получение текста placeholder для варианта: {option}")
    public String getPlaceholderText(String option) {
        waitForPageLoad();
        closeCookieBanner();
        selectPaymentOption(option);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        By inputField = getInputFieldByOption(option);
        WebElement input = driver.findElement(inputField);
        return input.getAttribute("placeholder");
    }

    private By getInputFieldByOption(String option) {
        switch (option) {
            case "Услуги связи":
                return phoneInput;
            case "Домашний интернет":
                return internetInput;
            case "Рассрочка":
                return installmentInput;
            case "Задолженность":
                return debtInput;
            default:
                return phoneInput;
        }
    }

    @Step("Проверка placeholder'ов для всех вариантов оплаты")
    public Map<String, String> getAllPlaceholders() {
        Map<String, String> placeholders = new HashMap<>();
        String[] options = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String option : options) {
            try {
                selectPaymentOption(option);
                String placeholder = getPlaceholderText(option);
                placeholders.put(option, placeholder);
            } catch (Exception e) {
                placeholders.put(option, "Не найден");
            }
        }

        return placeholders;
    }
}