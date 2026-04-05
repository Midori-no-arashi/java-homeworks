package lesson_7;

public class Factorial {

    public int calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал отрицательного числа не существует");
        }

        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public void printResult(int n) {
        try {
            int result = calculate(n);
            System.out.println("Факториал числа " + n + " = " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}