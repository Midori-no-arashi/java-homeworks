package lesson_4;

public class Dog extends Animal {
    public static int dogCount = 0;

    public Dog(String name) {
        super(name, 500, 10);
        dogCount++;
    }

    @Override
    public void swim(int distance) {
        if (distance <= swimLimit) {
            System.out.println(name + " проплыл " + distance + " м. и сказал гав-гав!");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м., устанет");
        }
    }
}