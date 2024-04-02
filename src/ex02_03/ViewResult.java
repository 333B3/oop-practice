package ex02_03;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
        /**
        * Клас `ViewResult` представляє результати обчислень та їх відображення.
        * Цей клас реалізує інтерфейс `Serializable`, щоб можливо було серіалізувати об'єкти цього класу.
        */
public class ViewResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "calculation_result.ser";
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;
    /**
     * Конструктор класу `ViewResult` без параметрів.
     * Користувач вводить висоту основи трикутника та довжину сторони прямокутника з клавіатури.
     * Після введення обчислюється кількість одиниць у двійковому представленні суми периметрів.
     */
    public ViewResult() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть висоту основи трикутника: ");
        this.baseHeight = scanner.nextInt();

        System.out.print("Введіть довжину сторони прямокутника: ");
        this.rectangleLength = scanner.nextInt();

        scanner.close();

        this.countOnes = countOnesInBinary(this.baseHeight, this.rectangleLength);
    }
     /**
    * Конструктор класу `ViewResult` з параметрами.
    *
    * @param baseHeight      висота основи трикутника
    * @param rectangleLength довжина сторони прямокутника
    * @param countOnes       кількість одиниць у двійковому представленні суми периметрів
    */
    public ViewResult(int baseHeight, int rectangleLength, int countOnes) {
        this.baseHeight = baseHeight;
        this.rectangleLength = rectangleLength;
        this.countOnes = countOnes;
    }
     /**
     * Метод для обчислення кількості одиниць у двійковому представленні суми периметрів.
     *
     * @param baseHeight      висота основи трикутника
     * @param rectangleLength довжина сторони прямокутника
     * @return кількість одиниць у двійковому представленні суми периметрів
     */
    public void calculateAndSaveResult(int baseHeight, int rectangleLength) {
        this.baseHeight = baseHeight;
        this.rectangleLength = rectangleLength;
        this.countOnes = countOnesInBinary(baseHeight, rectangleLength);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(this);
            System.out.println("Результати обчислень було збережено у файл " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewShow() {
        viewHeader();
        System.out.printf("Висота основи трикутника: %d%n", baseHeight);
        System.out.printf("Довжина сторони прямокутника: %d%n", rectangleLength);
        System.out.printf("Кількість одиниць у двійковому представленні суми периметрів: %d%n", countOnes);
        viewFooter();
    }

    public void viewSave() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(this);
            System.out.println("Результати обчислень було збережено у файл " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewRestore() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ViewResult result = (ViewResult) in.readObject();
            this.baseHeight = result.baseHeight;
            this.rectangleLength = result.rectangleLength;
            this.countOnes = result.countOnes;
            System.out.println("Результати обчислень було відновлено з файлу " + FILE_NAME);
        }
    }

    public void viewHeader() {
        System.out.println("=== Результати обчислень ===");
    }

    public void viewFooter() {
        System.out.println("=== Кінець результатів ===");
    }

    public int getBaseHeight() {
        return baseHeight;
    }

    public void setBaseHeight(int baseHeight) {
        this.baseHeight = baseHeight;
    }

    public int getRectangleLength() {
        return rectangleLength;
    }

    public void setRectangleLength(int rectangleLength) {
        this.rectangleLength = rectangleLength;
    }

    public int getCountOnes() {
        return countOnes;
    }

    public void setCountOnes(int countOnes) {
        this.countOnes = countOnes;
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
        return countOnes;
    }
}

