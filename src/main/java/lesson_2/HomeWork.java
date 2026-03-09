package lesson_2;

public class HomeWork {

    public static void main(String[] args) {
        System.out.println("1. Задание: printThreeWords()");
        printThreeWords();
        System.out.println("\n-------------------\n");

        System.out.println("2. Задание: checkSumSign()");
        checkSumSign();
        System.out.println("\n-------------------\n");

        System.out.println("3. Задание: printColor()");
        printColor();
        System.out.println("\n-------------------\n");

        System.out.println("4. Задание: compareNumbers()");
        compareNumbers();
        System.out.println("\n-------------------\n");

        System.out.println("5. Задание: Проверка суммы в пределах 10-20");
        System.out.println("Сумма 5 и 5 = " + isSumInRange(5, 5) + " (ожидаем true, т.к. 10)");
        System.out.println("Сумма 10 и 10 = " + isSumInRange(10, 10) + " (ожидаем true, т.к. 20)");
        System.out.println("Сумма 15 и 10 = " + isSumInRange(15, 10) + " (ожидаем false, т.к. 25)");
        System.out.println("\n-------------------\n");

        System.out.println("6. Задание: Определение знака числа (с печатью)");
        printSign(5);
        printSign(-3);
        printSign(0);
        System.out.println("\n-------------------\n");

        System.out.println("7. Задание: Проверка на отрицательность (возврат boolean)");
        System.out.println("Число -5 отрицательное? " + isNegative(-5));
        System.out.println("Число 3 отрицательное? " + isNegative(3));
        System.out.println("Число 0 отрицательное? " + isNegative(0));
        System.out.println("\n-------------------\n");

        System.out.println("8. Задание: Печать строки N раз");
        printStringMultipleTimes("Hello, Java!", 3);
        System.out.println("\n-------------------\n");

        System.out.println("9. Задание: Проверка года на високосность");
        System.out.println("Год 2000 високосный? " + isLeapYear(2000)); // true (делится на 400)
        System.out.println("Год 2020 високосный? " + isLeapYear(2020)); // true (делится на 4, но не на 100)
        System.out.println("Год 1900 високосный? " + isLeapYear(1900)); // false (делится на 100, но не на 400)
        System.out.println("Год 2023 високосный? " + isLeapYear(2023)); // false
        System.out.println("\n-------------------\n");

        System.out.println("10. Задание: Замена 0 на 1 и 1 на 0");
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.print("Исходный массив: ");
        printArray(binaryArray);
        invertArray(binaryArray);
        System.out.print("Инвертированный: ");
        printArray(binaryArray);
        System.out.println("\n-------------------\n");

        System.out.println("11. Задание: Заполнение массива от 1 до 100");
        int[] hundredArray = createArrayHundred();
        System.out.print("Первые 10 элементов: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(hundredArray[i] + " ");
        }
        System.out.println("...");
        System.out.println("\n-------------------\n");

        System.out.println("12. Задание: Умножение чисел меньше 6 на 2");
        int[] taskArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.print("Исходный массив: ");
        printArray(taskArray);
        multiplyLessThanSix(taskArray);
        System.out.print("Результат:       ");
        printArray(taskArray);
        System.out.println("\n-------------------\n");

        System.out.println("13. Задание: Заполнение диагоналей единицами");
        int[][] squareArray = new int[5][5];
        fillDiagonals(squareArray);
        System.out.println("Квадратный массив 5x5 с диагоналями:");
        print2DArray(squareArray);
        System.out.println("\n-------------------\n");

        System.out.println("14. Задание: Создание массива с начальным значением");
        int[] newArray = createArray(7, 42);
        System.out.print("Массив длиной 7, заполненный числом 42: ");
        printArray(newArray);
    }

    // 1. Печать трех слов в столбец
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Проверка знака суммы двух переменных
    public static void checkSumSign() {
        int a = 5;
        int b = -3;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Определение цвета по числовому значению
    public static void printColor() {
        int value = 75; // Можно изменить для проверки
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Сравнение двух чисел
    public static void compareNumbers() {
        int a = 7;
        int b = 10;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Проверка, лежит ли сумма в пределах от 10 до 20 (включительно)
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Печать, положительное ли число (0 считается положительным)
    public static void printSign(int number) {
        if (number >= 0) {
            System.out.println("Число " + number + " положительное");
        } else {
            System.out.println("Число " + number + " отрицательное");
        }
    }

    // 7. Проверка, является ли число отрицательным (0 считается положительным)
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Печать строки указанное количество раз
    public static void printStringMultipleTimes(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    // 9. Определение високосного года
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    // 10. Инвертирование массива (замена 0 на 1, 1 на 0)
    public static void invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
            // Более короткая запись: arr[i] = (arr[i] == 0) ? 1 : 0;
        }
    }

    // 11. Создание массива длиной 100 и заполнение числами от 1 до 100
    public static int[] createArrayHundred() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    // 12. Умножение чисел меньше 6 на 2
    public static void multiplyLessThanSix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2; // arr[i] = arr[i] * 2;
            }
        }
    }

    // 13. Заполнение диагоналей квадратного массива единицами
    public static void fillDiagonals(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Главная диагональ [i][i]
            arr[i][i] = 1;
            // Побочная диагональ [i][arr.length - 1 - i]
            arr[i][arr.length - 1 - i] = 1;
        }
    }

    // 14. Создание массива заданной длины и заполнение его указанным значением
    public static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    // Вспомогательный метод для красивой печати одномерного массива
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // Вспомогательный метод для печати двумерного массива
    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}