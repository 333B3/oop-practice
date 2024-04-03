package test;

import org.junit.Test;

import ex02_03.ViewResult;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test_ex4 {

    @Test
    public void testCountOnesInBinary() {
        // Перевіряємо для суми, що містить одиниці
        int onesCount = ViewResult.countOnesInBinary(4, 2); // Висота = 4, Довжина = 5
        assertEquals(3, onesCount); // Очікуємо 5 одиниць у двійковому представленні

        // Перевіряємо для суми, що не містить одиниць
        onesCount = ViewResult.countOnesInBinary(3, 6); // Висота = 3, Довжина = 6
        assertEquals(1, onesCount); // Очікуємо 0 одиниць у двійковому представленні
    }
        @Test
    public void testInBinary() {
        // Вхідні дані
        int baseHeight = 5;
        int rectangleLength = 10;

        // Очікуваний результат
        int expectedCountOnes = 4;

        // Виклик методу для обчислення кількості одиниць у двійковому представленні суми периметрів
        int actualCountOnes = ViewResult.countOnesInBinary(baseHeight, rectangleLength);

        // Перевірка результату
        assertEquals(expectedCountOnes, actualCountOnes);
    }
}