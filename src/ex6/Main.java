package ex6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ViewTable viewTable1 = new ViewTable();
        ViewTable viewTable2 = new ViewTable();

        // Створення потоків для паралельної обробки
        Thread thread1 = new Thread(() -> {
            System.out.println("---------------");
            System.out.println("Середнє значення периметрів для першого: " + viewTable1.calculateAverage());
            System.out.println("Мінімальне число для першого: " + viewTable1.calculateMinimum());
            System.out.println("Максимум число для першого: " + viewTable1.calculateMaximum());
            System.out.println("Сума периметрів для першого: " + viewTable1.calculateSum());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Середнє значення периметрів для другого: " + viewTable2.calculateAverage());
            System.out.println("Мінімальне число для другого: " + viewTable2.calculateMinimum());
            System.out.println("Максимум число для другого: " + viewTable2.calculateMaximum());
            System.out.println("Сума периметрів для другого: " + viewTable2.calculateSum());

            System.out.println("---------------");
        });

        // Запуск потоків
        thread1.start();
        thread2.start();

        try {
            // Очікування завершення потоків
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean running = true;
        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Відобразити таблицю");
            System.out.println("2. Повторити введення");
            System.out.println("3. Скасувати останнє введення");
            System.out.println("4. Вийти");

            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewTable1.viewShow();
                    viewTable2.viewShow();
                    break;
                case 2:
                    viewTable1.execute();
                    break;
                case 3:
                    viewTable1.undo();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
            }
        }

        scanner.close();
    }
}
