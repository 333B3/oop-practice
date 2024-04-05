package ex6;

import java.util.Scanner;
import java.io.Serializable;
import java.util.Arrays;

public class ViewTable implements View, Command, Serializable {
    private static final long serialVersionUID = 1L;
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;

    public ViewTable() {
        getInputAndCalculate();
        CommandHistory.getInstance().add(this);
    }

    private void getInputAndCalculate() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть висоту основи трикутника: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Невірне введення. Будь ласка, введіть ціле число.");
            scanner.next(); 
        }
        this.baseHeight = scanner.nextInt();

        System.out.print("Введіть довжину сторони прямокутника: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Невірне введення. Будь ласка, введіть ціле число.");
            scanner.next();
        }
        this.rectangleLength = scanner.nextInt();

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

    public double calculateAverage() {
        int perimeterTriangle = 2 * (baseHeight + (int) Math.sqrt(Math.pow(baseHeight, 2) + Math.pow(rectangleLength / 2, 2)));
        int perimeterRectangle = 2 * (rectangleLength + baseHeight);
        return (perimeterTriangle + perimeterRectangle) / 2.0;
    }

    public int calculateMinimum() {
        return Arrays.stream(new int[]{baseHeight, rectangleLength, countOnes}).min().orElse(0);
    }

    public int calculateMaximum() {
        return Arrays.stream(new int[]{baseHeight, rectangleLength, countOnes}).max().orElse(0);
    }

    public int calculateSum() {
        int perimeterTriangle = 2 * (baseHeight + (int) Math.sqrt(Math.pow(baseHeight, 2) + Math.pow(rectangleLength / 2, 2)));
        int perimeterRectangle = 2 * (rectangleLength + baseHeight);
        return(perimeterTriangle + perimeterRectangle);
    }

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
        System.out.print("Двійковий вигляд: " + binary + "\n");
        System.out.print("Кількість одиниць: " + countOnes + "\n");
        
        return countOnes;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть команду (1 - ввести нові дані, інше - показати таблицю):");
        String command = scanner.nextLine();
        if (command.equals("1")) {
            Main.main(null);
            CommandHistory.getInstance().add(this);
        } else {
            CommandHistory.getInstance().add(this);
            viewShow();
        }
    }
    
    @Override
    public void undo() {
        this.baseHeight = 0;
        this.rectangleLength = 0;
        this.countOnes = 0;
        Main.main(null);
    }
}


