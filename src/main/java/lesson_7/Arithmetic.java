package lesson_7;

public class Arithmetic {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно");
        }
        return (double) a / b;
    }

    public void printResult(int a, int b) {
        System.out.println("Числа: " + a + " и " + b);
        System.out.println("Сложение: " + add(a, b));
        System.out.println("Вычитание: " + subtract(a, b));
        System.out.println("Умножение: " + multiply(a, b));

        try {
            System.out.println("Деление: " + divide(a, b));
        } catch (IllegalArgumentException e) {
            System.out.println("Деление: " + e.getMessage());
        }
        System.out.println();
    }
}