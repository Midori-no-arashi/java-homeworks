package lesson_7;

import org.testng.annotations.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialTestNG {

    private Factorial factorial;

    @BeforeMethod
    public void setUp() {
        factorial = new Factorial();
        System.out.println("Создан новый экземпляр Factorial для теста");
    }

    @AfterMethod
    public void tearDown() {
        factorial = null;
        System.out.println("Тест завершен");
    }

    @Test
    public void testFactorialOfFive() {
        assertEquals(factorial.calculate(5), 120, "Факториал 5 должен быть 120");
    }

    @Test
    public void testFactorialOfZero() {
        assertEquals(factorial.calculate(0), 1, "Факториал 0 должен быть 1");
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(factorial.calculate(1), 1, "Факториал 1 должен быть 1");
    }

    @Test
    public void testFactorialOfTen() {
        assertEquals(factorial.calculate(10), 3628800, "Факториал 10 должен быть 3628800");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialOfNegative() {
        factorial.calculate(-5);
    }

    @Test(dataProvider = "factorialData")
    public void testFactorialWithParams(int input, int expected) {
        assertEquals(factorial.calculate(input), expected,
                "Факториал " + input + " должен быть " + expected);
    }

    @DataProvider(name = "factorialData")
    public Object[][] provideFactorialData() {
        return new Object[][] {
                {0, 1},
                {1, 1},
                {2, 2},
                {3, 6},
                {4, 24},
                {5, 120},
                {6, 720}
        };
    }

    @Test(timeOut = 1000)
    public void testFactorialPerformance() {
        factorial.calculate(20);
    }
}