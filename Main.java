import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<String> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 0:
                    System.out.println("Программа завершена. До свидания!");
                    return;
                case 1:
                    addTask();
                    break;
                case 2:
                    showTasks();
                    break;
                case 3:
                    deleteTaskByNumber();
                    break;
                case 4:
                    deleteTaskByExactText();
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите пункт меню от 0 до 4.\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nВыберите операцию:");
        System.out.println("0. Выход из программы");
        System.out.println("1. Добавить дело");
        System.out.println("2. Показать дела");
        System.out.println("3. Удалить дело по номеру");
        System.out.println("4. Удалить дело по названию");
        System.out.print("Ваш выбор: ");
    }

    private static int getUserChoice() {
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            choice = -1;
        }
        return choice;
    }

    private static void addTask() {
        System.out.print("Введите название задачи: ");
        String task = scanner.nextLine().trim();

        if (task.isEmpty()) {
            System.out.println("Ошибка: название задачи не может быть пустым.");
            return;
        }

        if (tasks.contains(task)) {
            System.out.println("Такая задача уже существует!");
        } else {
            tasks.add(task);
            System.out.println("Добавлено!");
        }
        showTasks();
    }

    private static void showTasks() {
        System.out.println("\nВаш список дел:");
        if (tasks.isEmpty()) {
            System.out.println("Список дел пуст.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void deleteTaskByNumber() {
        if (tasks.isEmpty()) {
            System.out.println("Список дел пуст, нечего удалять.");
            return;
        }

        System.out.print("Введите номер для удаления: ");
        String input = scanner.nextLine().trim();

        try {
            int number = Integer.parseInt(input);
            if (number < 1 || number > tasks.size()) {
                System.out.printf("Ошибка: нет дела с номером %d. Доступны номера от 1 до %d.%n", number, tasks.size());
            } else {
                String removedTask = tasks.remove(number - 1);
                System.out.println("Удалено: \"" + removedTask + "\"");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректный номер (целое число).");
        }

        showTasks();
    }

    private static void deleteTaskByExactText() {
        if (tasks.isEmpty()) {
            System.out.println("Список дел пуст, нечего удалять.");
            return;
        }

        System.out.print("Введите точное название задачи для удаления: ");
        String taskText = scanner.nextLine().trim();

        if (taskText.isEmpty()) {
            System.out.println("Ошибка: название не может быть пустым.");
            return;
        }

        int index = tasks.indexOf(taskText);
        if (index == -1) {
            System.out.println("Задача \"" + taskText + "\" не найдена.");
        } else {
            String removedTask = tasks.remove(index);
            System.out.println("Удалено: \"" + removedTask + "\"");
        }

        showTasks();
    }
}