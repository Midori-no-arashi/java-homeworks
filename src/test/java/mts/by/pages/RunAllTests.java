package mts.by.tests;

import org.junit.jupiter.api.Test;

public class RunAllTests {

    @Test
    public void runAllTests() {
        MtsPaymentTests tests = new MtsPaymentTests();

        System.out.println("========================================");
        System.out.println("Запуск всех тестов MTS Payment");
        System.out.println("========================================\n");

        try {
            tests.setUp();
            tests.testBlockTitle();
            System.out.println("✓ Тест 1 пройден\n");
        } catch (Exception e) {
            System.err.println("✗ Тест 1 не пройден: " + e.getMessage());
        }

        try {
            tests.testPaymentOptions();
            System.out.println("✓ Тест 2 пройден\n");
        } catch (Exception e) {
            System.err.println("✗ Тест 2 не пройден: " + e.getMessage());
        }

        try {
            tests.testPlaceholdersForAllOptions();
            System.out.println("✓ Тест 3 пройден\n");
        } catch (Exception e) {
            System.err.println("✗ Тест 3 не пройден: " + e.getMessage());
        }

        try {
            tests.testPhoneServicePayment();
            System.out.println("✓ Тест 4 пройден\n");
        } catch (Exception e) {
            System.err.println("✗ Тест 4 не пройден: " + e.getMessage());
        }

        try {
            tests.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("========================================");
        System.out.println("Все тесты завершены!");
        System.out.println("========================================");
    }
}