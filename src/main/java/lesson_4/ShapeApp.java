package lesson_4;

public class ShapeApp {
    public static void main(String[] args) {
        System.out.println("========= ГЕОМЕТРИЧЕСКИЕ ФИГУРЫ =========\n");

        Circle circle = new Circle(5, "Красный", "Черный");
        Rectangle rectangle = new Rectangle(4, 6, "Синий", "Белый");
        Triangle triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        System.out.println("--- КРУГ ---");
        circle.printCircleInfo();

        System.out.println("\n--- ПРЯМОУГОЛЬНИК ---");
        rectangle.printRectangleInfo();

        System.out.println("\n--- ТРЕУГОЛЬНИК ---");
        triangle.printTriangleInfo();

        System.out.println("\n--- ДОПОЛНИТЕЛЬНО ---");

        Circle circle2 = new Circle(2.5, "Розовый", "Фиолетовый");
        circle2.printCircleInfo();

        System.out.println();

        Rectangle square = new Rectangle(5, 5, "Голубой", "Оранжевый");
        square.printRectangleInfo();
    }
}

interface Figure {
    double getPerimeter();
    double getArea();
    String getFillColor();
    String getBorderColor();
}

class Circle implements Figure {
    double radius;
    String fillColor;
    String borderColor;

    Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public double getPerimeter() {
        return 2 * 3.14 * radius;
    }

    public double getArea() {
        return 3.14 * radius * radius;
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    void printCircleInfo() {
        System.out.println("Радиус: " + radius);
        System.out.println("Длина окружности: " + getPerimeter());
        System.out.println("Площадь круга: " + getArea());
        System.out.println("Цвет заливки: " + fillColor);
        System.out.println("Цвет границы: " + borderColor);
    }
}

class Rectangle implements Figure {
    double width;
    double height;
    String fillColor;
    String borderColor;

    Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public double getArea() {
        return width * height;
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    void printRectangleInfo() {
        System.out.println("Ширина: " + width);
        System.out.println("Высота: " + height);
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет заливки: " + fillColor);
        System.out.println("Цвет границы: " + borderColor);
    }
}

class Triangle implements Figure {
    double sideA;
    double sideB;
    double sideC;
    String fillColor;
    String borderColor;

    Triangle(double sideA, double sideB, double sideC, String fillColor, String borderColor) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    void printTriangleInfo() {
        System.out.println("Сторона A: " + sideA);
        System.out.println("Сторона B: " + sideB);
        System.out.println("Сторона C: " + sideC);
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет заливки: " + fillColor);
        System.out.println("Цвет границы: " + borderColor);
    }
}