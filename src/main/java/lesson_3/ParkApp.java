package lesson_3;

public class ParkApp {
    public static void main(String[] args) {
        System.out.println("================ ПАРК С АТТРАКЦИОНАМИ ================");

        Park park = new Park(
                "Центральный парк культуры и отдыха",
                "ул. Ленина, 15"
        );

        System.out.println("Создан парк: \"" + park.name + "\" по адресу: " + park.address);
        System.out.println("\nДобавляем аттракционы...\n");

        park.addAttraction(
                "Колесо обозрения",
                "10:00 - 22:00",
                350
        );

        park.addAttraction(
                "Американские горки",
                "11:00 - 21:00",
                500
        );

        park.addAttraction(
                "Комната страха",
                "12:00 - 20:00",
                250
        );

        park.addAttraction(
                "Автодром",
                "09:00 - 19:00",
                150
        );

        park.addAttraction(
                "Водные горки",
                "10:00 - 20:00",
                400
        );

        park.printAllAttractions();

        System.out.println("\n============================================================\n");
    }
}

class Park {
    String name;
    String address;
    Attraction[] attractions;

    // Конструктор парка
    Park(String name, String address) {
        this.name = name;
        this.address = address;
        this.attractions = new Attraction[0];
    }

    void addAttraction(String name, String workingHours, double price) {
        Attraction[] newArray = new Attraction[attractions.length + 1];

        for (int i = 0; i < attractions.length; i++) {
            newArray[i] = attractions[i];
        }

        newArray[attractions.length] = new Attraction(name, workingHours, price);

        attractions = newArray;

        System.out.println("  ✓ Добавлен аттракцион: \"" + name + "\"");
    }

    void printAllAttractions() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│              ПАРК \"" + name + "\"              │");
        System.out.println("│              Адрес: " + address + "               │");
        System.out.println("├─────────────────────────────────────────┤");

        if (attractions.length == 0) {
            System.out.println("│   В парке пока нет аттракционов           │");
        } else {
            System.out.println("│           СПИСОК АТТРАКЦИОНОВ:            │");
            System.out.println("├─────────────────────────────────────────┤");

            for (int i = 0; i < attractions.length; i++) {
                System.out.println("│ " + (i + 1) + ". " + attractions[i].name);
                System.out.println("│    Время: " + attractions[i].workingHours);
                System.out.println("│    Цена:  " + attractions[i].price + " руб.");

                if (i < attractions.length - 1) {
                    System.out.println("├─────────────────────────────────────────┤");
                }
            }
        }
        System.out.println("└─────────────────────────────────────────┘");
    }

    class Attraction {
        String name;
        String workingHours;
        double price;

        Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }
    }
}
