package ex02_03;
    /**
     * @BinaryCountCalculator - відповідає за обчислення кількості одиниць у двійковому представленні суми значень.
    */
public class BinaryCountCalculator {
    /**
    * Метод обчислює кількість одиниць у двійковому представленні суми периметрів
    * рівнобедреного трикутника та прямокутника по заданій висоті та довжині сторони основи.
    * @param baseHeight        висота основи трикутника
    * @param rectangleLength   довжина сторони прямокутника
    * @return кількість одиниць у двійковому представленні суми периметрів
    */
    public static int countOnesInBinary(int baseHeight, int rectangleLength) {
        int perimeterTriangle = 2 * (baseHeight + (int) Math.sqrt(Math.pow(baseHeight, 2) + Math.pow(rectangleLength / 2, 2)));
        int perimeterRectangle = 2 * (rectangleLength + baseHeight);
        int sum = perimeterTriangle + perimeterRectangle;
        String binary = Integer.toBinaryString(sum);
        System.out.println("Сума периметрів: " + sum);
        System.out.println("Двійкове значення суми: " + binary);
        int countOnes = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                countOnes++;
            }
        }
        return countOnes;
    }
}
