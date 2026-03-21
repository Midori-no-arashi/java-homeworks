package lesson_7;

public class NumberComparator {

    public String compare(int a, int b) {
        if (a > b) {
            return a + " > " + b;
        } else if (a < b) {
            return a + " < " + b;
        } else {
            return a + " = " + b;
        }
    }

    public boolean isEqual(int a, int b) {
        return a == b;
    }

    public boolean isGreater(int a, int b) {
        return a > b;
    }

    public boolean isLess(int a, int b) {
        return a < b;
    }

    public void printResult(int a, int b) {
        System.out.println("Сравнение чисел " + a + " и " + b + ":");
        System.out.println("  " + compare(a, b));
        System.out.println("  Равны? " + isEqual(a, b));
        System.out.println("  Первое больше? " + isGreater(a, b));
        System.out.println("  Первое меньше? " + isLess(a, b));
        System.out.println();
    }
}