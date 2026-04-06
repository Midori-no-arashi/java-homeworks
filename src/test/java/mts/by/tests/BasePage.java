package mts.by.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Закрытие баннера куки")
    public void closeCookieBanner() {
        try {
            By cookieButton = By.xpath("//button[contains(@class, 'cookie')] | //div[contains(@class, 'cookie')]//button | //*[contains(text(), 'Принять')]");
            List<WebElement> buttons = driver.findElements(cookieButton);
            if (!buttons.isEmpty()) {
                buttons.get(0).click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            // Баннер не найден или уже закрыт
        }
    }

    @Step("Ожидание загрузки страницы")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Клик через JavaScript")
    public void clickByJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Скролл к элементу")
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Сделать скриншот: {name}")
    public void takeScreenshot(String name) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Allure attachment будет добавлен в тестовом классе
        } catch (Exception e) {
            System.out.println("Не удалось сделать скриншот: " + e.getMessage());
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}