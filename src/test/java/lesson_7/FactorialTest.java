package lesson_7;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    private Factorial factorial;

    @BeforeEach
    void setUp() {
        factorial = new Factorial();
        System.out.println("Создан новый экземпляр Factorial для теста");
    }

    @AfterEach
    void tearDown() {
        factorial = null;
        System.out.println("Тест завершен");
    }

    @Test
    @DisplayName("Тест факториала 5")
    void testFactorialOfFive() {
        assertEquals(120, factorial.calculate(5), "Факториал 5 должен быть 120");
    }

    @Test
    @DisplayName("Тест факториала 0")
    void testFactorialOfZero() {
        assertEquals(1, factorial.calculate(0), "Факториал 0 должен быть 1");
    }

    @Test
    @DisplayName("Тест факториала 1")
    void testFactorialOfOne() {
        assertEquals(1, factorial.calculate(1), "Факториал 1 должен быть 1");
    }

    @Test
    @DisplayName("Тест факториала 10")
    void testFactorialOfTen() {
        assertEquals(3628800, factorial.calculate(10), "Факториал 10 должен быть 3628800");
    }

    @Test
    @DisplayName("Тест отрицательного числа - должно выбросить исключение")
    void testFactorialOfNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> factorial.calculate(-5),
                "Отрицательное число должно вызывать исключение");
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест факториала")
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120",
            "6, 720"
    })
    void testFactorialWithParams(int input, int expected) {
        assertEquals(expected, factorial.calculate(input),
                "Факториал " + input + " должен быть " + expected);
    }

    @Test
    @DisplayName("Тест времени выполнения")
    @Timeout(1)
    void testFactorialPerformance() {
        assertDoesNotThrow(() -> factorial.calculate(20));
    }
}