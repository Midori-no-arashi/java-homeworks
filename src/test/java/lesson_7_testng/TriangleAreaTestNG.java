package lesson_7;

import org.testng.annotations.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleAreaTestNG {

    private TriangleArea triangle;

    @BeforeMethod
    public void setUp() {
        triangle = new TriangleArea();
    }

    @Test
    public void testRightTriangle() {
        assertEquals(triangle.calculate(3, 4, 5), 6.0, 0.01,
                "Площадь треугольника 3-4-5 должна быть 6.0");
    }

    @Test
    public void testEquilateralTriangle() {
        assertEquals(triangle.calculate(5, 5, 5), 10.83, 0.01,
                "Площадь равностороннего треугольника со стороной 5 должна быть ~10.83");
    }

    @Test
    public void testIsoscelesTriangle() {
        assertEquals(triangle.calculate(5, 5, 6), 12.0, 0.01,
                "Площадь треугольника 5-5-6 должна быть 12.0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidTriangle() {
        triangle.calculate(1, 1, 3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroSide() {
        triangle.calculate(0, 4, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeSide() {
        triangle.calculate(-3, 4, 5);
    }

    @Test(dataProvider = "triangleData")
    public void testTriangleWithParams(double a, double b, double c, double expected) {
        assertEquals(triangle.calculate(a, b, c), expected, 0.01,
                "Площадь треугольника " + a + "-" + b + "-" + c + " должна быть " + expected);
    }

    @DataProvider(name = "triangleData")
    public Object[][] provideTriangleData() {
        return new Object[][] {
                {3, 4, 5, 6.0},
                {5, 5, 5, 10.83},
                {6, 8, 10, 24.0},
                {7, 8, 9, 26.83}
        };
    }
}