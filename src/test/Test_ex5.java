package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ex02_03.ViewResult;

public class Test_ex5 {
 @Test
    public void testCountOnesInBinary() {
        // Перевіряємо для суми, що містить одиниці
        int onesCount = ViewResult.countOnesInBinary(4, 2); // Висота = 4, Довжина = 5
        assertEquals(3, onesCount); 

        
        onesCount = ViewResult.countOnesInBinary(3, 6); // Висота = 3, Довжина = 6
        assertEquals(1, onesCount); 
    }
        @Test
    public void testInBinary() {
        
        int baseHeight = 5;
        int rectangleLength = 10;

        
        int expectedCountOnes = 4;

       
        int actualCountOnes = ViewResult.countOnesInBinary(baseHeight, rectangleLength);

        
        assertEquals(expectedCountOnes, actualCountOnes);
    }

}
