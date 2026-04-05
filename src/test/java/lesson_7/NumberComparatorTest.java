package lesson_7;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    private NumberComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new NumberComparator();
    }

    @Test
    @DisplayName("Тест сравнения - первое больше")
    void testCompareFirstGreater() {
        assertEquals("10 > 5", comparator.compare(10, 5));
        assertTrue(comparator.isGreater(10, 5));
        assertFalse(comparator.isLess(10, 5));
        assertFalse(comparator.isEqual(10, 5));
    }

    @Test
    @DisplayName("Тест сравнения - первое меньше")
    void testCompareFirstLess() {
        assertEquals("3 < 8", comparator.compare(3, 8));
        assertFalse(comparator.isGreater(3, 8));
        assertTrue(comparator.isLess(3, 8));
        assertFalse(comparator.isEqual(3, 8));
    }

    @Test
    @DisplayName("Тест сравнения - равны")
    void testCompareEqual() {
        assertEquals("7 = 7", comparator.compare(7, 7));
        assertFalse(comparator.isGreater(7, 7));
        assertFalse(comparator.isLess(7, 7));
        assertTrue(comparator.isEqual(7, 7));
    }

    @Test
    @DisplayName("Тест с отрицательными числами")
    void testCompareNegative() {
        assertEquals("-5 > -10", comparator.compare(-5, -10));
        assertEquals("-10 < -5", comparator.compare(-10, -5));
        assertEquals("-7 = -7", comparator.compare(-7, -7));
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест сравнения")
    @CsvSource({
            "5, 3, true, false, false",
            "3, 5, false, true, false",
            "4, 4, false, false, true"
    })
    void testComparison(int a, int b, boolean expectedGreater, boolean expectedLess, boolean expectedEqual) {
        assertEquals(expectedGreater, comparator.isGreater(a, b), a + " > " + b + " должно быть " + expectedGreater);
        assertEquals(expectedLess, comparator.isLess(a, b), a + " < " + b + " должно быть " + expectedLess);
        assertEquals(expectedEqual, comparator.isEqual(a, b), a + " = " + b + " должно быть " + expectedEqual);
    }
}