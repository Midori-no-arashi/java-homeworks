package lesson_7;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== ПРОГРАММА 1: ФАКТОРИАЛ ==========");
        Factorial factorial = new Factorial();
        factorial.printResult(5);
        factorial.printResult(0);
        factorial.printResult(-3);

        System.out.println("\n========== ПРОГРАММА 2: ПЛОЩАДЬ ТРЕУГОЛЬНИКА ==========");
        TriangleArea triangle = new TriangleArea();
        triangle.printResult(3, 4, 5);
        triangle.printResult(5, 5, 5);
        triangle.printResult(1, 1, 3);

        System.out.println("\n========== ПРОГРАММА 3: АРИФМЕТИЧЕСКИЕ ДЕЙСТВИЯ ==========");
        Arithmetic arithmetic = new Arithmetic();
        arithmetic.printResult(10, 5);
        arithmetic.printResult(7, 0);
        arithmetic.printResult(-3, 8);

        System.out.println("\n========== ПРОГРАММА 4: СРАВНЕНИЕ ЧИСЕЛ ==========");
        NumberComparator comparator = new NumberComparator();
        comparator.printResult(10, 5);
        comparator.printResult(3, 8);
        comparator.printResult(7, 7);
    }
}