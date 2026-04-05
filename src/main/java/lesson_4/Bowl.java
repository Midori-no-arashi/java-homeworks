package lesson_4;

public class Bowl {
    private int foodAmount;

    public Bowl(int foodAmount) {
        if (foodAmount < 0) {
            this.foodAmount = 0;
        } else {
            this.foodAmount = foodAmount;
        }
    }

    public boolean takeFood(int amount) {
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

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount = foodAmount + amount;
            System.out.println("Добавили " + amount + " еды");
        } else {
            System.out.println("Нельзя добавить отрицательную еду");
        }
    }

    public void printInfo() {
        System.out.println("В миске сейчас " + foodAmount + " еды");
    }
}