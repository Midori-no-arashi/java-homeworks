package lesson_7;

import org.testng.annotations.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArithmeticTestNG {

    private Arithmetic arithmetic;

    @BeforeMethod
    public void setUp() {
        arithmetic = new Arithmetic();
    }

    @Test
    public void testAdd() {
        assertEquals(arithmetic.add(10, 5), 15, "10 + 5 должно быть 15");
        assertEquals(arithmetic.add(-3, 3), 0, "-3 + 3 должно быть 0");
        assertEquals(arithmetic.add(-5, -3), -8, "-5 + (-3) должно быть -8");
    }

    @Test
    public void testSubtract() {
        assertEquals(arithmetic.subtract(10, 5), 5, "10 - 5 должно быть 5");
        assertEquals(arithmetic.subtract(-3, 3), -6, "-3 - 3 должно быть -6");
        assertEquals(arithmetic.subtract(-5, -3), -2, "-5 - (-3) должно быть -2");
    }

    @Test
    public void testMultiply() {
        assertEquals(arithmetic.multiply(10, 5), 50, "10 * 5 должно быть 50");
        assertEquals(arithmetic.multiply(-3, 3), -9, "-3 * 3 должно быть -9");
        assertEquals(arithmetic.multiply(-5, -3), 15, "-5 * (-3) должно быть 15");
        assertEquals(arithmetic.multiply(0, 5), 0, "0 * 5 должно быть 0");
    }

    @Test
    public void testDivide() {
        assertEquals(arithmetic.divide(10, 5), 2.0, 0.001, "10 / 5 должно быть 2.0");
        assertEquals(arithmetic.divide(-3, 3), -1.0, 0.001, "-3 / 3 должно быть -1.0");
        assertEquals(arithmetic.divide(3, 2), 1.5, 0.001, "3 / 2 должно быть 1.5");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDivideByZero() {
        arithmetic.divide(10, 0);
    }

    @Test(dataProvider = "addData")
    public void testAddWithParams(int a, int b, int expected) {
        assertEquals(arithmetic.add(a, b), expected, a + " + " + b + " должно быть " + expected);
    }

    @DataProvider(name = "addData")
    public Object[][] provideAddData() {
        return new Object[][] {
                {1, 2, 3},
                {-1, -2, -3},
                {-5, 5, 0},
                {100, 200, 300}
        };
    }

    @Test(dataProvider = "multiplyData")
    public void testMultiplyWithParams(int a, int b, int expected) {
        assertEquals(arithmetic.multiply(a, b), expected, a + " * " + b + " должно быть " + expected);
    }

    @DataProvider(name = "multiplyData")
    public Object[][] provideMultiplyData() {
        return new Object[][] {
                {2, 3, 6},
                {-2, 3, -6},
                {-2, -3, 6},
                {0, 5, 0}
        };
    }
}