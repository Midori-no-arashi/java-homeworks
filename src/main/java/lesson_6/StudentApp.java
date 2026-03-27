package lesson_6;

import java.util.*;

public class StudentApp {
    public static void main(String[] args) {
        System.out.println("========== РАБОТА СО СТУДЕНТАМИ ==========\n");

        List<Student> students = new ArrayList<>();

        students.add(new Student("Иванов Иван", "Группа А-101", 1, Arrays.asList(5, 4, 5, 4, 5)));  // средний 4.6
        students.add(new Student("Петров Петр", "Группа А-101", 1, Arrays.asList(3, 3, 2, 3, 2)));  // средний 2.6
        students.add(new Student("Сидорова Анна", "Группа Б-202", 2, Arrays.asList(5, 5, 5, 4, 5))); // средний 4.8
        students.add(new Student("Козлов Дмитрий", "Группа Б-202", 2, Arrays.asList(2, 3, 2, 3, 2))); // средний 2.4
        students.add(new Student("Морозова Елена", "Группа В-303", 3, Arrays.asList(3, 4, 3, 4, 3))); // средний 3.4
        students.add(new Student("Волков Андрей", "Группа В-303", 3, Arrays.asList(5, 5, 5, 5, 5))); // средний 5.0
        students.add(new Student("Зайцева Ольга", "Группа Г-404", 4, Arrays.asList(4, 4, 4, 4, 4))); // средний 4.0
        students.add(new Student("Соколов Игорь", "Группа Г-404", 4, Arrays.asList(3, 3, 3, 2, 3))); // средний 2.8

        System.out.println("--- ВСЕ СТУДЕНТЫ (ДО ОБРАБОТКИ) ---");
        printAllStudents(students);

        System.out.println("\n========================================\n");

        System.out.println("--- УДАЛЯЕМ СТУДЕНТОВ СО СРЕДНИМ БАЛЛОМ < 3 ---");
        removePoorStudents(students);
        System.out.println("Студенты после удаления:");
        printAllStudents(students);

        System.out.println("\n========================================\n");

        System.out.println("--- ПЕРЕВОДИМ СТУДЕНТОВ НА СЛЕДУЮЩИЙ КУРС ---");
        promoteStudents(students);
        System.out.println("Студенты после перевода:");
        printAllStudents(students);

        System.out.println("\n========================================\n");

        System.out.println("--- ПЕЧАТАЕМ СТУДЕНТОВ ПО КУРСАМ ---");
        printStudents(students, 2);
        printStudents(students, 3);
        printStudents(students, 4);
        printStudents(students, 5);

        System.out.println("\n========================================\n");

        System.out.println("--- ИТОГОВАЯ ИНФОРМАЦИЯ ---");
        System.out.println("Всего студентов в коллекции: " + students.size());
        for (int course = 1; course <= 5; course++) {
            int count = countStudentsOnCourse(students, course);
            if (count > 0) {
                System.out.println("На " + course + " курсе: " + count + " студентов");
            }
        }
    }

    public static void removePoorStudents(List<Student> students) {
        for (int i = students.size() - 1; i >= 0; i--) {
            Student s = students.get(i);
            if (s.getAverageGrade() < 3.0) {
                System.out.println("Удаляем: " + s.getName() + " (средний балл " + s.getAverageGrade() + ")");
                students.remove(i);
            }
        }
    }

    public static void promoteStudents(List<Student> students) {
        for (Student s : students) {
            if (s.getAverageGrade() >= 3.0) {
                int oldCourse = s.getCourse();
                s.setCourse(oldCourse + 1);
                System.out.println(s.getName() + " переведен с " + oldCourse + " на " + s.getCourse() + " курс");
            } else {
                System.out.println(s.getName() + " не переведен (средний балл " + s.getAverageGrade() + ")");
            }
        }
    }

    public static void printStudents(List<Student> students, int course) {
        System.out.println("\nСтуденты на " + course + " курсе:");

        boolean found = false;
        for (Student s : students) {
            if (s.getCourse() == course) {
                System.out.println("  - " + s.getName() + " (группа " + s.getGroup() + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("  Студентов на " + course + " курсе нет");
        }
    }

    public static void printAllStudents(List<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public static int countStudentsOnCourse(List<Student> students, int course) {
        int count = 0;
        for (Student s : students) {
            if (s.getCourse() == course) {
                count++;
            }
        }
        return count;
    }
}

class Student {
    private String name;        // имя
    private String group;       // группа
    private int course;         // курс
    private List<Integer> grades;  // оценки по предметам

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new ArrayList<>(grades);  // копируем, чтобы не менять оригинал
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public double getAverageGrade() {
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (int grade : grades) {
            sum = sum + grade;
        }
        return (double) sum / grades.size();
    }

    @Override
    public String toString() {
        return String.format("%s (группа %s, курс %d) - оценки: %s, средний балл: %.2f",
                name, group, course, grades, getAverageGrade());
    }
}
