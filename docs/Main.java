public class Main {
    public static void main(String[] args) {
        int baseHeight = 5; // висота трикутника
        int rectangleLength = 4; // довжина сторони прямокутника
        int perimeterTriangle = (int) (2 * (baseHeight + Math.sqrt(Math.pow(baseHeight, 2) + Math.pow(rectangleLength / 2, 2)))); // периметр трикутника
        int perimeterRectangle = 2 * (rectangleLength + baseHeight); // периметр прямокутника
        int sum = perimeterTriangle + perimeterRectangle; // сума периметрів
        String binary = Integer.toBinaryString(sum); // переведення суми у двійкову форму
        int countOnes = 0; // лічильник одиниць
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                countOnes++;
            }
        }
        System.out.println("Кількість одиниць у двійковому поданні суми: " + countOnes);
        System.out.println(binary);
    }
}
