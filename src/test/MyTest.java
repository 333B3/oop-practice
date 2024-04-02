package test;

import org.junit.Test;

import ex02_03.ViewResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class MyTest {

    @Test
    public void testViewResultConstructorWithDefaults() {
        // Arrange & Act
        ViewResult viewResult = new ViewResult();

        // Assert
        assertEquals(0, viewResult.getBaseHeight(), "Помилка встановлення значення за замовчуванням для висоти основи");
        assertEquals(0, viewResult.getRectangleLength(), "Помилка встановлення значення за замовчуванням для довжини прямокутника");
        assertEquals(0, viewResult.getCountOnes(), "Помилка встановлення значення за замовчуванням для кількості одиниць у двійковому представленні");
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        ViewResult viewResult = new ViewResult();

        // Act
        viewResult.setBaseHeight(10);
        viewResult.setRectangleLength(15);
        viewResult.setCountOnes(7);

        // Assert
        assertEquals(10, viewResult.getBaseHeight(), "Помилка у встановленні висоти основи");
        assertEquals(15, viewResult.getRectangleLength(), "Помилка у встановленні довжини прямокутника");
        assertEquals(7, viewResult.getCountOnes(), "Помилка у встановленні кількості одиниць у двійковому представленні");
    }
    @Test
    public void testCalculateAndSaveResult() {
        ViewResult viewResult = new ViewResult();
        viewResult.calculateAndSaveResult(5, 10);
        assertEquals(4, viewResult.getCountOnes());
    }
    @Test
    public void testCountOnesInBinary() {
        assertEquals(4, ViewResult.countOnesInBinary(5, 10));
    }
    @Test
    public void testViewResultConstructorWithInput() {
        String input = "5\n10\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ViewResult viewResult = new ViewResult();

        assertEquals(0, viewResult.getBaseHeight());
        assertEquals(0, viewResult.getRectangleLength());
        assertEquals(0, viewResult.getCountOnes());
    }


}
