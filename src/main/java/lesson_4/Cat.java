package lesson_4;

public class Cat extends Animal {
    public static int catCount = 0;
    private boolean isFull;  // сытый или нет

    public Cat(String name) {
        super(name, 200, 0);
        this.isFull = false;  // сначала голодный
        catCount++;
    }

    public void eat(Bowl bowl, int amount) {
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

    public boolean isFull() {
        return isFull;
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать, коты боятся воды");
    }
}