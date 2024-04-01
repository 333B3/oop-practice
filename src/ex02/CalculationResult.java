package ex02;

import java.io.Serializable;

    /**
 * Клас, який представляє результати обчислень і є серіалізованим.
 * Зберігає параметри і результати обчислень, а саме висоту основи трикутника,
 * довжину сторони прямокутника та кількість одиниць у двійковому представленні суми периметрів.
 */
public class CalculationResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;

            /**
        * Конструктор класу CalculationResult для ініціалізації параметрів та результатів обчислень.
        *
        * @param baseHeight        висота основи трикутника
        * @param rectangleLength   довжина сторони прямокутника
        * @param countOnes         кількість одиниць у двійковому представленні суми периметрів
        */
    public CalculationResult(int baseHeight, int rectangleLength, int countOnes) {
        this.baseHeight = baseHeight;
        this.rectangleLength = rectangleLength;
        this.countOnes = countOnes;
    }

            /**
        * Повертає висоту основи трикутника.
        *
        * @return висота основи трикутника
        */
    public int getBaseHeight() {
        return baseHeight;
    }
            /**
        * Встановлює висоту основи трикутника.
        *
        * @param baseHeight нове значення висоти основи трикутника
        */
    public void setBaseHeight(int baseHeight) {
        this.baseHeight = baseHeight;
    }
            /**
        * Повертає довжину сторони прямокутника.
        *
        * @return довжина сторони прямокутника
        */
    public int getRectangleLength() {
        return rectangleLength;
    }
            /**
         * Встановлює довжину сторони прямокутника.
         *
         * @param rectangleLength нове значення довжини сторони прямокутника
         */
    public void setRectangleLength(int rectangleLength) {
        this.rectangleLength = rectangleLength;
    }
            /**
        * Повертає кількість одиниць у двійковому представленні суми периметрів.
        *
        * @return кількість одиниць у двійковому представленні суми периметрів
        */
    public int getCountOnes() {
        return countOnes;
    }
        /**
        * Встановлює кількість одиниць у двійковому представленні суми периметрів.
        *
        * @param countOnes нове значення кількості одиниць у двійковому представленні суми периметрів
        */
    public void setCountOnes(int countOnes) {
        this.countOnes = countOnes;
    }

    // Перевизначений метод toString() для зручного виводу інформації про об'єкт
    @Override
    public String toString() {
        return "Початкова висота: " + baseHeight + ", Початкова довжина: " + rectangleLength + ", Кількість одиниць: " + countOnes;
    }
}
