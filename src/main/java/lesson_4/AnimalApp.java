package lesson_4;

public class AnimalApp {
    public static void main(String[] args) {
        System.out.println("========== ЖИВОТНЫЕ, БЕГ И ПЛАВАНИЕ ==========\n");

        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");

        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Мурзик");
        Cat cat3 = new Cat("Рыжик");

        System.out.println("--- СОБАКИ ---");
        dog1.run(150);
        dog1.run(600);
        dog1.swim(5);
        dog1.swim(15);

        System.out.println("\n--- КОТЫ ---");
        cat1.run(100);
        cat1.run(250);
        cat1.swim(10);

        System.out.println("\n--- ПОДСЧЕТ ---");
        System.out.println("Всего животных: " + Animal.animalCount);
        System.out.println("Из них собак: " + Dog.dogCount);
        System.out.println("Из них котов: " + Cat.catCount);

        System.out.println("\n========== КОТЫ И МИСКА С ЕДОЙ ==========\n");

        Bowl bowl = new Bowl(30);

        Cat[] cats = {
                new Cat("Васька"),
                new Cat("Тимофей"),
                new Cat("Пушок"),
                new Cat("Снежок"),
                new Cat("Мурка")
        };

        System.out.println("Начало:");
        bowl.printInfo();
        System.out.println("Все коты голодны\n");

        System.out.println("Кормим котов по 10 еды:");
        for (int i = 0; i < cats.length; i++) {
            System.out.print((i + 1) + ". ");
            cats[i].eat(bowl, 10);
        }

        System.out.println("\nПосле кормления:");
        bowl.printInfo();
        System.out.println("Состояние котов:");
        for (int i = 0; i < cats.length; i++) {
            String status = cats[i].isFull() ? "СЫТ" : "ГОЛОДЕН";
            System.out.println("   " + cats[i].name + " - " + status);
        }

        System.out.println("\nДобавляем еду в миску:");
        bowl.addFood(25);
        bowl.printInfo();

        System.out.println("\nКормим голодных котов по 10 еды:");
        for (int i = 0; i < cats.length; i++) {
            if (!cats[i].isFull()) {
                System.out.print(cats[i].name + " - ");
                cats[i].eat(bowl, 10);
            }
        }

        System.out.println("\n--- ФИНАЛ ---");
        bowl.printInfo();
        System.out.println("Состояние котов:");
        for (int i = 0; i < cats.length; i++) {
            String status = cats[i].isFull() ? "СЫТ" : "ГОЛОДЕН";
            System.out.println("   " + cats[i].name + " - " + status);
        }
    }
}