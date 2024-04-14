package ex7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel view;
    private JButton обчислитиButton;
    private JTextField textField1;
    private JTextField textField2;

    public GUI() {
        setContentPane(view);
        setVisible(true);
        setSize(800,300);

        обчислитиButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Отримуємо дані з текстових полів
                int height = Integer.parseInt(textField1.getText());
                int length = Integer.parseInt(textField2.getText());

                // Викликаємо метод для обчислення результату
                int countOnes = calculateCountOnes(height, length);

                // Отримуємо двійковий вигляд
                String binary = calculateBinaryRepresentation(height, length);

                // Виводимо результат у спливаючому вікні
                JOptionPane.showMessageDialog(null, "Двійковий вигляд: " + binary + "\nРезультат обчислення: " + countOnes);
            }
        });
    }

    private int calculateCountOnes(int height, int length) {
        int perimeterTriangle = 2 * (height + (int) Math.sqrt(Math.pow(height, 2) + Math.pow(length / 2, 2)));
        int perimeterRectangle = 2 * (length + height);
        int sum = perimeterTriangle + perimeterRectangle;
        String binary = Integer.toBinaryString(sum);
        int countOnes = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                countOnes++;
            }
        }
        System.out.print("Двійковий вигляд: " + binary);

        return countOnes;
    }
    private String calculateBinaryRepresentation(int height, int length) {
        int perimeterTriangle = 2 * (height + (int) Math.sqrt(Math.pow(height, 2) + Math.pow(length / 2, 2)));
        int perimeterRectangle = 2 * (length + height);
        int sum = perimeterTriangle + perimeterRectangle;
        return Integer.toBinaryString(sum);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
