package lesson_4;

public class AnimalApp {
    public static void main(String[] args) {
        System.out.println("========= ЖИВОТНЫЕ =========\n");

        // Создаем собак
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");

        // Создаем котов
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
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Из них собак: " + Dog.getDogCount());
        System.out.println("Из них котов: " + Cat.getCatCount());

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
            System.out.println("   " + cats[i].getName() + " - " + status);
        }

        System.out.println("\nДобавляем еду в миску:");
        bowl.addFood(25);
        bowl.printInfo();

        System.out.println("\nКормим голодных котов по 10 еды:");
        for (int i = 0; i < cats.length; i++) {
            if (!cats[i].isFull()) {
                System.out.print(cats[i].getName() + " - ");
                cats[i].eat(bowl, 10);
            }
        }

        System.out.println("\n--- ФИНАЛ ---");
        bowl.printInfo();
        System.out.println("Состояние котов:");
        for (int i = 0; i < cats.length; i++) {
            String status = cats[i].isFull() ? "СЫТ" : "ГОЛОДЕН";
            System.out.println("   " + cats[i].getName() + " - " + status);
        }
    }
}

class Animal {
    String name;
    int runLimit;
    int swimLimit;

    static int animalCount = 0;

    Animal(String name, int runLimit, int swimLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        animalCount++;
    }

    void run(int distance) {
        if (distance <= runLimit) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м., максимум " + runLimit + " м.");
        }
    }

    void swim(int distance) {
        if (swimLimit == 0) {
            System.out.println(name + " не умеет плавать");
        } else if (distance <= swimLimit) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м., максимум " + swimLimit + " м.");
        }
    }

    String getName() {
        return name;
    }

    static int getAnimalCount() {
        return animalCount;
    }
}

class Dog extends Animal {
    static int dogCount = 0;

    Dog(String name) {
        super(name, 500, 10);
        dogCount++;
    }

    void swim(int distance) {
        if (distance <= swimLimit) {
            System.out.println(name + " проплыл " + distance + " м. и сказал гав-гав!");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м., устанет");
        }
    }

    static int getDogCount() {
        return dogCount;
    }
}

class Cat extends Animal {
    static int catCount = 0;
    boolean isFull;  // сытый или нет

    Cat(String name) {
        super(name, 200, 0);
        isFull = false;  // сначала голодный
        catCount++;
    }

    void eat(Bowl bowl, int amount) {
        if (isFull) {
            System.out.println(name + " уже сыт");
            return;
        }

        if (bowl.takeFood(amount)) {
            isFull = true;
            System.out.println(name + " покушал " + amount + " еды и теперь сыт");
        } else {
            System.out.println(name + " не стал есть, мало еды");
        }
    }

    boolean isFull() {
        return isFull;
    }

    void swim(int distance) {
        System.out.println(name + " не умеет плавать, коты боятся воды");
    }

    static int getCatCount() {
        return catCount;
    }
}

class Bowl {
    int foodAmount;

    Bowl(int foodAmount) {
        if (foodAmount < 0) {
            this.foodAmount = 0;
        } else {
            this.foodAmount = foodAmount;
        }
    }

    boolean takeFood(int amount) {
        if (amount <= 0) {
            return false;
        }

        if (foodAmount >= amount) {
            foodAmount = foodAmount - amount;
            return true;
        } else {
            return false;
        }
    }

    void addFood(int amount) {
        if (amount > 0) {
            foodAmount = foodAmount + amount;
            System.out.println("Добавили " + amount + " еды");
        } else {
            System.out.println("Нельзя добавить отрицательную еду");
        }
    }

    void printInfo() {
        System.out.println("В миске сейчас " + foodAmount + " еды");
    }
}