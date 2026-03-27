package lesson_6;

import java.util.*;

public class PhonebookApp {
    public static void main(String[] args) {
        System.out.println("========== ТЕЛЕФОННЫЙ СПРАВОЧНИК ==========\n");

        Phonebook phonebook = new Phonebook();

        System.out.println("--- ДОБАВЛЯЕМ ЗАПИСИ ---");

        phonebook.add("Иванов", "89991234567");
        phonebook.add("Петров", "89992345678");
        phonebook.add("Сидоров", "89993456789");
        phonebook.add("Иванов", "89994567890");  // второй телефон Иванова
        phonebook.add("Смирнов", "89995678901");
        phonebook.add("Петров", "89996789012");  // второй телефон Петрова
        phonebook.add("Кузнецов", "89997890123");
        phonebook.add("Иванов", "89998901234");  // третий телефон Иванова
        phonebook.add("Васильев", "89999012345");

        System.out.println("\nВсего записей добавлено: 9\n");

        System.out.println("--- ПОИСК ПО ФАМИЛИИ ---");

        searchAndPrint(phonebook, "Иванов");
        searchAndPrint(phonebook, "Петров");
        searchAndPrint(phonebook, "Сидоров");
        searchAndPrint(phonebook, "Смирнов");
        searchAndPrint(phonebook, "Кузнецов");
        searchAndPrint(phonebook, "Васильев");
        searchAndPrint(phonebook, "Соколов");  // этой фамилии нет

        System.out.println("\n========== ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ ==========\n");

        System.out.println("--- ТЕСТ: добавление пустой фамилии ---");
        phonebook.add("", "89990000000");

        System.out.println("\n--- ТЕСТ: добавление пустого номера ---");
        phonebook.add("Иванов", "");

        System.out.println("\n--- ТЕСТ: добавление существующего номера ---");
        phonebook.add("Иванов", "89991234567");  // уже есть такой номер

        System.out.println("\n--- ПОКАЗЫВАЕМ ВЕСЬ СПРАВОЧНИК ---");
        phonebook.printAll();
    }

    public static void searchAndPrint(Phonebook phonebook, String surname) {
        System.out.println("Ищем: " + surname);
        List<String> phones = phonebook.get(surname);

        if (phones.isEmpty()) {
            System.out.println("  → Фамилия " + surname + " не найдена в справочнике");
        } else {
            System.out.println("  → Найдено телефонов: " + phones.size());
            for (int i = 0; i < phones.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + phones.get(i));
            }
        }
        System.out.println();
    }
}

class Phonebook {
    private Map<String, List<String>> phonebook;

    public Phonebook() {
        phonebook = new HashMap<>();
        System.out.println("Справочник создан");
    }

    public void add(String surname, String phoneNumber) {
        if (surname == null || surname.trim().isEmpty()) {
            System.out.println("Ошибка: фамилия не может быть пустой");
            return;
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.out.println("Ошибка: номер телефона не может быть пустым");
            return;
        }

        surname = surname.trim();
        phoneNumber = phoneNumber.trim();

        List<String> phones = phonebook.get(surname);

        if (phones == null) {
            phones = new ArrayList<>();
            phones.add(phoneNumber);
            phonebook.put(surname, phones);
            System.out.println("Добавлена новая запись: " + surname + " - " + phoneNumber);
        }
        else {
            if (phones.contains(phoneNumber)) {
                System.out.println("Такой номер уже есть у " + surname);
            } else {
                phones.add(phoneNumber);
                System.out.println("Добавлен еще один телефон для " + surname + ": " + phoneNumber);
            }
        }
    }

    public List<String> get(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            System.out.println("Ошибка: фамилия не может быть пустой");
            return new ArrayList<>();
        }

        surname = surname.trim();

        List<String> phones = phonebook.get(surname);

        if (phones == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(phones);
    }

    public boolean containsSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            return false;
        }
        return phonebook.containsKey(surname.trim());
    }

    public Set<String> getAllSurnames() {
        return phonebook.keySet();
    }

    public void printAll() {
        System.out.println("\n--- ВЕСЬ ТЕЛЕФОННЫЙ СПРАВОЧНИК ---");

        if (phonebook.isEmpty()) {
            System.out.println("Справочник пуст");
            return;
        }

        List<String> surnames = new ArrayList<>(phonebook.keySet());
        Collections.sort(surnames);

        for (String surname : surnames) {
            List<String> phones = phonebook.get(surname);
            System.out.println(surname + ":");
            for (int i = 0; i < phones.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + phones.get(i));
            }
        }
    }

    public int size() {
        return phonebook.size();
    }

    public int totalPhones() {
        int count = 0;
        for (List<String> phones : phonebook.values()) {
            count = count + phones.size();
        }
        return count;
    }
}