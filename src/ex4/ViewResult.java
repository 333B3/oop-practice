package ex4;

import java.util.Scanner;

public class ViewResult implements View {
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;
    

    public ViewResult() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть висоту основи трикутника: ");
        this.baseHeight = scanner.nextInt();

        System.out.print("Введіть довжину сторони прямокутника: ");
        this.rectangleLength = scanner.nextInt();

        scanner.close();

        this.countOnes = countOnesInBinary(this.baseHeight, this.rectangleLength);
    }
;
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
        System.out.print("Двійковий вигляд: " +  binary);
        return countOnes;
        
    }
}