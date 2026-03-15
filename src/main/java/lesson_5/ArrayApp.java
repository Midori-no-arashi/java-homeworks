package lesson_5;

public class ArrayApp {
    public static void main(String[] args) {
        System.out.println("========= РАБОТА С МАССИВОМ =========\n");

        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] wrongSizeArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };

        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "привет", "12"},
                {"13", "14", "15", "16"}
        };

        System.out.println("ТЕСТ 1: Правильный массив");
        try {
            int sum = processArray(correctArray);
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка в данных: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        System.out.println("ТЕСТ 2: Массив неправильного размера");
        try {
            int sum = processArray(wrongSizeArray);
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка в данных: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        System.out.println("ТЕСТ 3: Массив с неправильными данными");
        try {
            int sum = processArray(wrongDataArray);
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка в данных: " + e.getMessage());
        }

        System.out.println("\n========== ArrayIndexOutOfBoundsException ==========\n");

        System.out.println("ТЕСТ 4: Выход за границы массива");
        try {
            generateArrayIndexOutOfBounds();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймали исключение: " + e);
            System.out.println("Текст ошибки: " + e.getMessage());
        }

        System.out.println("\n========== ПРОГРАММА ЗАВЕРШЕНА ==========");
    }

    public static int processArray(String[][] array)
            throws MyArraySizeException, MyArrayDataException {

        // Проверка размера массива (задание 1)
        if (array.length != 4) {
            throw new MyArraySizeException("Массив должен быть 4x4, а у него " + array.length + " строк");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Массив должен быть 4x4. Строка " + i + " имеет " + array[i].length + " столбцов");
            }
        }

        System.out.println("Размер массива правильный: 4x4");

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int number = Integer.parseInt(array[i][j]);
                    sum = sum + number;
                    System.out.println("  [" + i + "][" + j + "] = " + array[i][j] + " -> OK");
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(
                            "В ячейке [" + i + "][" + j + "] лежит '" + array[i][j] + "', а должно быть число"
                    );
                }
            }
        }

        return sum;
    }

    public static void generateArrayIndexOutOfBounds() {
        int[] numbers = {10, 20, 30};

        System.out.println("Массив numbers имеет длину: " + numbers.length);
        System.out.println("Пробуем обратиться к элементу с индексом 5...");

        int value = numbers[5];

        System.out.println("Значение: " + value);
    }
}

class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}
