package lesson_7;

public class TriangleArea {

    public double calculate(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Стороны треугольника должны быть положительными числами");
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Треугольник с такими сторонами не существует");
        }

        double p = (a + b + c) / 2;
        double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        return Math.round(area * 100.0) / 100.0;
    }

    public void printResult(double a, double b, double c) {
        try {
            double area = calculate(a, b, c);
            System.out.println("Площадь треугольника со сторонами " + a + ", " + b + ", " + c + " = " + area);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}