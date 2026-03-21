package lesson_7;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticTest {

    private Arithmetic arithmetic;

    @BeforeEach
    void setUp() {
        arithmetic = new Arithmetic();
    }

    @Test
    @DisplayName("Тест сложения")
    void testAdd() {
        assertEquals(15, arithmetic.add(10, 5), "10 + 5 должно быть 15");
        assertEquals(0, arithmetic.add(-3, 3), "-3 + 3 должно быть 0");
        assertEquals(-8, arithmetic.add(-5, -3), "-5 + (-3) должно быть -8");
    }

    @Test
    @DisplayName("Тест вычитания")
    void testSubtract() {
        assertEquals(5, arithmetic.subtract(10, 5), "10 - 5 должно быть 5");
        assertEquals(-6, arithmetic.subtract(-3, 3), "-3 - 3 должно быть -6");
        assertEquals(-2, arithmetic.subtract(-5, -3), "-5 - (-3) должно быть -2");
    }

    @Test
    @DisplayName("Тест умножения")
    void testMultiply() {
        assertEquals(50, arithmetic.multiply(10, 5), "10 * 5 должно быть 50");
        assertEquals(-9, arithmetic.multiply(-3, 3), "-3 * 3 должно быть -9");
        assertEquals(15, arithmetic.multiply(-5, -3), "-5 * (-3) должно быть 15");
        assertEquals(0, arithmetic.multiply(0, 5), "0 * 5 должно быть 0");
    }

    @Test
    @DisplayName("Тест деления")
    void testDivide() {
        assertEquals(2.0, arithmetic.divide(10, 5), 0.001, "10 / 5 должно быть 2.0");
        assertEquals(-1.0, arithmetic.divide(-3, 3), 0.001, "-3 / 3 должно быть -1.0");
        assertEquals(1.5, arithmetic.divide(3, 2), 0.001, "3 / 2 должно быть 1.5");
    }

    @Test
    @DisplayName("Тест деления на ноль")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class,
                () -> arithmetic.divide(10, 0),
                "Деление на ноль должно вызывать исключение");
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест сложения")
    @CsvSource({
            "1, 2, 3",
            "-1, -2, -3",
            "-5, 5, 0",
            "100, 200, 300"
    })
    void testAddWithParams(int a, int b, int expected) {
        assertEquals(expected, arithmetic.add(a, b), a + " + " + b + " должно быть " + expected);
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест умножения")
    @CsvSource({
            "2, 3, 6",
            "-2, 3, -6",
            "-2, -3, 6",
            "0, 5, 0"
    })
    void testMultiplyWithParams(int a, int b, int expected) {
        assertEquals(expected, arithmetic.multiply(a, b), a + " * " + b + " должно быть " + expected);
    }
}