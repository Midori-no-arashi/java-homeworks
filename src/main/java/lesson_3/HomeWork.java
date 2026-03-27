package lesson_3;

public class HomeWork {
    public static void main(String[] args) {
        System.out.println("================ Товары ================");

        Product[] products = new Product[5];

        products[0] = new Product(
                "Samsung S25 Ultra",    // название
                "01.02.2025",           // дата производства
                "Samsung Corp.",        // производитель
                "Корея",                // страна
                5599.99,                // цена
                true                    // забронирован (true - да)
        );

        products[1] = new Product(
                "iPhone 15 Pro",
                "15.01.2025",
                "Apple Inc.",
                "США",
                8999.00,
                false                   // false - свободен
        );

        products[2] = new Product(
                "Xiaomi Mi 14",
                "10.03.2025",
                "Xiaomi Corporation",
                "Китай",
                4599.50,
                true
        );

        products[3] = new Product(
                "Ноутбук Asus ROG",
                "05.12.2024",
                "AsusTek",
                "Тайвань",
                12999.00,
                false
        );

        products[4] = new Product(
                "Наушники Sony WH-1000XM5",
                "20.02.2025",
                "Sony Corporation",
                "Япония",
                2999.90,
                true
        );

        System.out.println("\nСПИСОК ВСЕХ ТОВАРОВ:\n");

        for (int i = 0; i < products.length; i++) {
            System.out.println("--- Товар №" + (i + 1) + " ---");
            products[i].printInfo();
            System.out.println();
        }

        System.out.println("================================================");
    }
}
class Product {
    String name;              // название
    String productionDate;    // дата производства
    String manufacturer;      // производитель
    String country;           // страна происхождения
    double price;             // цена
    boolean isReserved;       // забронирован или нет

    Product(String name, String productionDate, String manufacturer,
            String country, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isReserved = isReserved;
    }

    void printInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + country);
        System.out.println("Цена: " + price + " руб.");

        String status;
        if (isReserved) {
            status = "ЗАБРОНИРОВАН";
        } else {
            status = "СВОБОДЕН";
        }
        System.out.println("Статус: " + status);
    }
}
