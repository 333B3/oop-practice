package test;
import ex02.*;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Вхідні параметри
        int baseHeight = 5;
        int rectangleLength = 4;

        // Знаходимо кількість одиниць у двійковому поданні суми
        int countOnes = BinaryCountCalculator.countOnesInBinary(baseHeight, rectangleLength);

        // Виведення результатів
        System.out.println("Висота основи трикутника: " + baseHeight);
        System.out.println("Довжина сторони прямокутника: " + rectangleLength);
        System.out.println("Кількість одиниць у двійковому поданні суми: " + countOnes);

        scanner.close();
    }
}
