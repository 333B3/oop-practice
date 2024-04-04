package ex5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ViewTable viewTable = new ViewTable();

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
                    viewTable.viewShow();
                    break;
                case 2:
                    viewTable.execute();
                    break;
                case 3:
                    viewTable.undo();
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