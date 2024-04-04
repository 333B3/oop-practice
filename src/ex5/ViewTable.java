package ex5;

import java.util.Scanner;
import java.io.Serializable;

/**
 * Клас, який представляє результати обчислень та забезпечує їх відображення.
 * Реалізує інтерфейс View для створення відображення результатів у вигляді таблиці.
 */
public class ViewTable implements View, Command, Serializable {
    private static final long serialVersionUID = 1L;
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;

    /**
     * Конструктор класу, який отримує від користувача вхідні дані та обчислює кількість одиниць.
     */
    public ViewTable() {
        getInputAndCalculate();
        // Додаємо команду у історію при створенні екземпляру класу
        CommandHistory.getInstance().add(this);
    }

    private void getInputAndCalculate() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть висоту основи трикутника: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Невірне введення. Будь ласка, введіть ціле число.");
            scanner.next(); // Пропускаємо некоректний ввід
        }
        this.baseHeight = scanner.nextInt();

        System.out.print("Введіть довжину сторони прямокутника: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Невірне введення. Будь ласка, введіть ціле число.");
            scanner.next(); // Пропускаємо некоректний ввід
        }
        this.rectangleLength = scanner.nextInt();

        // Не закриваємо Scanner тут, оскільки він використовується для отримання введення з консолі
        this.countOnes = countOnesInBinary(this.baseHeight, this.rectangleLength);
    }

    @Override
    public void viewHeader() {
        System.out.println();
        System.out.println(" ┌──────────────────────────┬──────────────────────────────┬───────────────────────┐");
        System.out.println(" │ Висота основи трикутника │ Довжина сторони прямокутника │ Кількість одиниць     │");
        System.out.println(" ├──────────────────────────┼──────────────────────────────┼───────────────────────┤");
    }

    @Override
    public void viewBody() {
        System.out.printf(" │ %24d │ %28d │ %21d │%n", baseHeight, rectangleLength, countOnes);
    }

    @Override
    public void viewFooter() {
        System.out.println(" └──────────────────────────┴──────────────────────────────┴───────────────────────┘");
    }

    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }

    /**
     * Метод для обчислення кількості одиниць у двійковому представленні суми периметрів.
     *
     * @param baseHeight      висота основи трикутника
     * @param rectangleLength довжина сторони прямокутника
     * @return кількість одиниць у двійковому представленні суми периметрів
     */
    public static int countOnesInBinary(int baseHeight, int rectangleLength) {
        int perimeterTriangle = 2 * (baseHeight + (int) Math.sqrt(Math.pow(baseHeight, 2) + Math.pow(rectangleLength / 2, 2)));
        int perimeterRectangle = 2 * (rectangleLength + baseHeight);
        int sum = perimeterTriangle + perimeterRectangle;
        String binary = Integer.toBinaryString(sum);
        int countOnes = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                countOnes++;
            }
        }
        System.out.print("Двійковий вигляд: " + binary);
        
        return countOnes;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть команду (1 - ввести нові дані, інше - показати таблицю):");
        String command = scanner.nextLine();
        if (command.equals("1")) {
            getInputAndCalculate(); // Введення нових даних
            // Додаємо команду у історію після введення нових даних
            CommandHistory.getInstance().add(this);
        } else {
            // Додавання команди у історію
            CommandHistory.getInstance().add(this);
            // Виконання відображення
            viewShow();
        }
    }
    
    @Override
    public void undo() {
        // Скасовуємо останнє введення, просто очищаючи дані та запитуючи їх знову
        this.baseHeight = 0;
        this.rectangleLength = 0;
        this.countOnes = 0;
        getInputAndCalculate(); // Знову отримуємо дані
        viewShow(); // Показуємо оновлені результати
    }
}

