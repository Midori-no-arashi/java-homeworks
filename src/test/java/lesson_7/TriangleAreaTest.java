package lesson_7;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    private TriangleArea triangle;

    @BeforeEach
    void setUp() {
        triangle = new TriangleArea();
    }

    @Test
    @DisplayName("Тест прямоугольного треугольника 3-4-5")
    void testRightTriangle() {
        assertEquals(6.0, triangle.calculate(3, 4, 5), 0.01,
                "Площадь треугольника 3-4-5 должна быть 6.0");
    }

    @Test
    @DisplayName("Тест равностороннего треугольника")
    void testEquilateralTriangle() {
        assertEquals(10.83, triangle.calculate(5, 5, 5), 0.01,
                "Площадь равностороннего треугольника со стороной 5 должна быть ~10.83");
    }

    @Test
    @DisplayName("Тест равнобедренного треугольника")
    void testIsoscelesTriangle() {
        assertEquals(12.0, triangle.calculate(5, 5, 6), 0.01,
                "Площадь треугольника 5-5-6 должна быть 12.0");
    }

    @Test
    @DisplayName("Тест несуществующего треугольника")
    void testInvalidTriangle() {
        assertThrows(IllegalArgumentException.class,
                () -> triangle.calculate(1, 1, 3),
                "Треугольник 1-1-3 не должен существовать");
    }

    @Test
    @DisplayName("Тест с нулевой стороной")
    void testZeroSide() {
        assertThrows(IllegalArgumentException.class,
                () -> triangle.calculate(0, 4, 5),
                "Нулевая сторона должна вызывать исключение");
    }

    @Test
    @DisplayName("Тест с отрицательной стороной")
    void testNegativeSide() {
        assertThrows(IllegalArgumentException.class,
                () -> triangle.calculate(-3, 4, 5),
                "Отрицательная сторона должна вызывать исключение");
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест треугольников")
    @CsvSource({
            "3, 4, 5, 6.0",
            "5, 5, 5, 10.83",
            "6, 8, 10, 24.0",
            "7, 8, 9, 26.83"
    })
    void testTriangleWithParams(double a, double b, double c, double expected) {
        assertEquals(expected, triangle.calculate(a, b, c), 0.01,
                "Площадь треугольника " + a + "-" + b + "-" + c + " должна быть " + expected);
    }
}