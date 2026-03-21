package lesson_7;

import org.testng.annotations.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberComparatorTestNG {

    private NumberComparator comparator;

    @BeforeMethod
    public void setUp() {
        comparator = new NumberComparator();
    }

    @Test
    public void testCompareFirstGreater() {
        assertEquals(comparator.compare(10, 5), "10 > 5");
        assertTrue(comparator.isGreater(10, 5));
        assertFalse(comparator.isLess(10, 5));
        assertFalse(comparator.isEqual(10, 5));
    }

    @Test
    public void testCompareFirstLess() {
        assertEquals(comparator.compare(3, 8), "3 < 8");
        assertFalse(comparator.isGreater(3, 8));
        assertTrue(comparator.isLess(3, 8));
        assertFalse(comparator.isEqual(3, 8));
    }

    @Test
    public void testCompareEqual() {
        assertEquals(comparator.compare(7, 7), "7 = 7");
        assertFalse(comparator.isGreater(7, 7));
        assertFalse(comparator.isLess(7, 7));
        assertTrue(comparator.isEqual(7, 7));
    }

    @Test
    public void testCompareNegative() {
        assertEquals(comparator.compare(-5, -10), "-5 > -10");
        assertEquals(comparator.compare(-10, -5), "-10 < -5");
        assertEquals(comparator.compare(-7, -7), "-7 = -7");
    }

    @Test(dataProvider = "comparisonData")
    public void testComparison(int a, int b, boolean expectedGreater, boolean expectedLess, boolean expectedEqual) {
        assertEquals(comparator.isGreater(a, b), expectedGreater, a + " > " + b);
        assertEquals(comparator.isLess(a, b), expectedLess, a + " < " + b);
        assertEquals(comparator.isEqual(a, b), expectedEqual, a + " = " + b);
    }

    @DataProvider(name = "comparisonData")
    public Object[][] provideComparisonData() {
        return new Object[][] {
                {5, 3, true, false, false},
                {3, 5, false, true, false},
                {4, 4, false, false, true}
        };
    }
}