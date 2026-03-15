package lesson_5;

public class ArrayApp {
    public static void main(String[] args) {
        System.out.println("========== ЗАДАНИЕ 1-3: РАБОТА С МАССИВОМ 4x4 ==========\n");

        // Создаем правильный массив 4x4
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Создаем массив неправильного размера (3x4)
        String[][] wrongSizeArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };

        // Создаем массив с неправильными данными (буквы вместо чисел)
        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "привет", "12"},  // здесь "привет" вместо числа
                {"13", "14", "15", "16"}
        };

        // Тест 1: Правильный массив
        System.out.println("ТЕСТ 1: Правильный массив 4x4");
        try {
            int sum = processArray(correctArray);
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка в данных: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Тест 2: Массив неправильного размера
        System.out.println("ТЕСТ 2: Массив неправильного размера (3x4)");
        try {
            int sum = processArray(wrongSizeArray);
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка в данных: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Тест 3: Массив с неправильными данными
        System.out.println("ТЕСТ 3: Массив с неправильными данными");
        try {
            int sum = processArray(wrongDataArray);
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка в данных: " + e.getMessage());
        }

        System.out.println("\n========== ЗАДАНИЕ 4: ArrayIndexOutOfBoundsException ==========\n");

        // Демонстрация ArrayIndexOutOfBoundsException
        System.out.println("ТЕСТ 4: Выход за границы массива");
        try {
            generateArrayIndexOutOfBounds();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймали исключение: " + e);
            System.out.println("Текст ошибки: " + e.getMessage());
        }

        System.out.println("\n========== ПРОГРАММА ЗАВЕРШЕНА ==========");
    }

    // Метод для обработки массива (задания 1-3)
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

        // Суммируем элементы (задание 2)
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    // Пробуем преобразовать строку в число
                    int number = Integer.parseInt(array[i][j]);
                    sum = sum + number;
                    System.out.println("  [" + i + "][" + j + "] = " + array[i][j] + " -> OK");
                } catch (NumberFormatException e) {
                    // Если не получилось - бросаем наше исключение с деталями
                    throw new MyArrayDataException(
                            "В ячейке [" + i + "][" + j + "] лежит '" + array[i][j] + "', а должно быть число"
                    );
                }
            }
        }

        return sum;
    }

    // Метод для генерации ArrayIndexOutOfBoundsException (задание 4)
    public static void generateArrayIndexOutOfBounds() {
        // Создаем массив из 3 элементов
        int[] numbers = {10, 20, 30};

        System.out.println("Массив numbers имеет длину: " + numbers.length);
        System.out.println("Пробуем обратиться к элементу с индексом 5...");

        // Пытаемся обратиться к несуществующему элементу
        // Это вызовет ArrayIndexOutOfBoundsException
        int value = numbers[5];

        // Эта строка никогда не выполнится, потому что выше будет ошибка
        System.out.println("Значение: " + value);
    }
}

// ============== НАШИ СОБСТВЕННЫЕ ИСКЛЮЧЕНИЯ ==============

// Исключение для неправильного размера массива
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

// Исключение для неправильных данных в ячейке
class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}
